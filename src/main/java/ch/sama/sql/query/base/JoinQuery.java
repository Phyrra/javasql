package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class JoinQuery implements IQuery {
    public enum TYPE {
        LEFT,
        RIGHT,
        INNER,
        FULL,
        CROSS
    }

    private IQueryFactory factory;
	private IQuery parent;
	private Source source;
	private TYPE type;

    public JoinQuery(IQueryFactory factory, IQuery parent, Source source) {
        this.factory = factory;
        this.parent = parent;
        this.source = source;
        this.type = null;
    }
    
    @Override
	public IQuery getParent() {
		return parent;
	}

    @Override
    public String getSql() {
        return factory.renderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }
	
	public Source getSource() {
		return source;
	}
	
	public TYPE getType() {
		return type;
	}
	
	public JoinQuery left() {
		this.type = TYPE.LEFT;
		return this;
	}
	
	public JoinQuery right() {
		this.type = TYPE.RIGHT;
		return this;
	}
	
	public JoinQuery inner() {
		this.type = TYPE.INNER;
		return this;
	}

	public JoinQuery full() {
		this.type = TYPE.FULL;
		return this;
	}

	public JoinQuery cross() {
		this.type = TYPE.CROSS;
		return this;
	}
	
	public JoinQueryFinal on(ICondition condition) {
		return factory.joinFinal(this, condition);
	}
}