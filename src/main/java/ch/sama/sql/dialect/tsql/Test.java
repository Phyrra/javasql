package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.ISchema;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dbo.generator.ClassGenerator;
import ch.sama.sql.dbo.result.MapResultList;
import ch.sama.sql.dbo.result.MapTransformer;
import ch.sama.sql.dialect.tsql.connection.TSqlConnection;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        TSqlConnection connection = new TSqlConnection("192.168.112.14/BormBusiness15Dev", "sa", "default11$");

        QueryExecutor<MapResultList> executor = new QueryExecutor<MapResultList>(connection, new MapTransformer());
        ISchema schema = new TSqlSchema(executor, table -> table.startsWith("META_WEB"));

        new ClassGenerator<TSqlQueryBuilder>(schema, TSqlQueryBuilder.class)
                .generate("src/main/java", "ch.project.generated");
    }
}
