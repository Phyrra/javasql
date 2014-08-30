package ch.sama.sql;

import java.util.*;

import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class QueryTest {
	public static void main(String[] args) {
        QueryFactory fac = new TSqlQueryFactory();

		Condition c = Condition.and(
				Condition.eq(fac.field("FIELD1"), fac.date(new Date())),
				Condition.or(
						Condition.neq(fac.field("FIELD2"), fac.numeric(3.32)),
						Condition.like(fac.field("FIELD3"), fac.string("test3"))
				)
		);
		
		Condition c2 = Condition.not(
				Condition.eq(fac.field("FIELD4"), fac.numeric(1))
		);
		
		Condition c3 = Condition.eq(fac.numeric(1), fac.numeric(1));

        String s = fac.create()
                        .select(fac.field("TABLE1", "FIELD1").as("TEST"), fac.field("TABLE1", "FIELD2"), fac.string("Hello").as("GREETING"))
                        .from(fac.table("TABLE1"), fac.table("TABLE2"))
                        .join(fac.table("TABLE3").as("T3")).left().on(c2)
                        .join(fac.table("TABLE4")).on(c3)
                        .where(c)
                        .order(Order.asc(fac.field("TABLE1", "FIELD1"), fac.field("TABLE2", "FIELD2")))
                        .toString();
						
		System.out.println(s);
	}
}