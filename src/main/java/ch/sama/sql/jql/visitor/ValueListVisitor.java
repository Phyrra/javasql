package ch.sama.sql.jql.visitor;

import ch.sama.sql.jql.antlr.JqlBaseVisitor;
import ch.sama.sql.jql.antlr.JqlParser;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

class ValueListVisitor extends JqlBaseVisitor<List<Value>> {
    private ValueVisitor valueVisitor;

    public ValueListVisitor(ValueVisitor visitor) {
        this.valueVisitor = visitor;
    }

    @Override
    public List<Value> visitValueList(JqlParser.ValueListContext ctx) {
        List<Value> values = new ArrayList<Value>();

        values.add(valueVisitor.visit(ctx.value()));

        if (ctx.valueList() != null) {
            values.addAll(visit(ctx.valueList()));
        }

        return values;
    }

    @Override
    public List<Value> visitConstantValueList(JqlParser.ConstantValueListContext ctx) {
        List<Value> values = new ArrayList<Value>();

        values.add(valueVisitor.visit(ctx.constantValue()));

        if (ctx.constantValueList() != null) {
            values.addAll(visit(ctx.constantValueList()));
        }

        return values;
    }
}
