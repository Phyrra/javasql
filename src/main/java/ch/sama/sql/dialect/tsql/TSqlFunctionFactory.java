package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    There is no parent object to this,
    the function factory is not necessary - only a convenient helper

    The functions are much too dialect specific and cannot easily be abstracted
 */
public class TSqlFunctionFactory {
    public TSqlFunctionFactory() { }
    
    public Value coalesce(Value lhs, Value rhs) {
        Function f = new Function("COALESCE", lhs, rhs);

        return new Value(f, f.getDefaultString());
    }

    public static interface WhenThen {
        public String getString();
    }

    private static class ValueWhenThen implements WhenThen {
        private Value expression;
        private Value value;

        public ValueWhenThen(Value expression, Value value) {
            this.expression = expression;
            this.value = value;
        }

        public String getString() {
            return "WHEN " + expression.getValue() + " THEN " + value.getValue();
        }
    }

    public WhenThen whenThen(Value expression, Value value) {
        return new ValueWhenThen(expression, value);
    }

    private static class ConditionWhenThen implements WhenThen {
        private static final IConditionRenderer renderer = new TSqlConditionRenderer();

        private ICondition condition;
        private Value value;

        public ConditionWhenThen(ICondition condition, Value value) {
            this.condition = condition;
            this.value = value;
        }

        public String getString() {
            return "WHEN " + condition.render(renderer) + " THEN " + value.getValue();
        }
    }

    public WhenThen whenThen(ICondition condition, Value value) {
        return new ConditionWhenThen(condition, value);
    }

    private static class ElseWhenThen implements WhenThen {
        private Value value;

        public ElseWhenThen(Value value) {
            this.value = value;
        }

        public String getString() {
            return "ELSE " + value.getValue();
        }
    }

    public WhenThen otherwise(Value value) {
        return new ElseWhenThen(value);
    }

    private String buildWhenThen(WhenThen[] wts) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wts.length; ++i) {
            WhenThen wt = wts[i];

            if ((wt instanceof ElseWhenThen) && i != wts.length - 1) { // this also captures multiple ELSEs
                throw new BadParameterException("ELSE must be the last argument");
            }

            builder.append("\n");
            builder.append(wt.getString());
        }

        return builder.toString();
    }

    public Value caseWhen(Value expression, WhenThen... wts) {
        StringBuilder builder = new StringBuilder();

        builder.append("(CASE ");
        builder.append(expression.getValue());

        builder.append(buildWhenThen(wts));

        builder.append("\nEND)");

        return new Value(null, builder.toString());
    }

    public Value caseWhen(WhenThen... wts) {
        StringBuilder builder = new StringBuilder();

        builder.append("(CASE");

        builder.append(buildWhenThen(wts));

        builder.append("\nEND)");

        return new Value(null, builder.toString());
    }
    
    public List<Value> valueList(Value... value) {
        List<Value> values = new ArrayList<Value>();
        Collections.addAll(values, value);
        
        return values;
    }
    
    public List<IOrder> orderList(IOrder... order) {
        List<IOrder> orders = new ArrayList<IOrder>();
        Collections.addAll(orders, order);
        
        return orders;
    }
    
    public Value rowNumber(List<Value> partition, List<IOrder> order) {
        IOrderRenderer renderer = new TSqlOrderRenderer();
        
        StringBuilder builder = new StringBuilder();
        String prefix;
        
        builder.append("ROW_NUMBER() OVER (PARTITION BY ");

        prefix = "";
        for (Value v : partition) {
            builder.append(prefix);
            builder.append(v.getValue());
            
            prefix = ", ";
        }
        
        builder.append(" ORDER BY ");
        
        prefix = "";
        for (IOrder o : order) {
            builder.append(prefix);
            builder.append(o.render(renderer));
            
            prefix = ", ";
        }
        
        builder.append(")");
        
        return new Value(null, builder.toString());
    }

    public Value min(Value v1, Value v2) {
        return caseWhen(
                whenThen(Condition.lt(v1, v2), v1),
                otherwise(v2)
        );
    }

    public Value max(Value v1, Value v2) {
        return caseWhen(
                whenThen(Condition.gt(v1, v2), v1),
                otherwise(v2)
        );
    }
    
    // extend at leisure
}
