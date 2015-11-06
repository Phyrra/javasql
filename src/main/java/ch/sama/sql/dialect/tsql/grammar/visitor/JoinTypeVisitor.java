package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.join.JoinType;
import ch.sama.sql.query.helper.join.JoinTypeDirection;

class JoinTypeVisitor extends SqlBaseVisitor<JoinType> {
    @Override
    public JoinType visitJoinDirection(SqlParser.JoinDirectionContext ctx) {
        String dir = ctx.getText();

        switch (dir.toUpperCase()) {
            case "LEFT":
                return JoinType.LEFT;
            case "RIGHT":
                return JoinType.RIGHT;
            case "FULL":
                return JoinType.FULL;
            default:
                throw new BadSqlException("Unknown join direction: " + dir);
        }
    }

    @Override
    public JoinType visitJoinTypeSpecial(SqlParser.JoinTypeSpecialContext ctx) {
        String special = ctx.getText();

        switch (special.toUpperCase()) {
            case "CROSS":
                return JoinType.CROSS;
            case "INNER":
                return JoinType.INNER;
            default:
                throw new BadSqlException("Unknown join type: " + special);
        }
    }

    @Override
    public JoinType visitJoinTypeOuter(SqlParser.JoinTypeOuterContext ctx) {
        JoinTypeDirection direction = (JoinTypeDirection) visit(ctx.joinDirection());

        if (ctx.Outer() == null) {
            return direction;
        } else {
            return direction.outer();
        }
    }

    @Override
    public JoinType visitJoinType(SqlParser.JoinTypeContext ctx) {
        return visitChildren(ctx);
    }
}
