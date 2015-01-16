# Java SQL #

Java SQL is a simple and easy to use way of bringing your SQL statements directly into your Java code, providing (some) compile time check for correctness and automatic value escaping.

Currently there is only an implementation of Microsoft's T-SQL available.

## Usage ##

To use the library copy the *.jar file into your library folder. When using gradle, use

    dependencies {
        compile files('/lib/JavaSQL.jar')
    }

If not, use your IDE of choice to bring the *.jar into your build path.

Alternatively, simply check out the entirety of the source code into your project.

## QuickStart ##

To get started, create a query factory of your choice. From this the value- and source factory can be derived.

    IQueryFactory fac = new TSqlQueryFactory();
    IValueFactory value = fac.value();
    ISourceFactory source = fac.source();

It's all SQL from there..

    fac.query()
        .select(value.table("TABLE"))
        .from(source.table("TABLE"))
        .where(Condition.eq(value.field("TABLE", "P_KEY"), 23);

## DB Schema ##

There is an (experimental) possibility to create classes from a database connection (or from an SQL schema file).

    ISchema schema = new TSqlSchema(IQueryExecutor, ITableFilter);
    new ClassGenerator<TSqlQueryFactory>(schema, ITableFilter, TSqlQueryFactory.class).generate("src/main/java", "ch.sama.project.generated");

The generated sources can then be used in a query.

    fac.query()
        .select(Sources.TABLE.FIELD)
        .from(Sources.TABLE)