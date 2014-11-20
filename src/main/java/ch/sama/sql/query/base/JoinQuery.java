package ch.sama.sql.query.base;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Source;

public class JoinQuery implements IQuery {
	private IQuery parent;
	private Source source;
	private String type;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Source getSource() {
		return source;
	}
	
	public String getType() {
		return type;
	}
	
	public JoinQuery(IQuery parent, Source source) {
		this.parent = parent;
		this.source = source;
		this.type = null;
	}
	
	public JoinQuery left() {
		this.type = "LEFT";
		return this;
	}
	
	public JoinQuery right() {
		this.type = "RIGHT";
		return this;
	}
	
	// Could also add inner/outer/cross and so on..
	//	Since I never use them, I didn't :>
	
	public JoinQueryFinal on(Condition condition) {
		return new JoinQueryFinal(this, condition);
	}

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}