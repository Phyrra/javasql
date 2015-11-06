package ch.sama.sql.query.helper.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CustomCondition implements ICondition {
    private Supplier<String> renderer;

    public CustomCondition(Supplier<String> renderer) {
        this.renderer = renderer;
    }

    public Supplier<String> getRenderer() {
        return renderer;
    }
    
    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
