package ch.sama.sql.query.helper.pattern;

public class Numerics {
    public static final String INT = "^-?(0|[1-9]\\d*)$";

    public static final String NUMERICAL_FLOAT = "^-?((0|[1-9]\\d*)\\.\\d*|\\.\\d+)$";
    public static final String PROGRAMMATICAL_FLOAT = "-?((0|[1-9]\\d*)\\.(\\d*[1-9]|0)?|\\.(\\d*[1-9]|0))";
    public static final String STRICT_FLOAT = "-?(0|[1-9]\\d*)\\.(\\d*[1-9]|0)";

    /**
     * Allows for all integer representations
     * - x
     * Disallows leading zeroes
     * - 0x
     * @param s String to evaluate
     * @return boolean
     */
    public static boolean isInteger(String s) {
        return s != null && s.matches(INT);
    }

    /**
     * Allows for all floating point representations
     * - .x
     * - x.
     * - x.y
     * - x.y0
     * Disallows leading zeroes for the integer part
     * - 0x.y
     * @param s String to evaluate
     * @return boolean
     */
    public static boolean isNumericalFloat(String s) {
        return s != null && s.matches(NUMERICAL_FLOAT);
    }

    /**
     * Allows for floating point representations that will not be shortened
     * - .x
     * - x.
     * - x.y
     * Disallows leading and trailing zeroes
     * - 0x.y
     * - x.y0
     * @param s String to evaluate
     * @return boolean
     */
    public static boolean isProgrammaticalFloat(String s) {
        return s != null && s.matches(PROGRAMMATICAL_FLOAT);
    }

    /**
     * Allows only for floating point representations that will not change on Double.toString()
     * - x.y
     * Disallows everything that will not have a consistent roundtrip, including leading and trailing zeroes
     * - .x
     * - x.
     * - 0x.y
     * - x.y0
     * @param s String to evaluate
     * @return boolean
     */
    public static boolean isStrictFloat(String s) {
        return s != null && s.matches(STRICT_FLOAT);
    }
}
