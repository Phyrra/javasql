package ch.sama.sql.dbo;

import ch.sama.sql.query.base.checker.Identifier;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
	private String function;

    public Function(String function) {
        this(function, true);
    }
	
	public Function(String function, boolean test) {
        if (test) {
            Pattern pattern = Pattern.compile("^(.+)\\((.*)?\\)$");
            Matcher matcher = pattern.matcher(function);
            int idx = function.indexOf("(");

            if (!matcher.find() || idx <= 0) {
                throw new BadParameterException("Function does not match a function pattern: " + function);
            } else {
                String fnc = function.substring(0, idx);

                if (!Identifier.test(fnc)) {
                    throw new IllegalIdentifierException("Illegal function name: " + fnc);
                }
            }
        }

		this.function = function;
	}
	
	public String getString() {
		return function;
	}
}
