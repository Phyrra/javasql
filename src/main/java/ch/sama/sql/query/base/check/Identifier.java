package ch.sama.sql.query.base.check;

public class Identifier {
    private static final String PATTERN = "([a-z]|[A-Z]|_)([a-z]|[A-Z]|[0-9]|_)*";

    public static boolean test(String s) {
        return s.matches(PATTERN);
    }
}
