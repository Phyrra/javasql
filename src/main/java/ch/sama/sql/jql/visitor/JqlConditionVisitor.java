package ch.sama.sql.jql.visitor;

import ch.sama.sql.jql.antlr.JqlBaseVisitor;
import ch.sama.sql.jql.antlr.JqlParser;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.AndCondition;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.OrCondition;

import java.util.ArrayList;
import java.util.List;

public class JqlConditionVisitor extends JqlBaseVisitor<ICondition> {
    private ValueVisitor valueVisitor;
    private ValueListVisitor valueListVisitor;

    public JqlConditionVisitor(IValueFactory valueFactory) {
        this.valueVisitor = new ValueVisitor(valueFactory);
        this.valueListVisitor = new ValueListVisitor(this.valueVisitor);
    }

    @Override
    public ICondition visitCondition(JqlParser.ConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ICondition visitLogicalOrCondition(JqlParser.LogicalOrConditionContext ctx) {
        ICondition rhs = visit(ctx.logicalAndCondition());

        if (ctx.logicalOrCondition() != null) {
            ICondition lhs = visit(ctx.logicalOrCondition());

            if (lhs instanceof OrCondition) {
                List<ICondition> cond = new ArrayList<ICondition>();
                cond.addAll(((OrCondition) lhs).getConditions());
                cond.add(rhs);

                return Condition.or(cond.toArray(new ICondition[cond.size()]));
            }

            return Condition.or(visit(ctx.logicalOrCondition()), rhs);
        }

        return rhs;
    }

    @Override
    public ICondition visitLogicalAndCondition(JqlParser.LogicalAndConditionContext ctx) {
        ICondition rhs = visit(ctx.notCondition());

        if (ctx.logicalAndCondition() != null) {
            ICondition lhs = visit(ctx.logicalAndCondition());

            if (lhs instanceof AndCondition) {
                List<ICondition> cond = new ArrayList<ICondition>();
                cond.addAll(((AndCondition) lhs).getConditions());
                cond.add(rhs);

                return Condition.and(cond.toArray(new ICondition[cond.size()]));
            }

            return Condition.and(lhs, rhs);
        }

        return rhs;
    }

    @Override
    public ICondition visitNotCondition(JqlParser.NotConditionContext ctx) {
        ICondition c = visit(ctx.primaryCondition());

        if (ctx.notOperator() != null) {
            return Condition.not(c);
        }

        return c;
    }

    @Override
    public ICondition visitPrimaryCondition(JqlParser.PrimaryConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ICondition visitEqualCondition(JqlParser.EqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.eq(lhs, rhs);
    }

    @Override
    public ICondition visitUnequalCondition(JqlParser.UnequalConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.neq(lhs, rhs);
    }

    @Override
    public ICondition visitLessCondition(JqlParser.LessConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.le(lhs, rhs);
    }

    @Override
    public ICondition visitLessEqualCondition(JqlParser.LessEqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.lt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterCondition(JqlParser.GreaterConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitGreaterEqualCondition(JqlParser.GreaterEqualConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.gt(lhs, rhs);
    }

    @Override
    public ICondition visitLikeCondition(JqlParser.LikeConditionContext ctx) {
        Value lhs = valueVisitor.visit(ctx.expression(0));
        Value rhs = valueVisitor.visit(ctx.expression(1));

        return Condition.like(lhs, rhs);
    }

    @Override
    public ICondition visitIsNullCondition(JqlParser.IsNullConditionContext ctx) {
        Value value = valueVisitor.visit(ctx.expression());

        return Condition.isNull(value);
    }

    @Override
    public ICondition visitIsNotNullCondition(JqlParser.IsNotNullConditionContext ctx) {
        Value value = valueVisitor.visit(ctx.expression());

        return Condition.not(Condition.isNull(value));
    }

    @Override
    public ICondition visitInCondition(JqlParser.InConditionContext ctx) {
        Value value = valueVisitor.visit(ctx.expression());
        List<Value> values = valueListVisitor.visit(ctx.constantValueList());

        return Condition.in(value, values);
    }

    @Override
    public ICondition visitNotInCondition(JqlParser.NotInConditionContext ctx) {
        Value value = valueVisitor.visit(ctx.expression());
        List<Value> values = valueListVisitor.visit(ctx.constantValueList());

        return Condition.not(Condition.in(value, values));
    }

    @Override
    public ICondition visitParCondition(JqlParser.ParConditionContext ctx) {
        return visit(ctx.condition());
    }
}
