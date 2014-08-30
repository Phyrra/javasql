package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import org.junit.Test;

public class IdentifierTest {
    @Test
    public void singleChar() {
        new Field("A");
    }

	@Test
	public void string() {
        new Field("FIELD");
	}

    @Test
    public void underscore() {
        new Field("_FIELD");
    }

    @Test
    public void number() {
        new Field("FIELD1");
    }

    @Test(expected = IllegalIdentifierException.class)
    public void illegalStart() {
        new Field("1FIELD");
    }

    @Test(expected = IllegalIdentifierException.class)
    public void illegalChar() {
        new Field("F'IELD");
    }
}
