package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.helper.Source;

class SourceVisitor extends SqlBaseVisitor<Source> {
    private ISourceFactory source;
    private QueryVisitor visitor;

    public SourceVisitor(IQueryFactory fac, QueryVisitor visitor) {
        this.source = fac.source();
        this.visitor = visitor;
    }

    @Override
    public Source visitSource(SqlParser.SourceContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Source visitAliasedSource(SqlParser.AliasedSourceContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Source visitAliasedTable(SqlParser.AliasedTableContext ctx) {
        Source table = visit(ctx.tableSource());

        String alias = ctx.sqlIdentifier().Identifier().getText();
        table.as(alias);

        return table;
    }

    @Override
    public Source visitTableSource(SqlParser.TableSourceContext ctx) {
        String table = ctx.sqlIdentifier().Identifier().getText();
        return source.table(table);
    }

    @Override
    public Source visitAliasedStatement(SqlParser.AliasedStatementContext ctx) {
        IQuery query = visitor.visit(ctx.statement());
        String alias = ctx.sqlIdentifier().Identifier().getText();

        return source.query(query).as(alias);
    }
}
