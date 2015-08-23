package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

import java.util.Arrays;
import java.util.List;

public class InsertQueryFinal implements IQuery {
    private IQueryCreator creator;
	private InsertQueryIM parent;
    private List<Field> fields;

    public InsertQueryFinal(IQueryCreator creator, InsertQueryIM parent, Field[] fields) {
        this.creator = creator;
        this.parent = parent;
        this.fields = Arrays.asList(fields);
    }

    @Override
	public IQuery getParent() {
		return parent;
	}

    @Override
    public String getSql() {
        return creator.renderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        return this.parent.chainTo(query);
    }
    
    public List<Field> getFields() {
        return fields;
    }
    
    public Table getTable() {
        return parent.getTable();
    }
    
    public SelectQuery select(Value... values) {
        return creator.selectQuery(this, values);
    }

    public InsertQueryValues values(Value... values) {
        return creator.insertQueryValues(this, values);
    }

    public UnionQuery union(IQuery... queries) {
        return creator.unionQuery(this, queries);
    }
}