package ch.sama.sql.dbo.generator;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.schema.ISchema;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClassGenerator<T extends IQueryFactory> {
    private static final Logger logger = LoggerFactory.getLogger(ClassGenerator.class);

    private ISchema schema;
    private Class<T> type;

    public ClassGenerator(ISchema schema, Class<T> type) {
        this.schema = schema;
        this.type = type;
    }

    public void generate(String srcFolder, String pkg) throws IOException {
        generate(srcFolder, pkg, name -> true);
    }

    public void generate(String srcFolder, String pkg, Function<String, Boolean> filter) throws IOException {
        BufferedWriter tables = createClassFile(srcFolder, pkg, "Tables");

        tables.write("package " + pkg + ";\n\n");
        tables.write("import java.util.Arrays;\n");
        tables.write("import java.util.List;\n");
        tables.write("import ch.sama.sql.dbo.Table;\n");
        tables.write("import " + pkg + ".tables.*;\n\n");
        tables.write("public class Tables {\n");

        BufferedWriter sources = createClassFile(srcFolder, pkg, "Sources");

        sources.write("package " + pkg + ";\n\n");
        sources.write("import " + pkg + ".sources.*;\n\n");
        sources.write("public class Sources {\n");

        List<String> tableList = new ArrayList<String>();

        for (Table table : schema.getTables()) {
            String tableName = table.getName();

            if (filter.apply(tableName)) {
                String tableClassName = getTableClassName(table);
                String sourceClassName = getSourceClassName(table);
                String tableVarName = getTableVariableName(table);

                tables.write("\tpublic static final " + tableClassName + " " + tableVarName + " = new " + tableClassName + "();\n");
                generateTableClass(table, srcFolder, pkg);

                sources.write("\tpublic static final " + sourceClassName + " " + tableVarName + " = new " + sourceClassName + "();\n");
                generateSourceClass(table, srcFolder, pkg);

                tableList.add("\t\t\t" + tableVarName);

                logger.debug("Generated: " + tableName);
            }
        }

        tables.write("\n\tpublic static List<Table> getSchema() {\n");
        tables.write("\t\treturn Arrays.asList(\n");
        tables.write(
                tableList.stream()
                        .collect(Collectors.joining(",\n"))
        );
        tables.write("\n\t\t);\n\t};\n");

        tables.write("}");
        tables.close();

        sources.write("}");
        sources.close();
    }

    private void generateTableClass(Table table, String srcFolder, String pkg) throws IOException {
        String tableClassName = getTableClassName(table);

        String tPkg = pkg + ".tables";
        BufferedWriter writer = createClassFile(srcFolder, tPkg, tableClassName);

        writer.write("package " + tPkg + ";\n\n");
        writer.write("import java.util.Arrays;\n");
        writer.write("import java.util.List;\n");
        writer.write("import ch.sama.sql.dbo.Table;\n");
        writer.write("import ch.sama.sql.dbo.Field;\n");
        writer.write("import ch.sama.sql.dbo.GenericType;\n\n");
        writer.write("public class " + tableClassName + " extends Table {\n");

        for (Field field : table.getColumns()) {
            String fieldName = field.getName();
            writer.write("\tpublic Field " + fieldName + " = new Field(this, \"" + fieldName + "\")");

            IType type = field.getDataType();
            if (type != null) {
                writer.write(".chainType(new GenericType(\"" + type.getString() + "\"))");
            }

            if (!field.isNullable()) {
                writer.write(".chainNullable(false)");
            }

            if (field.isPrimaryKey()) {
                writer.write(".chainPrimaryKey(true)");
            }

            if (field.isAutoIncrement()) {
                writer.write(".chainAutoIncrement(true)");
            }

            writer.write(";\n");
        }

        writer.write("\n");

        String schemaInit = table.getSchema();
        String tableName = table.getName();

        writer.write("\tpublic " + tableClassName + "() {\n");

        if (schemaInit != null) {
            writer.write("\t\tsuper(\"" + schemaInit + "\", \"" + tableName + "\");\n");
        } else {
            writer.write("\t\tsuper(\"" + tableName + "\");\n");
        }

        writer.write("\n");

        writer.append(
                table.getColumns().stream()
                        .map(f -> "\t\taddColumn(" + f.getName() + ");")
                        .collect(Collectors.joining("\n"))
        );

        writer.append("\n\t}\n");

        writer.write("}");
        writer.close();
    }

    private void generateSourceClass(Table table, String srcFolder, String pkg) throws IOException {
        String sourceClassName = getSourceClassName(table);
        String tableClassName = getTableClassName(table);

        String sPkg = pkg + ".sources";
        BufferedWriter writer = createClassFile(srcFolder, sPkg, sourceClassName);

        writer.write("package " + sPkg + ";\n\n");
        writer.write("import " + pkg + ".tables." + tableClassName + ";\n");
        writer.write("import ch.sama.sql.query.base.IQueryRenderer;\n");
        writer.write("import ch.sama.sql.query.base.IValueFactory;\n");
        writer.write("import ch.sama.sql.query.helper.Source;\n");
        writer.write("import ch.sama.sql.query.helper.Value;\n");
        writer.write("import " + type.getName() + ";\n\n");

        writer.write("public class " + sourceClassName + " extends Source {\n");
        writer.write("\tprivate static final " + type.getSimpleName() + " fac = new " + type.getSimpleName() + "();\n");
        writer.write("\tprivate static final IValueFactory value = fac.value();\n");
        writer.write("\tprivate static final IQueryRenderer renderer = fac.renderer();\n\n");

        writer.write("\tprivate static final " + tableClassName + " table = new " + tableClassName + "();\n\n");

        writer.write("\tpublic " + sourceClassName + "() {\n");
        writer.write("\t\tsuper(table, renderer.render(table));\n");
        writer.write("\t}\n\n");

        for (Field field : table.getColumns()) {
            String fieldName = field.getName();
            writer.write("\tpublic Value " + fieldName + " = value.field(table." + fieldName + ");\n");
        }

        writer.write("}");
        writer.close();
    }

    private BufferedWriter createClassFile(String srcFolder, String pkg, String className) throws IOException {
        String separator = System.getProperty("file.separator");
        String path = pkg.replace(".", separator);

        File file = new File(srcFolder + separator + path + separator + className + ".java");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        return writer;
    }

    private String getTableClassName(Table table) {
        return "Tbl_" + table.getName();
    }

    private String getSourceClassName(Table table) {
        return "Src_" + table.getName();
    }

    private String getTableVariableName(Table table) {
        return table.getName();
    }
}
