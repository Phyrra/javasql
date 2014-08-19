package ch.sama.sql.query.helper;

import java.util.List;

public interface OrderParser {
	public String asc(List<Value> values);
    public String desc(List<Value> values);
}
