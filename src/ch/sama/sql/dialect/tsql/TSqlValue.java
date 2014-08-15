package ch.sama.sql.dialect.tsql;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Value;

public class TSqlValue implements Value {
	private String value;
	
	public String toString() {
		return value;
	}
	
	public TSqlValue(String s) {
		value = "'" + s.replace("'", "''") + "'";
	}
	
	public TSqlValue(Integer i) {
		value = i.toString();
	}
	
	public TSqlValue(Float f) {
		value = f.toString();
	}
	
	public TSqlValue(Double d) {
		value = d.toString();
	}
	
	public TSqlValue(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		value = "CONVERT(datetime, '" + df.format(d) + "', 21)";
	}
	
	public TSqlValue(Field f) {
		value = f.toString();
	}
}
