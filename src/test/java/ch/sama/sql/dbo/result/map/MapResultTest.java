package ch.sama.sql.dbo.result.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MapResultTest {
    private MapResult map;

    @Before
    public void prepare() {
        map = new MapResult();
    }

    @Test
    public void doesContain() {
        map.put("KEY", "VALUE");

        assertEquals(true, map.containsKey("KEY"));
    }

    @Test
    public void doesNotContain() {
        assertEquals(false, map.containsKey("KEY"));
    }

    @Test
    public void keySet() {
        map.put("KEY1", "VALUE1");
        map.put("KEY2", "VALUE2");

        Set<String> set = map.getKeySet();

        assertEquals(true, set.contains("KEY1"));
        assertEquals(true, set.contains("KEY2"));
        assertEquals(false, set.contains("KEY3"));
    }

    @Test
    public void getObject() {
        map.put("KEY", "VALUE");

        assertEquals("VALUE", map.get("KEY"));
    }

    @Test
    public void getAsString() {
        map.put("KEY", "VALUE");

        assertEquals("VALUE", map.getAsString("KEY"));
    }

    @Test
    public void getAsInteger() {
        map.put("KEY", (int) 1);

        assertEquals(1, (int) map.getAsInt("KEY"));
    }

    @Test
    public void getAsShort() {
        map.put("KEY", (short) 1);

        assertEquals(1, (short) map.getAsShort("KEY"));
    }

    @Test
    public void getAsLong() {
        map.put("KEY", (long) 1);

        assertEquals(1, (long) map.getAsLong("KEY"));
    }

    @Test
    public void getAsFloat() {
        map.put("KEY", 1.f);

        assertEquals(1.f, (float) map.getAsFloat("KEY"), 1e-10);
    }

    @Test
    public void getAsDouble() {
        map.put("KEY", 1.0);

        assertEquals(1.0, (double) map.getAsDouble("KEY"), 1e-10);
    }

    @Test
    public void getAsDate() {
        Date d = new Date();

        map.put("KEY", d);

        assertEquals(d, map.getAsDate("KEY"));
    }

    @Test (expected = ClassCastException.class)
    public void cannotConvert() {
        map.put("KEY", 1.f);

        map.getAsDouble("KEY");
    }

    @Test
    public void getNull() {
        assertEquals(null, map.get("KEY"));
    }

    @Test
    public void getAsNull() {
        assertEquals(null, map.getAsString("KEY"));
    }
}
