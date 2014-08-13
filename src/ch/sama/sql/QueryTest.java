package ch.sama.sql;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.dialect.SqlQuery;
import ch.sama.sql.query.dialect.SqlValue;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Value;

public class QueryTest {
	public static void main(String[] args) {
		Condition c = Condition.and(
				Condition.eq("FIELD1", new SqlValue(new Date())),
				Condition.or(
						Condition.neq("FIELD2", new SqlValue(3.32)),
						Condition.like("FIELD3", new SqlValue("test3"))
				)
		);
		
		String s = new SqlQuery()
						.select(new Field("TABLE1.FIELD1"), new Field("TABLE1.FIELD2"), new Field("TABLE2.FIELD1"))
						.from(new Table("TABLE1"), new Table("TABLE2"))
						.where(c)
						.order(Order.asc(new Field("TABLE1.FIELD1"), new Field("TABLE2.FIELD2")))
						.toString();
						
		System.out.println(s);
	}
}