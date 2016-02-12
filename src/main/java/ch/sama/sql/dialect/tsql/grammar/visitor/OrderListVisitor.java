package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.order.IOrder;

import java.util.ArrayList;
import java.util.List;

class OrderListVisitor extends SqlBaseVisitor<List<IOrder>> {
    private ValueVisitor visitor;

    public OrderListVisitor(ValueVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public List<IOrder> visitOrderList(SqlParser.OrderListContext ctx) {
        List<IOrder> orders = new ArrayList<IOrder>();

        orders.addAll(visit(ctx.orderValue()));

        if (ctx.orderList() != null) {
            orders.addAll(visit(ctx.orderList()));
        }

        return orders;
    }

    @Override
    public List<IOrder> visitOrderValue(SqlParser.OrderValueContext ctx) {
        List<IOrder> orders = new ArrayList<IOrder>();

        orders.addAll(visitChildren(ctx));

        return orders;
    }

    @Override
    public List<IOrder> visitDefaultOrderValue(SqlParser.DefaultOrderValueContext ctx) {
        List<IOrder> orders = new ArrayList<IOrder>();

        orders.add(Order.def(visitor.visit(ctx.expression())));

        return orders;
    }

    @Override
    public List<IOrder> visitAscOrderValue(SqlParser.AscOrderValueContext ctx) {
        List<IOrder> orders = new ArrayList<IOrder>();

        orders.add(Order.asc(visitor.visit(ctx.expression())));

        return orders;
    }

    @Override
    public List<IOrder> visitDescOrderValue(SqlParser.DescOrderValueContext ctx) {
        List<IOrder> orders = new ArrayList<IOrder>();

        orders.add(Order.desc(visitor.visit(ctx.expression())));

        return orders;
    }
}
