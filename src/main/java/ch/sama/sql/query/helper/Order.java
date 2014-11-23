package ch.sama.sql.query.helper;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.UnknownOrderException;
import ch.sama.sql.query.helper.order.AscOrder;
import ch.sama.sql.query.helper.order.DescOrder;

import java.util.*;

public class Order {
	public static AscOrder asc(Value... v) {
		return new AscOrder(v);
	}
	
	public static DescOrder desc(Value... v) {
		return new DescOrder(v);
	}
}