package ch.sama.sql.query.helper.pattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatesTest {
    @Test
    public void nullIsNotEuropeanDate() {
        assertEquals(false, Dates.isEuropeanDate(null));
    }

    @Test
    public void emptyIsNotEuropeanDate() {
        assertEquals(false, Dates.isEuropeanDate(""));
    }

    @Test
    public void halfEuropeanDate() {
        assertEquals(true, Dates.isEuropeanDate("1.1.2015"));
    }

    @Test
    public void halfYearIsNotEuropeanDate() {
        assertEquals(false, Dates.isEuropeanDate("1.1.15"));
    }

    @Test
    public void europeanDate() {
        assertEquals(true, Dates.isEuropeanDate("01.01.2015"));
    }

    @Test
    public void zeroIsNoMonthInEuropeanDate() {
        assertEquals(false, Dates.isEuropeanDate("01.0.2015"));
        assertEquals(false, Dates.isEuropeanDate("01.00.2015"));
    }

    @Test
    public void zeroIsNoDayInEuropeanDate() {
        assertEquals(false, Dates.isEuropeanDate("0.01.2015"));
        assertEquals(false, Dates.isEuropeanDate("00.01.2015"));
    }
    
    @Test
    public void europeanDateTime() {
        assertEquals(true, Dates.isEuropeanDateTime("01.01.2015 00:00"));
        assertEquals(true, Dates.isEuropeanDateTime("01.01.2015 00:00:00"));
    }

    @Test
    public void nullIsNotIsoDate() {
        assertEquals(false, Dates.isIsoDate(null));
    }

    @Test
    public void emptyIsNotIsoDate() {
        assertEquals(false, Dates.isIsoDate(""));
    }

    @Test
    public void halfIsNoIsoDate() {
        assertEquals(false, Dates.isIsoDate("2015-1-1"));
    }

    @Test
    public void halfYearIsNotIsoDate() {
        assertEquals(false, Dates.isIsoDate("15-01-01"));
    }

    @Test
    public void isoDate() {
        assertEquals(true, Dates.isIsoDate("2015-01-01"));
    }

    @Test
    public void zeroIsNoMonthInIsoDate() {
        assertEquals(false, Dates.isIsoDate("2015-00-01"));
    }

    @Test
    public void zeroIsNoDayInIsoDate() {
        assertEquals(false, Dates.isIsoDate("2015-01-00"));
    }

    @Test
    public void isoDateTime() {
        assertEquals(true, Dates.isIsoDateTime("2015-01-01 00:00"));
        assertEquals(true, Dates.isIsoDateTime("2015-01-01 00:00:00"));
    }

    @Test
    public void europeanDateToIsoDate() {
        assertEquals("2015-01-02", Dates.europeanToIsoDate("02.01.2015"));
    }

    @Test
    public void europeanDateTimeToIsoDate() {
        assertEquals("2015-01-02", Dates.europeanToIsoDate("02.01.2015 12:13:14"));
    }

    @Test
    public void europeanDateToIsoDateTime() {
        assertEquals("2015-01-02 00:00:00", Dates.europeanToIsoDateTime("02.01.2015"));
    }

    @Test
    public void europeanDateTimeToIsoDateTime() {
        assertEquals("2015-01-02 12:13:00", Dates.europeanToIsoDateTime("02.01.2015 12:13"));
        assertEquals("2015-01-02 12:13:14", Dates.europeanToIsoDateTime("02.01.2015 12:13:14"));
    }

    @Test
    public void isoDateToEuropeanDate() {
        assertEquals("02.01.2015", Dates.isoToEuropeanDate("2015-01-02"));
    }

    @Test
    public void isoDateTimeToEuropeanDate() {
        assertEquals("02.01.2015", Dates.isoToEuropeanDate("2015-01-02 12:13:14"));
    }

    @Test
    public void isoDateToEuropeanDateTime() {
        assertEquals("02.01.2015 00:00:00", Dates.isoToEuropeanDateTime("2015-01-02"));
    }

    @Test
    public void isoDateTimeToEuropeanDateTime() {
        assertEquals("02.01.2015 12:13:00", Dates.isoToEuropeanDateTime("2015-01-02 12:13"));
        assertEquals("02.01.2015 12:13:14", Dates.isoToEuropeanDateTime("2015-01-02 12:13:14"));
    }
}
