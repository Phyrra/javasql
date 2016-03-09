package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.join.JoinType;

public class JoinQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private Source source;
	private JoinType type;

    public JoinQuery(IQueryRenderer renderer, IQuery parent, Source source) {
        this.renderer = renderer;
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
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }
	
	public Source getSource() {
		return source;
	}
	
	public JoinType getType() {
		return type;
	}

    public boolean hasType() {
        return type != null;
    }

    public JoinQuery type(JoinType type) {
        this.type = type;
        return this;
    }
	
	public JoinQuery left() {
		this.type = JoinType.LEFT;
		return this;
	}
	
	public JoinQuery right() {
		this.type = JoinType.RIGHT;
		return this;
	}

    public JoinQuery full() {
        this.type = JoinType.FULL;
        return this;
    }
	
	public JoinQuery inner() {
		this.type = JoinType.INNER;
		return this;
	}

	public JoinQuery cross() {
		this.type = JoinType.CROSS;
		return this;
	}

    public JoinQuery leftOuter() {
        this.type = JoinType.LEFT_OUTER;
        return this;
    }

    public JoinQuery rightOuter() {
        this.type = JoinType.RIGHT_OUTER;
        return this;
    }

    public JoinQuery fullOuter() {
        this.type = JoinType.FULL_OUTER;
        return this;
    }
	
	public JoinQueryFinal on(ICondition condition) {
		return new JoinQueryFinal(renderer, this, condition);
	}
}