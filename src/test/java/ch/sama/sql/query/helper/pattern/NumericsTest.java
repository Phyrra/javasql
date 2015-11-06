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

    @Test
    public void nullIsNotFloat() {
        assertEquals(false, Numerics.isFloat(null));
    }

    @Test
    public void emptyIsNotFloat() {
        assertEquals(false, Numerics.isFloat(""));
    }

    @Test
    public void noLeadFloat() {
        assertEquals(true, Numerics.isFloat(".1"));
    }

    @Test
    public void negativeNoLeadFloat() {
        assertEquals(true, Numerics.isFloat("-.1"));
    }

    @Test
    public void numericLeadFloat() {
        assertEquals(true, Numerics.isFloat("1.2"));
    }

    @Test
    public void negativeNumericLeadFloat() {
        assertEquals(true, Numerics.isFloat("-1.2"));
    }

    @Test
    public void zeroLeadFloat() {
        assertEquals(true, Numerics.isFloat("0.1"));
    }

    @Test
    public void negativeZeroLeadFloat() {
        assertEquals(true, Numerics.isFloat("-0.1"));
    }

    @Test
    public void noTrailFloat() {
        assertEquals(true, Numerics.isFloat("1."));
    }

    @Test
    public void negativeNoTrailFloat() {
        assertEquals(true, Numerics.isFloat("-1."));
    }

    @Test
    public void trailingZeroIsFloat() {
        assertEquals(true, Numerics.isFloat("1.00"));
    }
}
