package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.standard.QueryRenderer;

public class SqLiteQueryRenderer extends QueryRenderer {
    private static final SqLiteConditionRenderer conditionRenderer = new SqLiteConditionRenderer();
    private static final SqLiteOrderRenderer orderRenderer = new SqLiteOrderRenderer();

    @Override
    public String render(ICondition condition) {
        return condition.render(conditionRenderer);
    }

    @Override
    public String render(IOrder order) {
        return order.render(orderRenderer);
    }

    @Override
    public String renderObjectName(String s) {
        return "\"" + s + "\"";
    }
}