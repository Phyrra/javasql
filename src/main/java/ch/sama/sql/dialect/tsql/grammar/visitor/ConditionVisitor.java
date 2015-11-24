package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.AndCondition;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.OrCondition;

import java.util.ArrayList;
import java.util.List;

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
        ICondition rhs = visit(ctx.logicalAndCondition());

        if (ctx.logicalOrCondition() != null) {
            ICondition lhs = visit(ctx.logicalOrCondition());

            if (lhs instanceof OrCondition) {
                List<ICondition> cond = new ArrayList<ICondition>();
                cond.addAll(((OrCondition) lhs).getConditions());
                cond.add(rhs);

                return Condition.or(cond.toArray(new ICondition[cond.size()]));
            }

            return Condition.or(lhs, rhs);
        }

        return rhs;
    }

    @Override
    public ICondition visitLogicalAndCondition(SqlParser.LogicalAndConditionContext ctx) {
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
    public ICondition visitNotCondition(SqlParser.NotConditionContext ctx) {
        ICondition c = visit(ctx.primaryCondition());

        if (ctx.Not() != null) {
            return Condition.not(c);
        }

        return c;
    }

    @Override
    public ICondition visitPrimaryCondition(SqlParser.PrimaryConditionContext ctx) {
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

    @Override
    public ICondition visitParCondition(SqlParser.ParConditionContext ctx) {
        return visit(ctx.condition());
    }
}
