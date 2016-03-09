package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Value;

public class TSqlDeclareQuery extends MainQuery {
    private IQuery parent;
    private String variable;
    private IType type;
    private Value value;

    public TSqlDeclareQuery(TSqlQueryRenderer renderer, IQuery parent, String variable, IType type, Value value) {
        super(renderer);

        if (!Identifier.test(variable)) {
            throw new IllegalIdentifierException(variable);
        }

        this.parent = parent;
        this.variable = variable;
        this.type = type;
        this.value = value;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return getRenderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }

    public String getVariable() {
        return variable;
    }

    public IType getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public TSqlDeclareQuery declare(String variable, IType type, Value value) {
        return new TSqlDeclareQuery(getRenderer(), this, variable, type, value);
    }
}
