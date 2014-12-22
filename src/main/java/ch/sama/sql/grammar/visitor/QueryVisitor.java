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

    private ValueVisitor valueVisitor;
    private ValueListVisitor valueListVisitor;
    private SourceVisitor sourceVisitor;
    private SourceListVisitor sourceListVisitor;

    private ConditionVisitor conditionVisitor;
    private OrderListVisitor orderListVisitor;

    public QueryVisitor(IQueryFactory factory) {
        this.factory = factory;

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

    private JoinQueryFinal joinTo(FromQuery query, SqlParser.JoinStatementContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();

        switch (getJoinType(ctx)) {
            case "left":
                return query.join(getSource(ctx.source()).as(alias))
                        .left()
                        .on(getCondition(ctx.condition()));
            case "right":
                return query.join(getSource(ctx.source()).as(alias))
                        .right()
                        .on(getCondition(ctx.condition()));
            default:
                return query.join(getSource(ctx.source()).as(alias))
                        .on(getCondition(ctx.condition()));
        }
    }

    private JoinQueryFinal joinTo(JoinQueryFinal query, SqlParser.JoinStatementContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();

        switch (getJoinType(ctx)) {
            case "left":
                return query
                        .join(getSource(ctx.source()).as(alias))
                        .left()
                        .on(getCondition(ctx.condition()));
            case "right":
                return query.join(getSource(ctx.source()).as(alias))
                        .right()
                        .on(getCondition(ctx.condition()));
            default:
                return query.join(getSource(ctx.source()).as(alias))
                        .on(getCondition(ctx.condition()));
        }
    }

    /*
    @Override
    public IQuery visitFullStatement(SqlParser.FullStatementContext ctx) {
        IQuery query;

        if (ctx.cteStatementHead() != null) {
            query = visit(ctx.cteStatementHead());
        } else {
            query = factory.query();
        }

        query.chain(visit(ctx.unionStatement()));

        return query;
    }

    @Override
    public IQuery visitUnionStatement(SqlParser.UnionStatementContext ctx) {
        IQuery query = visit(ctx.statement());

        if (ctx.unionStatement() != null) {
            query = query.chain(visit(ctx.unionStatement()));
        }

        return query;
    }

    @Override
    public IQuery visitCteStatementHead(SqlParser.CteStatementHeadContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public IQuery visitCteStatementBlock(SqlParser.CteStatementBlockContext ctx) {
        IQuery query = visit(ctx.cteStatement());

        if (ctx.cteStatementBlock() != null) {
            query = query.chain(visit(ctx.cteStatementBlock()));
        }

        return query;
    }

    @Override
    public IQuery visitCteStatement(SqlParser.CteStatementContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();
        return factory.query().with(alias).as(visit(ctx.statement()));
    }

    @Override
    public IQuery visitStatement(SqlParser.StatementContext ctx) {
        SelectQuery select = factory.query().select(getValueList(ctx.selectStatement().valueList()));

        FromQuery from = null;
        JoinQueryFinal join = null;

        if (ctx.fromStatement() != null) {
            from = select.from(getSourceList(ctx.fromStatement().sourceList()));

            List<SqlParser.JoinStatementContext> joins = ctx.joinStatement();
            if (joins != null) {
                for (SqlParser.JoinStatementContext jCtx : joins) {
                    if (join == null) {
                        join = joinTo(from, jCtx);
                    } else {
                        join = joinTo(join, jCtx);
                    }
                }
            }
        }

        WhereQuery where = null;
        if (ctx.whereStatement() != null) {
            ICondition condition = getCondition(ctx.whereStatement().condition());

            if (join != null) {
                where = join.where(condition);
            } else if (from != null) {
                where = from.where(condition);
            } else {
                where = select.where(condition);
            }
        }

        OrderQuery order = null;
        if (ctx.orderStatement() != null) {
            IOrder[] orderList = getOrderList(ctx.orderStatement().orderList());

            if (where != null) {
                order = where.order(orderList);
            } else if (join != null) {
                order = join.order(orderList);
            } else if (from != null) {
                order = from.order(orderList);
            }
            // TODO:
            // cannot "select order" I think?
            // This is not reflected in the grammar actually.. it would just vanish
        }

        List<IQuery> queries = new ArrayList<IQuery>();
        queries.add(order);
        queries.add(where);
        queries.add(join);
        queries.add(from);

        for (IQuery query : queries) {
            if (query != null) {
                return query;
            }
        }

        return select;
    }
    */
}
