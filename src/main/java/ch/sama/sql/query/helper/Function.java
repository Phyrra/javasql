package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private String name;
    private List<Value> parameters;

    public Function(String name, Value... parameters) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException("Illegal function name: " + name);
        }

        this.name = name;

        this.parameters = new ArrayList<Value>();

        for (Value v : parameters) {
            this.parameters.add(v);
        }
    }

    // This function exists, because I don't think any DB actually has a different form of rendering functions
	public String getDefaultString() {
        StringBuilder builder = new StringBuilder();

        builder.append(name);
        builder.append("(");

        String prefix = "";
        for (Value v : parameters) {
            builder.append(prefix);
            builder.append(v.getValue());

            prefix = ", ";
        }

        builder.append(")");

		return builder.toString();
	}

    public String getString(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}
