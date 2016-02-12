package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.jpa.JpaUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapObjectTest {
    private static double EPS = 1e-10;

    @Test
    public void mapToObject() throws IllegalAccessException, InstantiationException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sValue", "string");
        map.put("iValue", 1);
        map.put("dValue", 1.1);

        JpaUtil util = new JpaUtil();
        TestObject obj = util.toObject(map, TestObject.class);

        assertEquals("string", obj.getStringVal());
        assertEquals(1, obj.getIntVal());
        assertEquals(1.1, obj.getDoubleVal(), EPS);
    }

    @Test
    public void objectToMap() throws IllegalAccessException {
        TestObject obj = new TestObject();
        obj.setStringVal("string");
        obj.setIntVal(1);
        obj.setDoubleVal(1.1);

        JpaUtil util = new JpaUtil();
        Map<String, Object> map = util.toMap(obj);

        assertEquals("string", map.get("sValue"));
        assertEquals(1, map.get("iValue"));
        assertEquals(1.1, map.get("dValue"));
    }
}
