# Java SQL #

Java SQL is a simple and easy to use way of bringing your SQL statements directly into your Java code, providing (some) compile time check for correctness and automatic value escaping.

Currently there is only an implementation of Microsoft's T-SQL available.

## Build ##

The project is based on [Gradle](https://gradle.org/). Install it and call

    gradle jar
    
## Usage ##

To use the library copy the *.jar file into your library folder. When using gradle, use

    dependencies {
        compile files('/lib/JavaSQL.jar')
    }

If not, use your IDE of choice to bring the *.jar into your build path.

### Dependencies ###

The core of the library (generation of SQL) does not depend on anything but standard java.

You may use any class that implements the IConnection interface, or generate your own DB query functions, using only the generated SQL strings.

There are no data base drivers shipped with this library, to use the DBConnection class, you need to provide a driver matching your database.

    dependencies {
        compile group: 'net.sourceforge.jtds', name: 'jtds', version: '1.3.1' // Microsoft SQL Server (TSql)
        compile group: 'mysql', name: 'mysql-connector-java', version: '5.1.6' // MySql
        compile group: 'org.xerial', name: 'sqlite-jdbc', version: '3.8.7' // SQLite
        ..
    }

The grammar depends on Antlr4.

    dependencies {
        compile group: 'org.antlr', name: 'antlr4-runtime', version: '4.3'
    }

## QuickStart ##

To get started, create a query builder of your choice. From this the value- and source factory can be derived.

    TSqlQueryBuilder builder = new TSqlQueryBuilder();
    TSqlValueFactory value = builder.value();
    TSqlSourceFactory source = builder.source();

It's all SQL from there..

    fac.query()
        .select(value.table("TABLE"))
        .from(source.table("TABLE"))
        .where(
            Condition.eq(
                value.field("TABLE", "P_KEY"),
                value.numeric(23)
            )
        )
    .getSql();

Note that this only results in a query-string. The execution of the query can still be done by whatever data base driver you want.

There is an interface for the IQueryExecutor but it is not required.

## DB Schema ##

There is an (experimental) possibility to create classes from a database connection (or from an SQL schema file).

    TSqlConnection connection = new TSqlConnection(link, user, password);
    
    QueryExecutor<List<MapResult>> executor = new QueryExecutor<List<MapResult>>(connection, new MapTransformer());
    ISchema schema = new TSqlSchema(executor, table -> true);
    
    new ClassGenerator<TSqlQueryFactory>(schema, TSqlQueryFactory.class)
            .generate("src/main/java", "ch.project.generated");

The generated sources can then be used in a query.

    fac.query()
        .select(Sources.TABLE.FIELD)
        .from(Sources.TABLE)
    .getSql();