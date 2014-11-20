package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Function;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Value;

/*
    There is no parent object to this,
    the function factory is not necessary - only a convenient helper

    The functions are much too dialect specific and cannot easily be abstracted
 */
public class TSqlFunctionFactory {
    public TSqlFunctionFactory() { }
    
    public Function coalesce(Value lhs, Value rhs) {
        return new Function("COALESCE((" + lhs.getString() + "), (" + rhs.getString() + "))", false);
    }

    public static class WhenThen {
        private Value expression;
        private Value value;

        public WhenThen(Value expression, Value value) {
            this.expression = expression;
            this.value = value;
        }

        public boolean isElse() {
            return expression == null;
        }

        public String getString() {
            if (isElse()) {
                return "ELSE " + value.getString();
            } else {
                return "WHEN " + expression.getString() + " THEN " + value.getString();
            }
        }
    }
    public WhenThen whenThen(Value expression, Value value) {
        return new WhenThen(expression, value);
    }
    public Function caseWhen(Value expression, WhenThen... wts) {
        StringBuilder builder = new StringBuilder();

        builder.append("(CASE ");
        builder.append(expression.getString());

        for (int i = 0; i < wts.length; ++i) {
            WhenThen wt = wts[i];

            if (wt.isElse() && i != wts.length - 1) { // this also captures multiple ELSEs
                throw new BadParameterException("ELSE must be the last argument");
            }

            builder.append("\n");
            builder.append(wt.getString());
        }

        builder.append("\nEND)");

        return new Function(builder.toString(), false);
    }

    // extend at leisure
}
