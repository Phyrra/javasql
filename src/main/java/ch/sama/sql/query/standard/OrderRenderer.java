package ch.sama.sql.query.standard;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.order.AscOrder;
import ch.sama.sql.query.helper.order.DefOrder;
import ch.sama.sql.query.helper.order.DescOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;

import java.util.List;
import java.util.stream.Collectors;

public abstract class OrderRenderer implements IOrderRenderer {
    private String getBase(List<Value> values) {
        return values.stream()
                .map(Value::getValue)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String render(DefOrder o) {
        return getBase(o.getValues());
    }

    @Override
    public String render(AscOrder o) {
        return getBase(o.getValues()) + " ASC";
    }

    @Override
    public String render(DescOrder o) {
        return getBase(o.getValues()) + " DESC";
    }
}
