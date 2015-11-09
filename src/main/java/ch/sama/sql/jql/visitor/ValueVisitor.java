package ch.sama.sql.jql.visitor;

import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.jql.antlr.JqlBaseVisitor;
import ch.sama.sql.jql.antlr.JqlParser;
import ch.sama.sql.query.helper.Value;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

class ValueVisitor extends JqlBaseVisitor<Value> {
    private IValueFactory valueFactory;
    private ValueListVisitor valueListVisitor;

    public ValueVisitor(IValueFactory valueFactory) {
        this.valueFactory = valueFactory;
        this.valueListVisitor = new ValueListVisitor(this);
    }

    private String cleanStringLiteral(TerminalNode sl) {
        String s = sl.getText();

        return s.substring(1, s.length() - 1).replace("\\'", "'");
    }

    @Override
    public Value visitValue(JqlParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitInterpolatedValue(JqlParser.InterpolatedValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitConstantValue(JqlParser.ConstantValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitField(JqlParser.FieldContext ctx) {
        String name = ctx.sqlIdentifier().Identifier().getText();

        return valueFactory.field(name);
    }

    @Override
    public Value visitStringValue(JqlParser.StringValueContext ctx) {
        String value = cleanStringLiteral(ctx.StringLiteral());

        return valueFactory.string(value);
    }

    @Override
    public Value visitNumericValue(JqlParser.NumericValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitIntegerValue(JqlParser.IntegerValueContext ctx) {
        int value = Integer.parseInt(ctx.IntegerConstant().getText());

        return valueFactory.numeric(value);
    }

    @Override
    public Value visitFloatingValue(JqlParser.FloatingValueContext ctx) {
        double value = Double.parseDouble(ctx.FloatingConstant().getText());

        return valueFactory.numeric(value);
    }

    @Override
    public Value visitFunctionValue(JqlParser.FunctionValueContext ctx) {
        String fnc = ctx.Identifier().getText();

        List<Value> params;
        if (ctx.valueList() == null) {
            params = new ArrayList<Value>();
        } else {
            params = valueListVisitor.visit(ctx.valueList());
        }

        return valueFactory.function(fnc, params.toArray(new Value[params.size()]));
    }

    // Expressions

    @Override
    public Value visitExpression(JqlParser.ExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAdditiveExpression(JqlParser.AdditiveExpressionContext ctx) {
        Value rhs = visit(ctx.multiplicativeExpression());

        if (ctx.additiveOperator() != null) {
            Value lhs = visit(ctx.additiveExpression());
            String op = ctx.additiveOperator().getText();

            return valueFactory.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitMultiplicativeExpression(JqlParser.MultiplicativeExpressionContext ctx) {
        Value rhs = visit(ctx.negateExpression());

        if (ctx.multiplicativeOperator() != null) {
            Value lhs = visit(ctx.multiplicativeExpression());
            String op = ctx.multiplicativeOperator().getText();

            return valueFactory.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitNegateExpression(JqlParser.NegateExpressionContext ctx) {
        Value val = visit(ctx.primaryExpression());

        if (ctx.negateOperator() != null) {
            String op = ctx.negateOperator().getText();

            // TODO: Some sort of "combine"?
            return valueFactory.plain(op + val.getValue());
        }

        return val;
    }

    @Override
    public Value visitPrimaryExpression(JqlParser.PrimaryExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitParExpression(JqlParser.ParExpressionContext ctx) {
        Value val = visit(ctx.expression());

        return valueFactory.bracket(val);
    }
}
