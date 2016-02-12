package ch.sama.sql.query.helper.order;

import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

public class DefOrder implements IOrder {
    private List<Value> values;

    public DefOrder(Value[] values) {
        this.values = new ArrayList<Value>();

        for (Value v : values) {
            this.values.add(v);
        }
    }

    public List<Value> getValues() {
        return values;
    }

    @Override
    public String render(IOrderRenderer renderer) {
        return renderer.render(this);
    }
}
