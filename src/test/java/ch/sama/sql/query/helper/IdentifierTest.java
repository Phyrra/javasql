package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.checker.Identifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdentifierTest {
    @Test
    public void singleChar() {
        assertEquals(true, Identifier.test("A"));
    }

	@Test
	public void string() {
        assertEquals(true, Identifier.test("FIELD"));
	}

    @Test
    public void underscore() {
        assertEquals(true, Identifier.test("_FIELD"));
    }

    @Test
    public void number() {
        assertEquals(true, Identifier.test("FIELD1"));
    }

    @Test
    public void illegalStart() {
        assertEquals(false, Identifier.test("1FIELD"));
    }

    @Test
    public void illegalChar() {
        assertEquals(false, Identifier.test("F'IELD"));
    }
}
