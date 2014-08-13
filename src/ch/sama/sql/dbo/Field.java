package ch.sama.sql.dbo;

public class Field {
	private String table;
	private String field;
	
	public Field(String field) {
		this.field = field;
	}
	
	public Field(String table, String field) {
		this.table = table;
		this.field = field;
	}
	
	public String toString() {
		String s = "";
		if (table != null) {
			s += table + ".";
		}
		
		return s + field;
	}
}