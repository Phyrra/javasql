package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

class ValueListVisitor extends SqlBaseVisitor<List<Value>> {
    private ValueVisitor valueVisitor;

    public ValueListVisitor(ValueVisitor visitor) {
        this.valueVisitor = visitor;
    }

    @Override
    public List<Value> visitValueList(SqlParser.ValueListContext ctx) {
        List<Value> values = new ArrayList<Value>();

        values.add(valueVisitor.visit(ctx.value()));

        if (ctx.valueList() != null) {
            values.addAll(visit(ctx.valueList()));
        }

        return values;
    }

    @Override
    public List<Value> visitArgumentList(SqlParser.ArgumentListContext ctx) {
        List<Value> values = new ArrayList<Value>();

        values.add(valueVisitor.visit(ctx.expression()));

        if (ctx.argumentList() != null) {
            values.addAll(visit(ctx.argumentList()));
        }

        return values;
    }
}
