import java.util.*;
import java.text.*;

public class Value {
	private String value;
	
	public String getString() {
		return value;
	}
	
	public Value(String s) {
		value = "'" + s + "'";
	}
	
	public Value(Integer i) {
		value = i.toString();
	}
	
	public Value(Float f) {
		value = f.toString();
	}
	
	public Value(Double d) {
		value = d.toString();
	}
	
	public Value(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		value = "CONVERT(datetime, '" + df.format(d) + "', 21)";
	}
}