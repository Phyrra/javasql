package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class JoinQuery implements IQuery {
    public enum TYPE {
        LEFT,
        RIGHT
    }

    private IQueryRenderer renderer;
	private IQuery parent;
	private Source source;
	private TYPE type;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Source getSource() {
		return source;
	}
	
	public TYPE getType() {
		return type;
	}
	
	public JoinQuery(IQueryRenderer renderer, IQuery parent, Source source) {
        this.renderer = renderer;
		this.parent = parent;
		this.source = source;
		this.type = null;
	}
	
	public JoinQuery left() {
		this.type = TYPE.LEFT;
		return this;
	}
	
	public JoinQuery right() {
		this.type = TYPE.RIGHT;
		return this;
	}
	
	// Could also add inner/outer/cross and so on..
	//	Since I never use them, I didn't :>
	
	public JoinQueryFinal on(ICondition condition) {
		return new JoinQueryFinal(renderer, this, condition);
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
}