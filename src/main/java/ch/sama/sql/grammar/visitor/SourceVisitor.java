package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.NotImplementedException;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.helper.Source;

class SourceVisitor extends SqlBaseVisitor<Source> {
    ISourceFactory fac;

    public SourceVisitor(ISourceFactory fac) {
        this.fac = fac;
    }

    @Override
    public Source visitSource(SqlParser.SourceContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Source visitAliasedSource(SqlParser.AliasedSourceContext ctx) {
        Source source;
        if (ctx.primarySource() != null) {
            source = visit(ctx.primarySource());
        } else if (ctx.statement() != null) {
            throw new NotImplementedException("Query selection not implemented", ctx);
        } else {
            throw new NotImplementedException("Unknown source", ctx);
        }

        String alias = ctx.sqlIdentifier().Identifier().getText();
        source.as(alias);

        return source;
    }

    @Override
    public Source visitPrimarySource(SqlParser.PrimarySourceContext ctx) {
        String table = ctx.sqlIdentifier().Identifier().getText();
        return fac.table(table);
    }
}
