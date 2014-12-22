package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

class ConditionVisitor extends SqlBaseVisitor<ICondition> {
    private ValueVisitor visitor;
    
    public ConditionVisitor(ValueVisitor visitor) {
        this.visitor = visitor;
    }
    
    @Override
    public ICondition visitCondition(SqlParser.ConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ICondition visitLogicalOrCondition(SqlParser.LogicalOrConditionContext ctx) {
        ICondition c = visit(ctx.logicalAndCondition());

        if (ctx.logicalOrCondition() != null) {
            return Condition.or(c, visit(ctx.logicalOrCondition()));
        }

        return c;
    }

    @Override
    public ICondition visitLogicalAndCondition(SqlParser.LogicalAndConditionContext ctx) {
        ICondition c = visit(ctx.notCondition());

        if (ctx.logicalAndCondition() != null) {
            return Condition.and(c, visit(ctx.logicalAndCondition()));
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
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.eq(lhs, rhs);
    }

    @Override
    public ICondition visitUnequalCondition(SqlParser.UnequalConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.neq(lhs, rhs);
    }

    @Override
    public ICondition visitLessCondition(SqlParser.LessConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.le(lhs, rhs);
    }

    @Override
    public ICondition visitLessThanCondition(SqlParser.LessThanConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.lt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterCondition(SqlParser.GreaterConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterThanCondition(SqlParser.GreaterThanConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitLikeCondition(SqlParser.LikeConditionContext ctx) {
        Value lhs = visitor.visit(ctx.expression(0));
        Value rhs = visitor.visit(ctx.expression(1));

        return Condition.like(lhs, rhs);
    }

    @Override
    public ICondition visitIsNullCondition(SqlParser.IsNullConditionContext ctx) {
        Value val = visitor.visit(ctx.expression());

        return Condition.isNull(val);
    }
}
