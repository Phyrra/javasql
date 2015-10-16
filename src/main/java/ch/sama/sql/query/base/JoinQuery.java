package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class JoinQuery implements IQuery {
    public enum TYPE {
        LEFT("LEFT"),
        RIGHT("RIGHT"),
        INNER("INNER"),
        FULL("FULL"),
        CROSS("CROSS");

        private String name;

        TYPE(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private IQueryRenderer renderer;
	private IQuery parent;
	private Source source;
	private TYPE type;

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
		return new JoinQueryFinal(renderer, this, condition);
	}
}