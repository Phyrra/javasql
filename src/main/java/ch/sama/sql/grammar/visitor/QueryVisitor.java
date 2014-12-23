package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class QueryVisitor extends SqlBaseVisitor<IQuery> {
    private IQueryFactory factory;
    private IQueryRenderer renderer;

    private ValueVisitor valueVisitor;
    private ValueListVisitor valueListVisitor;
    private SourceVisitor sourceVisitor;
    private SourceListVisitor sourceListVisitor;

    private ConditionVisitor conditionVisitor;
    private OrderListVisitor orderListVisitor;

    public QueryVisitor(IQueryFactory factory) {
        this.factory = factory;
        this.renderer = factory.renderer();

        this.valueVisitor = new ValueVisitor(factory.value());
        this.valueListVisitor = new ValueListVisitor(valueVisitor);
        this.sourceVisitor = new SourceVisitor(factory.source());
        this.sourceListVisitor = new SourceListVisitor(sourceVisitor);

        this.conditionVisitor = new ConditionVisitor(valueVisitor);
        this.orderListVisitor = new OrderListVisitor(valueVisitor);
    }

    private Value[] getValueList(SqlParser.ValueListContext ctx) {
        List<Value> valueList = valueListVisitor.visit(ctx);
        return valueList.toArray(new Value[valueList.size()]);
    }

    private Source[] getSourceList(SqlParser.SourceListContext ctx) {
        List<Source> sourceList = sourceListVisitor.visit(ctx);
        return sourceList.toArray(new Source[sourceList.size()]);
    }

    private IOrder[] getOrderList(SqlParser.OrderListContext ctx) {
        List<IOrder> orderList = orderListVisitor.visit(ctx);
        return orderList.toArray(new IOrder[orderList.size()]);
    }

    private Value getValue(SqlParser.ValueContext ctx) {
        return valueVisitor.visit(ctx);
    }

    private Source getSource(SqlParser.SourceContext ctx) {
        return sourceVisitor.visit(ctx);
    }

    private ICondition getCondition(SqlParser.ConditionContext ctx) {
        return conditionVisitor.visit(ctx);
    }

    private String getJoinType(SqlParser.JoinStatementContext ctx) {
        String joinType = "";
        if (ctx.joinType() != null) {
            joinType = ctx.joinType().getText().toLowerCase();
        }

        return joinType;
    }

    private IQuery chain(IQuery child, IQuery parent) {
        IQuery top = child;
        while (top.getParent() != null) {
            top = top.getParent();
        }

        top.chainTo(parent);

        return child;
    }

    @Override
    public IQuery visitFullStatement(SqlParser.FullStatementContext ctx) {
        IQuery base = factory.query();

        IQuery union = visit(ctx.unionStatement());

        if (ctx.cteStatementHead() != null) {
            IQuery cteBlock = chain(visit(ctx.cteStatementHead()), base);
            chain(union, cteBlock);
        } else {
            chain(union, base);
        }

        return union;
    }

    @Override
    public IQuery visitCteStatementHead(SqlParser.CteStatementHeadContext ctx) {
        return visit(ctx.cteStatementBlock());
    }

    @Override
    public IQuery visitCteStatementBlock(SqlParser.CteStatementBlockContext ctx) {
        IQuery cte = visit(ctx.cteStatement());

        if (ctx.cteStatementBlock() != null) {
            IQuery cteBlock = visit(ctx.cteStatementBlock());
            cte.chainTo(cteBlock);
        }

        return cte;
    }

    @Override
    public IQuery visitUnionStatement(SqlParser.UnionStatementContext ctx) {
        IQuery statement = visit(ctx.statement());

        if (ctx.unionStatement() != null) {
            IQuery union = visit(ctx.unionStatement());

            IQuery query = factory.query();
            chain(statement, query);
            chain(query, union);
        }

        return statement;
    }

    @Override
    public IQuery visitCteStatement(SqlParser.CteStatementContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();
        return factory.query().with(alias).as(visit(ctx.statement()));
    }

    @Override
    public IQuery visitStatement(SqlParser.StatementContext ctx) {
        IQuery iter = visit(ctx.selectStatement());

        if (ctx.fromStatement() != null) {
            iter = chain(visit(ctx.fromStatement()), iter);

            if (ctx.joinStatement() != null) {
                List<SqlParser.JoinStatementContext> joins = ctx.joinStatement();

                for (SqlParser.JoinStatementContext join : joins) {
                    iter = chain(visit(join), iter);
                }
            }
        }

        if (ctx.whereStatement() != null) {
            iter = chain(visit(ctx.whereStatement()), iter);
        }

        if (ctx.orderStatement() != null) {
            iter = chain(visit(ctx.orderStatement()), iter);
        }

        return iter;
    }

    @Override
    public IQuery visitSelectStatement(SqlParser.SelectStatementContext ctx) {
        Value[] values = getValueList(ctx.valueList());

        return new SelectQuery(renderer, null, values);
    }

    @Override
    public IQuery visitFromStatement(SqlParser.FromStatementContext ctx) {
        Source[] sources = getSourceList(ctx.sourceList());

        return new FromQuery(renderer, null, sources);
    }

    @Override
    public IQuery visitJoinStatement(SqlParser.JoinStatementContext ctx) {
        Source source = getSource(ctx.source());

        if (ctx.sqlIdentifier() != null) {
            source.as(ctx.sqlIdentifier().Identifier().getText());
        }

        ICondition condition = getCondition(ctx.condition());

        String joinType = getJoinType(ctx);
        switch (joinType) {
            case "left":
                return new JoinQuery(renderer, null, source).left().on(condition);
            case "right":
                return new JoinQuery(renderer, null, source).right().on(condition);
            default:
                return new JoinQuery(renderer, null, source).on(condition);
        }
    }

    @Override
    public IQuery visitWhereStatement(SqlParser.WhereStatementContext ctx) {
        ICondition condition = getCondition(ctx.condition());

        return new WhereQuery(renderer, null, condition);
    }

    @Override
    public IQuery visitOrderStatement(SqlParser.OrderStatementContext ctx) {
        IOrder[] orders = getOrderList(ctx.orderList());

        return new OrderQuery(renderer, null, orders);
    }
}
