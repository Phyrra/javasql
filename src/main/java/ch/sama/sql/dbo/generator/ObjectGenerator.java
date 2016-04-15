package ch.sama.sql.dbo.generator;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.schema.ISchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjectGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ObjectGenerator.class);

    private Function<IType, Class<?>> typeConverter;

    public ObjectGenerator(Function<IType, Class<?>> typeConverter) {
        this.typeConverter = typeConverter;
    }
    
    public void generate(String srcFolder, String pkg, ISchema schema, Function<String, Boolean> filter) throws IOException {
        generate(
                srcFolder,
                pkg,
                schema.getTables().stream()
                    .filter(table -> filter.apply(table.getName()))
                    .collect(Collectors.toList())
        );
    }
    
    public void generate(String srcFolder, String pkg, ISchema schema) throws IOException {
        generate(srcFolder, pkg, schema.getTables());
    }
    
    public void generate(String srcFolder, String pkg, List<Table> tables) throws IOException {
        for (Table table : tables) {
            String className = getTableClassName(table);
            
            BufferedWriter file = createClassFile(srcFolder, pkg, className);
            
            try {
                file.write("package " + pkg + ";\n\n");

                file.write("import ch.sama.sql.dbo.result.obj.JpaObject;\n\n");

                file.write("import ch.sama.sql.jpa.Entity;\n");
                file.write("import ch.sama.sql.jpa.Column;\n");
                file.write("import ch.sama.sql.jpa.PrimaryKey;\n");
                file.write("import ch.sama.sql.jpa.AutoIncrement;\n\n");
                file.write("import ch.sama.sql.jpa.Default;\n\n");

                file.write("import java.util.UUID;\n");
                file.write("import java.util.Date;\n\n");
                
                file.write("@Entity\n");
                file.write("public class " + className + " extends JpaObject {\n");
                
                String fieldBlocks = table.getColumns().stream()
                    .map(this::generateFieldBlock)
                    .collect(Collectors.joining("\n\n"));
                
                file.write(fieldBlocks);
                
                file.write("}");

                logger.debug("Generated: " + table.getName());
            // don't catch, forward exception
            } finally {
                file.close();
            }
        }
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
        String name = table.getName();
        
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }
    
    private String getFieldMemberName(Field field) {
        String name = field.getName();
        
        return Character.toString(name.charAt(0)).toLowerCase() + name.substring(1);
    }
    
    private String getFieldFunctionName(Field field) {
        String name = field.getName();
        
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }
    
    private String generateFieldBlock(Field field) {
        StringBuilder builder = new StringBuilder();
        
        String type = typeConverter.apply(field.getDataType()).getSimpleName();
        
        String memberName = getFieldMemberName(field);
        String functionName = getFieldFunctionName(field);
        
        if (field.isPrimaryKey()) {
            builder.append("\t@PrimaryKey\n");
        }
        
        if (field.isAutoIncrement()) {
            builder.append("\t@AutoIncrement\n");
        }
        
        if (field.hasDefaultValue()) {
            builder.append(String.format("\t@Default(value = \"%s\")\n", field.getDefaultValue().getValue()));
        }

        if (field.isNullable()) {
            builder.append(String.format("\t@Column(name = \"%s\", nullable = true)\n", field.getName()));
        } else {
            builder.append(String.format("\t@Column(name = \"%s\", nullable = false)\n", field.getName()));
        }
        builder.append(String.format("\tprivate %s %s;\n\n", type, memberName));
        
        builder.append(String.format("\tpublic void set%s(%s arg) {\n", functionName, type));
        builder.append(String.format("\t\tthis.%s = arg;\n", memberName));
        builder.append("\t}\n\n");
        
        builder.append(String.format("\tpublic %s get%s() {\n", type, functionName));
        builder.append(String.format("\t\treturn %s;\n", memberName));
        builder.append("\t}");
        
        return builder.toString();
    }
}