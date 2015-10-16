package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

class ConditionVisitor extends SqlBaseVisitor<ICondition> {
    private QueryVisitor queryVisitor;
    private ValueVisitor valueVisitor;
    
    public ConditionVisitor(QueryVisitor query, ValueVisitor value) {
        this.queryVisitor = query;
        this.valueVisitor = value;
    }
    
    @Override
    public ICondition visitCondition(SqlParser.ConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ICondition visitLogicalOrCondition(SqlParser.LogicalOrConditionContext ctx) {
        ICondition c = visit(ctx.logicalAndCondition());

        if (ctx.logicalOrCondition() != null) {
            return Condition.or(visit(ctx.logicalOrCondition()), c);
        }

        return c;
    }

    @Override
    public ICondition visitLogicalAndCondition(SqlParser.LogicalAndConditionContext ctx) {
        ICondition c = visit(ctx.notCondition());

        if (ctx.logicalAndCondition() != null) {
            return Condition.and(visit(ctx.logicalAndCondition()), c);
        }

        return c;
    }

    @Override
    public ICondition visitNotCondition(SqlParser.NotConditionContext ctx) {
        ICondition c = visit(ctx.comparativeCondition());

        if (ctx.Not() != null) {
            return Condition.not(c);
        }

        return c;
    }

    @Override
    public ICondition visitComparativeCondition(SqlParser.ComparativeConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ICondition visitEqualCondition(SqlParser.EqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.eq(lhs, rhs);
    }

    @Override
    public ICondition visitUnequalCondition(SqlParser.UnequalConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.neq(lhs, rhs);
    }

    @Override
    public ICondition visitLessCondition(SqlParser.LessConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.le(lhs, rhs);
    }

    @Override
    public ICondition visitLessEqualCondition(SqlParser.LessEqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.lt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterCondition(SqlParser.GreaterConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterEqualCondition(SqlParser.GreaterEqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitLikeCondition(SqlParser.LikeConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.like(lhs, rhs);
    }

    @Override
    public ICondition visitIsNullCondition(SqlParser.IsNullConditionContext ctx) {
        Value val = valueVisitor.visit(ctx.expression());

        return Condition.isNull(val);
    }

    @Override
    public ICondition visitIsNotNullCondition(SqlParser.IsNotNullConditionContext ctx) {
        Value val = valueVisitor.visit(ctx.expression());

        return Condition.not(Condition.isNull(val));
    }

    @Override
    public ICondition visitInCondition(SqlParser.InConditionContext ctx) {
        Value val = valueVisitor.visit(ctx.expression());
        IQuery query = queryVisitor.visit(ctx.statement());

        return Condition.in(val, query);
    }

    @Override
    public ICondition visitNotInCondition(SqlParser.NotInConditionContext ctx) {
        Value val = valueVisitor.visit(ctx.expression());
        IQuery query = queryVisitor.visit(ctx.statement());

        return Condition.not(Condition.in(val, query));
    }
}
