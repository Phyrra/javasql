package ch.sama.sql.query.base;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Source;

public class JoinQuery implements IQuery {
    public enum TYPE {
        LEFT,
        RIGHT
    }

    IQueryRenderer renderer;
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
	
	public JoinQueryFinal on(Condition condition) {
		return new JoinQueryFinal(renderer, this, condition);
	}

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}