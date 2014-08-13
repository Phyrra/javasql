package ch.sama.sql.query.dialect;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.query.helper.Value;

public class SqlValue implements Value {
	private String value;
	
	public String toString() {
		return value;
	}
	
	public SqlValue(String s) {
		value = "'" + s + "'";
	}
	
	public SqlValue(Integer i) {
		value = i.toString();
	}
	
	public SqlValue(Float f) {
		value = f.toString();
	}
	
	public SqlValue(Double d) {
		value = d.toString();
	}
	
	public SqlValue(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		value = "CONVERT(datetime, '" + df.format(d) + "', 21)";
	}
}
