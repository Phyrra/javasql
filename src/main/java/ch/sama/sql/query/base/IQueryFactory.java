package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public interface IQueryFactory {
    public IQueryRenderer renderer();

    public Query query();
    public Query query(IQuery parent);
    public SelectQuery select(IQuery parent, Value[] v);
    public FromQuery from(IQuery parent, Source[] s);
    public JoinQuery join(IQuery parent, Source s);
    public JoinQueryFinal joinFinal(JoinQuery parent, ICondition c);
    public WhereQuery where(IQuery parent, ICondition c);
    public OrderQuery order(IQuery parent, IOrder[] o);
    public DeleteQuery delete(IQuery parent);
    public DeleteQueryIM deleteFrom(DeleteQuery parent, Table t);
    public DeleteQueryFinal deleteWhere(DeleteQueryIM parent, ICondition c);
    public InsertQuery insert(IQuery parent);
    public InsertQueryIM insertInto(InsertQuery parent, Table t);
    public InsertQueryFinal insertColumns(InsertQueryIM parent, Field[] f);
    public UpdateQuery update(IQuery parent, Table t);
    public UpdateQueryIM updateField(IQuery parent, Field f, Value v);
    public UpdateQueryFinal updateWhere(UpdateQueryIM parent, ICondition c);
}
