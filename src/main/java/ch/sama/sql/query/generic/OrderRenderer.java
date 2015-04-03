package ch.sama.sql.query.generic;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.order.AscOrder;
import ch.sama.sql.query.helper.order.DefOrder;
import ch.sama.sql.query.helper.order.DescOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;

import java.util.List;

public abstract class OrderRenderer implements IOrderRenderer {
    private String getBase(List<Value> values) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value v : values) {
            builder.append(prefix);
            builder.append(v.getValue());

            prefix = ", ";
        }

        return builder.toString();
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
