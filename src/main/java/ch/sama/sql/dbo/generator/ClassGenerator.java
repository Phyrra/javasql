package ch.sama.sql.dbo.generator;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.ISchema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassGenerator<T extends IQueryFactory> {
    private ISchema schema;
    private Class<T> type;

    public ClassGenerator(ISchema schema, Class<T> type) {
        this.schema = schema;
        this.type = type;
    }

    public void generate(String srcFolder, String pkg) throws IOException {
        generate(srcFolder, pkg, name -> true);
    }

    public void generate(String srcFolder, String pkg, ITableFilter filter) throws IOException {
        BufferedWriter tables = createClassFile(srcFolder, pkg, "Tables");

        tables.write("package " + pkg + ";\n\n");
        tables.write("import " + pkg + ".tables.*;\n\n");
        tables.write("public class Tables {\n");

        BufferedWriter sources = createClassFile(srcFolder, pkg, "Sources");

        sources.write("package " + pkg + ";\n\n");
        sources.write("import " + pkg + ".sources.*;\n\n");
        sources.write("public class Sources {\n");

        for (Table table : schema.getTables()) {
            String tableName = table.getName();

            if (filter.filter(tableName)) {
                String tableClassName = getTableClassName(table);
                String sourceClassName = getSourceClassName(table);
                String tableVarName = getTableVariableName(table);

                String schemaInit;
                String tableSchema = table.getSchema();
                if (tableSchema == null) {
                    schemaInit = "";
                } else {
                    schemaInit = "\"" + tableSchema + "\", ";
                }

                tables.write("\tpublic static final " + tableClassName + " " + tableVarName + " = new " + tableClassName + "(" + schemaInit + "\"" + tableName + "\");\n");
                generateTableClass(table, srcFolder, pkg);

                sources.write("\tpublic static final " + sourceClassName + " " + tableVarName + " = new " + sourceClassName + "(Tables." + tableVarName + ");\n");
                generateSourceClass(table, srcFolder, pkg);

                System.out.println("Generated: " + tableName);
            }
        }

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
        writer.write("import ch.sama.sql.dbo.Table;\n");
        writer.write("import ch.sama.sql.dbo.Field;\n\n");
        writer.write("public class " + tableClassName + " extends Table {\n");
        writer.write("\tpublic " + tableClassName + "(String schema, String table) {\n");
        writer.write("\t\tsuper(schema, table);\n");
        writer.write("\t}\n\n");
        writer.write("\tpublic " + tableClassName + "(String table) {\n");
        writer.write("\t\tsuper(table);\n");
        writer.write("\t}\n\n");

        for (Field field : table.getColumns()) {
            String fieldName = field.getName();
            writer.write("\tpublic Field " + fieldName + " = new Field(this, \"" + fieldName + "\");\n");
        }

        writer.write("}");
        writer.close();
    }

    private void generateSourceClass(Table table, String srcFolder, String pkg) throws IOException {
        String sourceClassName = getSourceClassName(table);
        String tableVarName = getTableVariableName(table);

        String sPkg = pkg + ".sources";
        BufferedWriter writer = createClassFile(srcFolder, sPkg, sourceClassName);

        writer.write("package " + sPkg + ";\n\n");
        writer.write("import " + pkg + ".Tables;\n");
        writer.write("import ch.sama.sql.dbo.Table;\n");
        writer.write("import ch.sama.sql.query.base.IQueryFactory;\n");
        writer.write("import ch.sama.sql.query.base.IQueryRenderer;\n");
        writer.write("import ch.sama.sql.query.base.IValueFactory;\n");
        writer.write("import ch.sama.sql.query.helper.Source;\n");
        writer.write("import ch.sama.sql.query.helper.Value;\n");
        writer.write("import " + type.getName() + ";\n\n");

        writer.write("public class " + sourceClassName + " extends Source {\n");
        writer.write("\tprivate static final IQueryFactory fac = new " + type.getSimpleName() + "();\n");
        writer.write("\tprivate static final IValueFactory value = fac.value();\n");
        writer.write("\tprivate static final IQueryRenderer renderer = fac.renderer();\n\n");

        writer.write("\tpublic " + sourceClassName + "(Table table) {\n");
        writer.write("\t\tsuper(table, table.getString(renderer));\n");
        writer.write("\t}\n\n");

        for (Field field : table.getColumns()) {
            String fieldName = field.getName();
            writer.write("\tpublic Value " + fieldName + " = value.field(Tables." + tableVarName + "." + fieldName + ");\n");
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
