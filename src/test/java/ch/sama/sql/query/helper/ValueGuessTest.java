package ch.sama.sql.query.helper;

import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.query.exception.BadParameterException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class ValueGuessTest {
    private static final TSqlValueFactory value = new TSqlValueFactory();

    @Test
    public void guessNull() {
        assertEquals("NULL", value.object(null).getValue());
    }

    @Test
    public void guessBoolean() {
        assertEquals("1", value.object(true).getValue());
    }

    @Test
    public void guessShort() {
        assertEquals("123", value.object((short) 123).getValue());
    }

    @Test
    public void guessInt() {
        assertEquals("123", value.object((int) 123).getValue());
    }

    @Test
    public void guessLong() {
        assertEquals("123", value.object((long) 123).getValue());
    }

    @Test
    public void guessFloat() {
        assertEquals("1.23", value.object(1.23f).getValue());
    }

    @Test
    public void guessDouble() {
        assertEquals("1.23", value.object(1.23).getValue());
    }

    @Test
    public void guessDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertEquals("CONVERT(datetime, '2016-03-22 00:00:00', 21)", value.object(sdf.parse("2016-03-22")).getValue());
    }

    @Test
    public void guessEmptyString() {
        assertEquals("NULL", value.object("").getValue());
    }

    @Test
    public void guessNullString() {
        assertEquals("NULL", value.object("nUlL").getValue());
    }

    @Test
    public void guessIntString() {
        assertEquals("1", value.object("1").getValue());
    }

    @Test
    public void guessFloatString() {
        assertEquals("1.23", value.object("1.23").getValue());
    }

    @Test
    public void guessNormDateString() {
        assertEquals("CONVERT(datetime, '2016-03-22 00:00:00', 21)", value.object("22.03.2016").getValue());
    }

    @Test
    public void guessNormDateStringIncomplete() {
        assertEquals("CONVERT(datetime, '2016-04-01 00:00:00', 21)", value.object("1.4.2016").getValue());
    }

    @Test
    public void guessNormDateTimeString() {
        assertEquals("CONVERT(datetime, '2016-03-22 16:14:00', 21)", value.object("22.03.2016 16:14:00").getValue());
    }

    @Test
    public void guessNormDateTimeStringIncomplete() {
        assertEquals("CONVERT(datetime, '2016-04-01 16:14:00', 21)", value.object("1.4.2016 16:14:00").getValue());
    }

    @Test
    public void guessIsoDateString() {
        assertEquals("CONVERT(datetime, '2016-03-22 00:00:00', 21)", value.object("2016-03-22").getValue());
    }

    @Test
    public void guessIsoDateTimeString() {
        assertEquals("CONVERT(datetime, '2016-03-22 16:14:00', 21)", value.object("2016-03-22 16:14").getValue());
    }

    @Test
    public void guessString() {
        assertEquals("'Hello'", value.object("Hello").getValue());
    }

    @Test (expected = BadParameterException.class)
    public void wrongGuess() {
        value.object(new Object());
    }
}
