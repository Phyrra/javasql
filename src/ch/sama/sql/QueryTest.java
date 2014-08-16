package ch.sama.sql;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQuery;
import ch.sama.sql.dialect.tsql.TSqlValue;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class QueryTest {
	public static void main(String[] args) {
		Condition c = Condition.and(
				Condition.eq(new TSqlValue(new Field("FIELD1")), new TSqlValue(new Date())),
				Condition.or(
						Condition.neq(new TSqlValue(new Field("FIELD2")), new TSqlValue(3.32)),
						Condition.like(new TSqlValue(new Field("FIELD3")), new TSqlValue("test3"))
				)
		);
		
		Condition c2 = Condition.not(
				Condition.eq(new TSqlValue(new Field("FIELD4")), new TSqlValue(1))
		);
		
		Condition c3 = Condition.eq(new TSqlValue(1), new TSqlValue(1));
		
		String s = new TSqlQuery()
						.select(new TSqlValue(new Field("TABLE1.FIELD1")).as("TEST"), new TSqlValue(new Field("TABLE1.FIELD2")), new TSqlValue(new Field("TABLE2.FIELD1")))
						.from(new Table("TABLE1"), new Table("TABLE2"))
						.join(new Table("TABLE3")).left().as("T3").on(c2)
						.join(new Table("TABLE4")).on(c3)
						.where(c)
						.order(Order.asc(new TSqlValue(new Field("TABLE1", "FIELD1")), new TSqlValue(new Field("TABLE2", "FIELD2"))))
						.toString();
						
		System.out.println(s);
	}
}