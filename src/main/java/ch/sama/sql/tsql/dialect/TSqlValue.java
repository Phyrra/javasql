package ch.sama.sql.tsql.dialect;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.helper.Value;

public class TSqlValue extends Value {
    private IQueryRenderer renderer = new TSqlQueryRenderer();
	private String value;

    public String nullValue() {
        return "NULL";
    }

    public String allValue() {
        return "*";
    }
	
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

    public TSqlValue() { }
	
	public TSqlValue(String s) {
		super(s);
        value = "'" + s.replace("'", "''") + "'";
	}

    public static TSqlValue plain(String s) { // This is "unsafe"!
        TSqlValue val = new TSqlValue();
        val.value = s;

        return val;
    }
	
	public TSqlValue(Integer i) {
		super(i);
        value = i.toString();
	}
	
	public TSqlValue(Float f) {
		super(f);
        value = f.toString();
	}
	
	public TSqlValue(Double d) {
		super(d);
        value = d.toString();
	}
	
	public TSqlValue(Date d) {
        super(d);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		value = "CONVERT(datetime, '" + df.format(d) + "', 21)";
	}
	
	public TSqlValue(Field f) {
        super(f);

		value = f.getString();
	}

    public TSqlValue(Table t) {
        super(t);

        value = t.getString() + ".*";
    }
	
	public TSqlValue(IQuery query) {
		super(query);

        value = "(\n" + query.getSql(renderer) + "\n)";
	}
	
	public TSqlValue(Function f) {
		super(f);

        value = f.getString();
	}

    public TSqlValue(Value.VALUE v) {
        super(v);

        value = fromValue(v);
    }
}
