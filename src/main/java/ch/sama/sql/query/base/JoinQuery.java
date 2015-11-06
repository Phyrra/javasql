package ch.sama.sql.query.base;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

import java.util.ArrayList;
import java.util.List;

public class JoinQuery implements IQuery {
    public enum TYPE {
        LEFT("LEFT"),
        RIGHT("RIGHT"),
        INNER("INNER"),
        OUTER("OUTER"),
        FULL("FULL"),
        CROSS("CROSS");

        private String name;

        TYPE(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static TYPE fromString(String name) {
            switch (name.toUpperCase()) {
                case "LEFT":
                    return LEFT;
                case "RIGHT":
                    return RIGHT;
                case "INNER":
                    return INNER;
                case "OUTER":
                    return OUTER;
                case "FULL":
                    return FULL;
                case "CROSS":
                    return CROSS;
                default:
                    throw new BadSqlException("Unknown Join Type: " + name);
            }
        }
    }

    private IQueryRenderer renderer;
	private IQuery parent;
	private Source source;
	private List<TYPE> types;

    public JoinQuery(IQueryRenderer renderer, IQuery parent, Source source) {
        this.renderer = renderer;
        this.parent = parent;
        this.source = source;
        this.types = new ArrayList<TYPE>();
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
	
	public List<TYPE> getTypes() {
		return types;
	}

    public boolean hasTypes() {
        return !types.isEmpty();
    }

    public JoinQuery type(TYPE type) {
        this.types.add(type);
        return this;
    }
	
	public JoinQuery left() {
		this.types.add(TYPE.LEFT);
		return this;
	}
	
	public JoinQuery right() {
		this.types.add(TYPE.RIGHT);
		return this;
	}
	
	public JoinQuery inner() {
		this.types.add(TYPE.INNER);
		return this;
	}

    public JoinQuery outer() {
        this.types.add(TYPE.OUTER);
        return this;
    }

	public JoinQuery full() {
		this.types.add(TYPE.FULL);
		return this;
	}

	public JoinQuery cross() {
		this.types.add(TYPE.CROSS);
		return this;
	}
	
	public JoinQueryFinal on(ICondition condition) {
		return new JoinQueryFinal(renderer, this, condition);
	}
}