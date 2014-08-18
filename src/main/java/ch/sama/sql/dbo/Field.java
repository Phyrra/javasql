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
		StringBuilder builder = new StringBuilder();

		if (table != null) {
			builder.append(table);
			builder.append(".");
		}
		
		builder.append(field);
		
		return builder.toString();
	}
}