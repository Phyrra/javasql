package ch.sama.sql.query.helper;

public class Identifier {
    private static final String PATTERN = "([a-z]|[A-Z]|_)([a-z]|[A-Z]|[0-9]|_)*";

    public static boolean test(String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            return s.substring(1, s.length() - 1).matches(PATTERN);
        } else {
            return s.matches(PATTERN);
        }
    }
}
