package ch.sama.sql.dbo;

public class Table {
	private String table;
	private String alias;

	public Table(String table) {
		this.table = table;
	}
	
	public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(table);

        if (alias != null) {
            builder.append(" AS ");
            builder.append(alias);
        }

		return builder.toString();
	}

    public Table as(String alias) {
        this.alias = alias;
        return this;
    }
}