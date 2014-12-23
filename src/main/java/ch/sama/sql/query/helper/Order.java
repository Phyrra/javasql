package ch.sama.sql.query.helper;

import ch.sama.sql.query.helper.order.AscOrder;
import ch.sama.sql.query.helper.order.DefOrder;
import ch.sama.sql.query.helper.order.DescOrder;

public class Order {
	public static DefOrder def(Value... v) {
		return new DefOrder(v);
	}

	public static AscOrder asc(Value... v) {
		return new AscOrder(v);
	}
	
	public static DescOrder desc(Value... v) {
		return new DescOrder(v);
	}
}