package ch.sama.sql.generator;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.ISchema;
import ch.sama.sql.dbo.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassGenerator {
    public static interface TableFilter {
        public boolean filter(String table);
    }

    private ISchema schema;

    public ClassGenerator(ISchema schema) {
        this.schema = schema;
    }

    public void generate(String srcFolder, String pkg) throws IOException {
        generate(srcFolder, pkg, new TableFilter() {
            @Override
            public boolean filter(String name) {
                return true;
            }
        });
    }

    public void generate(String srcFolder, String pkg, TableFilter filter) throws IOException {
        BufferedWriter writer = createClassFile(srcFolder, pkg, "Tables");

        writer.write("package " + pkg + ";\n\n");
        writer.write("import " + pkg + ".tables.*;\n\n");
        writer.write("public class Tables {\n");

        for (Table table : schema.getTables()) {
            String tableName = table.getName();

            if (filter.filter(tableName)) {
                String tableClassName = getTableClassName(table);
                String tableVarName = getTableVariableName(table);

                String schemaInit;
                String tableSchema = table.getSchema();
                if (tableSchema == null) {
                    schemaInit = "";
                } else {
                    schemaInit = "\"" + tableSchema + "\", ";
                }

                writer.write("\tpublic static final " + tableClassName + " " + tableVarName + " = new " + tableClassName + "(" + schemaInit + "\"" + tableName + "\");\n");
                generateTableClass(table, srcFolder, pkg + ".tables");
            }
        }

        writer.write("}");
        writer.close();
    }

    private void generateTableClass(Table table, String srcFolder, String pkg) throws IOException {
        String tableName = table.getName();
        String tableClassName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);

        BufferedWriter writer = createClassFile(srcFolder, pkg, tableClassName);

        writer.write("package " + pkg + ";\n\n");
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
        String tableName = table.getName();
        return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
    }

    private String getTableVariableName(Table table) {
        String tableName = table.getName();
        return tableName.substring(0, 1).toLowerCase() + tableName.substring(1);
    }
}
