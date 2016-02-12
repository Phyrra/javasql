package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.helper.Source;

import java.util.ArrayList;
import java.util.List;

class SourceListVisitor extends SqlBaseVisitor<List<Source>> {
    private SourceVisitor sourceVisitor;

    public SourceListVisitor(SourceVisitor visitor) {
        this.sourceVisitor = visitor;
    }

    @Override
    public List<Source> visitSourceList(SqlParser.SourceListContext ctx) {
        List<Source> values = new ArrayList<Source>();

        values.add(sourceVisitor.visit(ctx.source()));

        if (ctx.sourceList() != null) {
            values.addAll(visit(ctx.sourceList()));
        }

        return values;
    }
}
