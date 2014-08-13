package ch.sama.sql;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.Value;
import ch.sama.sql.helper.Condition;
import ch.sama.sql.helper.Order;
import ch.sama.sql.query.Query;

public class QueryTest {
	public static void main(String[] args) {
		String s = new Query()
						.select(new Field("TABLE1.FIELD1"), new Field("TABLE1.FIELD2"), new Field("TABLE2.FIELD1"))
						.from(new Table("TABLE1"), new Table("TABLE2"))
						.where(Condition.eq("TABLE1.FIELD1", new Value(23)))
						.order(Order.asc(new Field("TABLE1.FIELD1"), new Field("TABLE2.FIELD2")))
						.getSql();
						
		System.out.println(s);
		
		Condition c = Condition.and(Condition.eq("FIELD1", new Value(new Date())), Condition.or(Condition.neq("FIELD2", new Value(3.32)), Condition.like("FIELD3", new Value("test3"))));
		
		System.out.println(c.getString());
	}
}