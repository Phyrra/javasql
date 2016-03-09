package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Function {
	private String name;
    private List<Value> parameters;

    public Function(String name, Value... parameters) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException("Illegal function name: " + name);
        }

        this.name = name;
        this.parameters = Arrays.asList(parameters);
    }

    public String getName() {
        return name;
    }

    public List<Value> getParameters() {
        return parameters;
    }

    // This function exists, because I don't think any DB actually has a different form of rendering functions
	public String getDefaultString() {
		return name +
                "(" +
                parameters.stream()
                        .map(Value::getValue)
                        .collect(Collectors.joining(", ")) +
                ")";
	}

    public String getString(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}
