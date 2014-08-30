package ch.sama.sql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.ResultSetTransformer;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.tsql.connection.JtdsConnection;
import ch.sama.sql.tsql.connection.TSqlExecutor;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.tsql.dialect.TSqlSchema;

import java.util.List;
import java.util.Map;

public class ServerConnection {
    public static void main(String[] args) {
        JtdsConnection connection = new JtdsConnection("localhost/TestDB", "sa", "asdf1234");
        ResultSetTransformer transformer = new ResultSetTransformer();

        TSqlQueryFactory factory = new TSqlQueryFactory();
        TSqlExecutor executor = new TSqlExecutor(connection, transformer);

        List<Map<String, Object>> list = executor.query(
                factory.create().select(factory.value(Value.ALL)).from(factory.table("tblTable1"))
        );

        for (Map<String, Object> row : list) {
            StringBuilder builder = new StringBuilder();
            String prefix = "";

            for (String column : row.keySet()) {
                Object obj = row.get(column);

                builder.append(prefix);
                builder.append(String.valueOf(obj));
                builder.append(" (");
                builder.append(obj.getClass().getName());
                builder.append(")");

                prefix = ", ";
            }

            System.out.println(builder.toString());
        }

        System.out.println("");
        System.out.println("---");
        System.out.println("");

        TSqlSchema schema = new TSqlSchema(executor, factory);
        Map<String, Table> tables = schema.getTables();

        for (Table table : tables.values()) {
            System.out.println(schema.getTableSchema(table));
        }
    }
}
