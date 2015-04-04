package ch.sama.sql.query.standard;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public abstract class QueryFactory implements IQueryFactory {
    @Override
    public Query query() {
        return new Query(this);
    }

    @Override
    public Query query(IQuery parent) {
        return new Query(this, parent);
    }

    @Override
    public SelectQuery select(IQuery parent, Value[] v) {
        return new SelectQuery(this, parent, v);
    }

    @Override
    public FromQuery from(IQuery parent, Source[] s) {
        return new FromQuery(this, parent, s);
    }

    @Override
    public JoinQuery join(IQuery parent, Source s) {
        return new JoinQuery(this, parent, s);
    }

    @Override
    public JoinQueryFinal joinFinal(JoinQuery parent, ICondition c) {
        return new JoinQueryFinal(this, parent, c);
    }

    @Override
    public WhereQuery where(IQuery parent, ICondition c) {
        return new WhereQuery(this, parent, c);
    }

    @Override
    public OrderQuery order(IQuery parent, IOrder[] orders) {
        return new OrderQuery(this, parent, orders);
    }

    @Override
    public DeleteQuery delete(IQuery parent) {
        return new DeleteQuery(this, parent);
    }

    @Override
    public DeleteQueryIM deleteFrom(DeleteQuery parent, Table t) {
        return new DeleteQueryIM(this, parent, t);
    }

    @Override
    public DeleteQueryFinal deleteWhere(DeleteQueryIM parent, ICondition c) {
        return new DeleteQueryFinal(this, parent, c);
    }

    @Override
    public InsertQuery insert(IQuery parent) {
        return new InsertQuery(this, parent);
    }

    @Override
    public InsertQueryIM insertInto(InsertQuery parent, Table t) {
        return new InsertQueryIM(this, parent, t);
    }

    @Override
    public InsertQueryFinal insertColumns(InsertQueryIM parent, Field[] f) {
        return new InsertQueryFinal(this, parent, f);
    }

    @Override
    public UpdateQuery update(IQuery parent, Table t) {
        return new UpdateQuery(this, parent, t);
    }

    @Override
    public UpdateQueryIM updateField(IQuery parent, Field f, Value v) {
        return new UpdateQueryIM(this, parent, f, v);
    }

    @Override
    public UpdateQueryFinal updateWhere(UpdateQueryIM parent, ICondition c) {
        return new UpdateQueryFinal(this, parent, c);
    }
}
