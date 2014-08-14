package ch.sama.sql;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.dialect.SqlQuery;
import ch.sama.sql.query.dialect.SqlValue;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class QueryTest {
	public static void main(String[] args) {
		Condition c = Condition.and(
				Condition.eq(new SqlValue(new Field("FIELD1")), new SqlValue(new Date())),
				Condition.or(
						Condition.neq(new SqlValue(new Field("FIELD2")), new SqlValue(3.32)),
						Condition.like(new SqlValue(new Field("FIELD3")), new SqlValue("test3"))
				)
		);
		
		Condition c2 = Condition.not(
				Condition.eq(new SqlValue(new Field("FIELD4")), new SqlValue(1))
		);
		
		Condition c3 = Condition.eq(new SqlValue(1), new SqlValue(1));
		
		String s = new SqlQuery()
						.select(new Field("TABLE1.FIELD1").as("TEST"), new Field("TABLE1.FIELD2"), new Field("TABLE2.FIELD1"))
						.from(new Table("TABLE1"), new Table("TABLE2"))
						.join(new Table("TABLE3")).left().as("T3").on(c2)
						.join(new Table("TABLE4")).on(c3)
						.where(c)
						.order(Order.asc(new Field("TABLE1.FIELD1"), new Field("TABLE2.FIELD2")))
						.toString();
						
		System.out.println(s);
	}
}