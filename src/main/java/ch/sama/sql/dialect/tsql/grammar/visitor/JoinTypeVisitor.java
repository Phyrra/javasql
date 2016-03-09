package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.helper.join.JoinType;
import ch.sama.sql.query.helper.join.JoinTypeDirection;

class JoinTypeVisitor extends SqlBaseVisitor<JoinType> {
    @Override
    public JoinTypeDirection visitJoinDirectionLeft(SqlParser.JoinDirectionLeftContext ctx) {
        return JoinType.LEFT;
    }

    @Override
    public JoinTypeDirection visitJoinDirectionRight(SqlParser.JoinDirectionRightContext ctx) {
        return JoinType.RIGHT;
    }

    @Override
    public JoinTypeDirection visitJoinDirectionFull(SqlParser.JoinDirectionFullContext ctx) {
        return JoinType.FULL;
    }

    @Override
    public JoinType visitJoinDirection(SqlParser.JoinDirectionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public JoinType visitJoinSpecialCross(SqlParser.JoinSpecialCrossContext ctx) {
        return JoinType.CROSS;
    }

    @Override
    public JoinType visitJoinSpecialInner(SqlParser.JoinSpecialInnerContext ctx) {
        return JoinType.INNER;
    }

    @Override
    public JoinType visitJoinTypeSpecial(SqlParser.JoinTypeSpecialContext ctx) {
        return visitChildren(ctx);
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
