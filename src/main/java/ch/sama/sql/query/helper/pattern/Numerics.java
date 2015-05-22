package ch.sama.sql.query.helper.pattern;

public class Numerics {
    public static final String INT = "^-?(0|[1-9]\\d*)$";
    public static final String FLOAT = "^-?((0|[1-9]\\d*)\\.\\d*|\\.\\d+)$";

    public static boolean isInteger(String s) {
        return s != null && s.matches(INT);
    }

    public static boolean isFloat(String s) {
        return s != null && s.matches(FLOAT);
    }
}
