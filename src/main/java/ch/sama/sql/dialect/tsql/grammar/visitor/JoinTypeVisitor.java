package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.JoinQuery;

import java.util.ArrayList;
import java.util.List;

class JoinTypeVisitor extends SqlBaseVisitor<List<JoinQuery.TYPE>> {
    @Override
    public List<JoinQuery.TYPE> visitJoinDirection(SqlParser.JoinDirectionContext ctx) {
        List<JoinQuery.TYPE> types = new ArrayList<JoinQuery.TYPE>();

        types.add(JoinQuery.TYPE.fromString(ctx.getText()));

        return types;
    }

    @Override
    public List<JoinQuery.TYPE> visitJoinTypeSpecial(SqlParser.JoinTypeSpecialContext ctx) {
        List<JoinQuery.TYPE> types = new ArrayList<JoinQuery.TYPE>();

        types.add(JoinQuery.TYPE.fromString(ctx.getText()));

        return types;
    }

    @Override
    public List<JoinQuery.TYPE> visitJoinTypeOuter(SqlParser.JoinTypeOuterContext ctx) {
        List<JoinQuery.TYPE> types = new ArrayList<JoinQuery.TYPE>();

        types.addAll(visit(ctx.joinDirection()));

        if (ctx.Outer() != null) {
            types.add(JoinQuery.TYPE.OUTER);
        }

        return types;
    }

    @Override
    public List<JoinQuery.TYPE> visitJoinType(SqlParser.JoinTypeContext ctx) {
        List<JoinQuery.TYPE> types = new ArrayList<JoinQuery.TYPE>();

        types.addAll(visitChildren(ctx));

        return types;
    }
}
