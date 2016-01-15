package ch.sama.sql.query.helper.pattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumericsTest {
    @Test
    public void nullIsNotInt() {
        assertEquals(false, Numerics.isInteger(null));
    }

    @Test
    public void emptyIsNotInt() {
        assertEquals(false, Numerics.isInteger(""));
    }

    @Test
    public void oneCharInt() {
        assertEquals(true, Numerics.isInteger("1"));
    }

    @Test
    public void multiCharInt() {
        assertEquals(true, Numerics.isInteger("12"));
    }

    @Test
    public void zeroIsInt() {
        assertEquals(true, Numerics.isInteger("0"));
    }

    @Test
    public void leadingZeroIsNotInt() {
        assertEquals(false, Numerics.isInteger("01"));
    }

    @Test
    public void negativeInt() {
        assertEquals(true, Numerics.isInteger("-1"));
    }

    // Numerical Float

    @Test
    public void nullIsNotNumericalFloat() {
        assertEquals(false, Numerics.isNumericalFloat(null));
    }

    @Test
    public void emptyIsNotNumericalFloat() {
        assertEquals(false, Numerics.isNumericalFloat(""));
    }

    @Test
    public void dotIsNotNumericalFloat() {
        assertEquals(false, Numerics.isNumericalFloat("."));
    }

    @Test
    public void noLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat(".1"));
    }

    @Test
    public void negativeNoLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("-.1"));
    }

    @Test
    public void numericLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("1.2"));
    }

    @Test
    public void negativeNumericLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("-1.2"));
    }

    @Test
    public void zeroLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("0.1"));
    }

    @Test
    public void negativeZeroLeadIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("-0.1"));
    }

    @Test
    public void noTrailIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("1."));
    }

    @Test
    public void negativeNoTrailIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("-1."));
    }

    @Test
    public void trailingZeroIsNumericalFloat() {
        assertEquals(true, Numerics.isNumericalFloat("1.0"));
        assertEquals(true, Numerics.isNumericalFloat("1.00"));
    }

    @Test
    public void multiZeroLeadIsNotNumericalFloat() {
        assertEquals(false, Numerics.isNumericalFloat("01.1"));
    }

    // Programmatical Float

    @Test
    public void nullIsNotProgrammaticalFloat() {
        assertEquals(false, Numerics.isProgrammaticalFloat(null));
    }

    @Test
    public void trailingZeroIsNotProgrammaticalFloat() {
        assertEquals(true, Numerics.isProgrammaticalFloat("1.0"));
        assertEquals(false, Numerics.isProgrammaticalFloat("1.00"));
    }

    // Strict Float

    @Test
    public void nullIsNotStrictFloat() {
        assertEquals(false, Numerics.isStrictFloat(null));
    }

    @Test
    public void noLeadIsNotStrictFloat() {
        assertEquals(false, Numerics.isStrictFloat(".1"));
    }

    @Test
    public void noTrailIsNotStrictFloat() {
        assertEquals(false, Numerics.isStrictFloat("1."));
    }

    @Test
    public void trailingZeroIsNotStrictFloat() {
        assertEquals(true, Numerics.isStrictFloat("1.0"));
        assertEquals(false, Numerics.isStrictFloat("1.00"));
    }
}
