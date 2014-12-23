package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.StringGetter;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

class ValueVisitor extends SqlBaseVisitor<Value> {
    private IValueFactory fac;
    private ValueListVisitor listVisitor;

    public ValueVisitor(IValueFactory fac) {
        this.fac = fac;
        this.listVisitor = new ValueListVisitor(this);
    }

    @Override
    public Value visitValue(SqlParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAllFields(SqlParser.AllFieldsContext ctx) {
        return fac.value(Value.VALUE.ALL);
    }

    @Override
    public Value visitAllTableFields(SqlParser.AllTableFieldsContext ctx) {
        String table = ctx.sqlIdentifier().Identifier().getText();
        return fac.table(table);
    }

    @Override
    public Value visitExpression(SqlParser.ExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAdditiveExpression(SqlParser.AdditiveExpressionContext ctx) {
        Value rhs = visit(ctx.multiplicativeExpression());

        if (ctx.additiveOperator() != null) {
            Value lhs = visit(ctx.additiveExpression());
            String op = ctx.additiveOperator().getText();

            switch (op) {
                case "+":
                    return fac.plain(lhs.getValue() + " + " + rhs.getValue());
                case "-":
                    return fac.plain(lhs.getValue() + " - " + rhs.getValue());
                default:
                    throw new SqlGrammarException("Unknown operator: " + op, ctx);
            }
        }

        return rhs;
    }

    @Override
    public Value visitMultiplicativeExpression(SqlParser.MultiplicativeExpressionContext ctx) {
        Value rhs = visit(ctx.negateExpression());

        if (ctx.multiplicativeOperator() != null) {
            Value lhs = visit(ctx.multiplicativeExpression());
            String op = ctx.multiplicativeOperator().getText();

            switch (op) {
                case "*":
                    return fac.plain(lhs.getValue() + " * " + rhs.getValue());
                case "/":
                    return fac.plain(lhs.getValue() + " / " + rhs.getValue());
                default:
                    throw new SqlGrammarException("Unknown operator: " + op, ctx);
            }
        }

        return rhs;
    }

    @Override
    public Value visitNegateExpression(SqlParser.NegateExpressionContext ctx) {
        Value val = visit(ctx.primaryExpression());

        if (ctx.negateOperator() != null) {
            String op = ctx.negateOperator().getText();

            switch (op) {
                case "-":
                    return fac.plain("-" + val.getValue());
                default:
                    throw new SqlGrammarException("Unknown operator: " + op, ctx);
            }
        }

        return val;
    }

    @Override
    public Value visitPrimaryExpression(SqlParser.PrimaryExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitParExpression(SqlParser.ParExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Value visitPrimaryValue(SqlParser.PrimaryValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAliasedValue(SqlParser.AliasedValueContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();
        Value value = visit(ctx.expression());

        value.as(alias);

        return value;
    }

    @Override
    public Value visitField(SqlParser.FieldContext ctx) {
        String field = ctx.sqlIdentifier().Identifier().getText();
        return fac.field(field);
    }

    @Override
    public Value visitTableField(SqlParser.TableFieldContext ctx) {
        String table = ctx.sqlIdentifier(0).Identifier().getText();
        String field = ctx.sqlIdentifier(1).Identifier().getText();
        return fac.field(table, field);
    }

    @Override
    public Value visitStringValue(SqlParser.StringValueContext ctx) {
        String s = StringGetter.get(ctx.StringLiteral());
        return fac.string(s);
    }

    @Override
    public Value visitNumericValue(SqlParser.NumericValueContext ctx) {
        String s = ctx.getText();
        if (ctx.IntegerConstant() != null) {
            int i = Integer.parseInt(s);
            return fac.numeric(i);
        } else if (ctx.FloatingConstant() != null) {
            double d = Double.parseDouble(s);
            return fac.numeric(d);
        } else {
            // This should never happen, unless someone defined a bad grammar :)
            throw new SqlGrammarException("Unknown Numeric Value", ctx);
        }
    }

    @Override
    public Value visitFunctionValue(SqlParser.FunctionValueContext ctx) {
        String fnc = ctx.Identifier().getText();

        List<Value> params;
        if (ctx.argumentList() == null) {
            params = new ArrayList<Value>();
        } else {
            params = listVisitor.visit(ctx.argumentList());
        }

        return fac.function(fnc, params.toArray(new Value[params.size()]));
    }
}