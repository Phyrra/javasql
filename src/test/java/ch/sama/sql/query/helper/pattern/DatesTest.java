package ch.sama.sql.query.helper.pattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatesTest {
    @Test
    public void nullOrEmptyIsNotGerDate() {
        assertEquals(false, Dates.isGerDate(null));
        assertEquals(false, Dates.isGerDate(""));
    }

    @Test
    public void gerDate() {
        assertEquals(true, Dates.isGerDate("1.1.2015"));
        assertEquals(true, Dates.isGerDate("01.01.2015"));
    }

    @Test
    public void zeroIsNoMonthInGerDate() {
        assertEquals(false, Dates.isGerDate("01.0.2015"));
        assertEquals(false, Dates.isGerDate("01.00.2015"));
    }

    @Test
    public void zeroIsNoDayInGerDate() {
        assertEquals(false, Dates.isGerDate("0.01.2015"));
        assertEquals(false, Dates.isGerDate("00.01.2015"));
    }
    
    @Test
    public void gerDateTime() {
        assertEquals(true, Dates.isGerDateTime("01.01.2015 00:00"));
        assertEquals(true, Dates.isGerDateTime("01.01.2015 00:00:00"));
    }

    @Test
    public void nullOrEmptyIsNotIsoDate() {
        assertEquals(false, Dates.isIsoDate(null));
        assertEquals(false, Dates.isIsoDate(""));
    }

    @Test
    public void isoDate() {
        assertEquals(true, Dates.isIsoDate("2015-1-1"));
        assertEquals(true, Dates.isIsoDate("2015-01-01"));
    }

    @Test
    public void zeroIsNoMonthInIsoDate() {
        assertEquals(false, Dates.isIsoDate("2015-0-01"));
        assertEquals(false, Dates.isIsoDate("2015-00-01"));
    }

    @Test
    public void zeroIsNoDayInIsoDate() {
        assertEquals(false, Dates.isIsoDate("2015-01-0"));
        assertEquals(false, Dates.isIsoDate("2015-01-00"));
    }

    @Test
    public void isoDateTime() {
        assertEquals(true, Dates.isIsoDateTime("2015-01-01 00:00"));
        assertEquals(true, Dates.isIsoDateTime("2015-01-01 00:00:00"));
    }

    @Test
    public void dateToIsoDate() {
        assertEquals("2015-01-02", Dates.toIsoDate("2015-1-2"));
        assertEquals("2015-01-02", Dates.toIsoDate("2.1.2015"));
    }

    @Test
    public void dateTimeToIsoDate() {
        assertEquals("2015-01-02", Dates.toIsoDate("2015-01-02 12:13:14"));
        assertEquals("2015-01-02", Dates.toIsoDate("02.01.2015 12:13:14"));
    }

    @Test
    public void dateToIsoDateTime() {
        assertEquals("2015-01-02 00:00:00", Dates.toIsoDateTime("2015-01-02"));
        assertEquals("2015-01-02 00:00:00", Dates.toIsoDateTime("02.01.2015"));
    }

    @Test
    public void dateTimeToIsoDateTime() {
        assertEquals("2015-01-02 12:13:00", Dates.toIsoDateTime("2015-01-02 12:13"));
        assertEquals("2015-01-02 12:13:14", Dates.toIsoDateTime("2015-01-02 12:13:14"));
    }

    @Test
    public void dateToGerDate() {
        assertEquals("02.01.2015", Dates.toGerDate("2.1.2015"));
        assertEquals("02.01.2015", Dates.toGerDate("2015-1-2"));
    }

    @Test
    public void dateTimeToGerDate() {
        assertEquals("02.01.2015", Dates.toGerDate("02.01.2015 12:13:14"));
        assertEquals("02.01.2015", Dates.toGerDate("2015-01-02 12:13:14"));
    }

    @Test
    public void dateToGerDateTime() {
        assertEquals("02.01.2015 00:00:00", Dates.toGerDateTime("02.01.2015"));
        assertEquals("02.01.2015 00:00:00", Dates.toGerDateTime("2015-01-02"));
    }

    @Test
    public void dateTimeToGerDateTime() {
        assertEquals("02.01.2015 12:13:00", Dates.toGerDateTime("2015-01-02 12:13"));
        assertEquals("02.01.2015 12:13:14", Dates.toGerDateTime("2015-01-02 12:13:14"));
    }
}