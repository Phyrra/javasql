package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.join.JoinType;

public class TSqlJoinQuery extends JoinQuery {
    private TSqlQueryRenderer renderer;

    public TSqlJoinQuery(TSqlQueryRenderer renderer, IQuery parent, Source source) {
        super(renderer, parent, source);

        this.renderer = renderer;
    }

    @Override
    public TSqlJoinQuery type(JoinType type) {
        super.type(type);
        return this;
    }

    @Override
    public TSqlJoinQuery left() {
        super.left();
        return this;
    }

    @Override
    public TSqlJoinQuery right() {
        super.right();
        return this;
    }

    @Override
    public TSqlJoinQuery full() {
        super.full();
        return this;
    }

    @Override
    public TSqlJoinQuery inner() {
        super.inner();
        return this;
    }

    @Override
    public TSqlJoinQuery cross() {
        super.cross();
        return this;
    }

    @Override
    public TSqlJoinQuery leftOuter() {
        super.leftOuter();
        return this;
    }

    @Override
    public TSqlJoinQuery rightOuter() {
        super.rightOuter();
        return this;
    }

    @Override
    public TSqlJoinQuery fullOuter() {
        super.fullOuter();
        return this;
    }

    @Override
    public TSqlJoinQueryFinal on(ICondition c) {
        return new TSqlJoinQueryFinal(renderer, this, c);
    }
}
