package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.helper.Source;

public class TSqlSource extends Source {
    private IQueryRenderer renderer = new TSqlQueryRenderer();
    private String value;

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();

        builder.append(value);

        if (getAlias() != null) {
            builder.append(" AS [");
            builder.append(getAlias());
            builder.append("]");
        }

        return builder.toString();
    }


    public TSqlSource(Table table) {
        super(table);

        this.value = table.getString();
    }

    public TSqlSource(IQuery query) {
        super(query);

        this.value = "(\n" + query.getSql(renderer) + "\n)";
    }
}
