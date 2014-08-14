package ch.sama.sql.dbo;

public class Field {
	private String table;
	private String field;
	private String alias;
	
	public Field(String field) {
		this.field = field;
	}
	
	public Field(String table, String field) {
		this.table = table;
		this.field = field;
		this.alias = null;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		if (table != null) {
			builder.append(table);
			builder.append(".");
		}
		
		builder.append(field);
		
		if (alias != null) {
			builder.append(" AS ");
			builder.append(alias);
		}
		
		return builder.toString();
	}
	
	public Field as(String alias) {
		this.alias = alias;
		return this;
	}
}