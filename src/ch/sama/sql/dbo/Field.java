package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;

public class Field {
	private String table;
	private String field;
	private String alias;
	private IQuery query;
	private Value value;
	private Function function;
	
	public Field(String field) {
		this.field = field;
	}
	
	public Field(String table, String field) {
		this.table = table;
		this.field = field;
	}
	
	public Field(IQuery query) {
		this.query = query;
	}
	
	public Field(Value value) {
		this.value = value;
	}
	
	public Field(Function function) {
		this.function = function;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (table != null) {
			builder.append(table);
			builder.append(".");
			builder.append(field);
		} else if (query != null) {
			builder.append("(\n");
			builder.append(query.toString());
			builder.append("\n)");
		} else if (value != null) {
			builder.append(value.toString());
		} else if (function != null) {
			builder.append(function.toString());
		} else {
			builder.append(field);
		}
		
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