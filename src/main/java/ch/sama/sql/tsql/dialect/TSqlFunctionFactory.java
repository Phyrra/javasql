package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

/*
    There is no parent object to this,
    the function factory is not necessary - only a convenient helper

    The functions are much too dialect specific and cannot easily be abstracted
 */
public class TSqlFunctionFactory {
    public TSqlFunctionFactory() { }
    
    public Value coalesce(Value lhs, Value rhs) {
        Function f = new  Function("COALESCE", lhs, rhs);

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

    // extend at leisure
}
