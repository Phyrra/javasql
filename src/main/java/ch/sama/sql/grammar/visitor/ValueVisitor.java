package ch.sama.sql.grammar.visitor;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.helper.StringGetter;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.tsql.type.TYPE;

import java.util.ArrayList;
import java.util.List;

class ValueVisitor extends SqlBaseVisitor<Value> {
    private IValueFactory value;
    private ValueListVisitor listVisitor;

    public ValueVisitor(IQueryFactory fac) {
        value = fac.value();
        listVisitor = new ValueListVisitor(this);
    }

    @Override
    public Value visitValue(SqlParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAllFields(SqlParser.AllFieldsContext ctx) {
        return value.value(Value.VALUE.ALL);
    }

    @Override
    public Value visitAllTableFields(SqlParser.AllTableFieldsContext ctx) {
        List<SqlParser.SqlIdentifierContext> identifiers = ctx.table().sqlIdentifier();
        
        if (identifiers.size() > 1) {
            return value.table(new Table(identifiers.get(0).Identifier().getText(), identifiers.get(1).Identifier().getText()));
        } else {
            return value.table(new Table(identifiers.get(0).Identifier().getText()));
        }
    }
    
    @Override
    public Value visitArgument(SqlParser.ArgumentContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitDataType(SqlParser.DataTypeContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitIntType(SqlParser.IntTypeContext ctx) {
        return value.type(TYPE.INT_TYPE);
    }
    
    @Override
    public Value visitFloatType(SqlParser.FloatTypeContext ctx) {
        return value.type(TYPE.FLOAT_TYPE);
    }
    
    @Override
    public Value visitDatetimeType(SqlParser.DatetimeTypeContext ctx) {
        return value.type(TYPE.DATETIME_TYPE);
    }
    
    @Override
    public Value visitVarcharType(SqlParser.VarcharTypeContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitVarcharTypeIntMax(SqlParser.VarcharTypeIntMaxContext ctx) {
        int max = Integer.parseInt(ctx.IntegerConstant().getText());
        
        return value.type(TYPE.VARCHAR_TYPE(max));
    }
    
    @Override
    public Value visitVarcharTypeMax(SqlParser.VarcharTypeMaxContext ctx) {
        return value.type(TYPE.VARCHAR_MAX_TYPE);
    }
    
    @Override
    public Value visitVarcharTypeNoMax(SqlParser.VarcharTypeNoMaxContext ctx) {
        return value.type(TYPE.VARCHAR_TYPE);
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

            return value.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitMultiplicativeExpression(SqlParser.MultiplicativeExpressionContext ctx) {
        Value rhs = visit(ctx.negateExpression());

        if (ctx.multiplicativeOperator() != null) {
            Value lhs = visit(ctx.multiplicativeExpression());
            String op = ctx.multiplicativeOperator().getText();

            return value.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitNegateExpression(SqlParser.NegateExpressionContext ctx) {
        Value val = visit(ctx.primaryExpression());

        if (ctx.negateOperator() != null) {
            String op = ctx.negateOperator().getText();

            // TODO: Some sort of "combine"?
            return value.plain(op + val.getValue());
        }

        return val;
    }

    @Override
    public Value visitPrimaryExpression(SqlParser.PrimaryExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitParExpression(SqlParser.ParExpressionContext ctx) {
        Value val = visit(ctx.expression());
        
        return value.bracket(val);
    }

    @Override
    public Value visitPrimaryValue(SqlParser.PrimaryValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAliasedValue(SqlParser.AliasedValueContext ctx) {
        String alias = ctx.sqlIdentifier().Identifier().getText();
        Value value = visit(ctx.expression());

        return value.as(alias);
    }

    @Override
    public Value visitField(SqlParser.FieldContext ctx) {
        String field = ctx.sqlIdentifier().Identifier().getText();
        return value.field(field);
    }

    @Override
    public Value visitTableField(SqlParser.TableFieldContext ctx) {
        Table table;
        List<SqlParser.SqlIdentifierContext> identifiers = ctx.table().sqlIdentifier();
        
        if (identifiers.size() > 1) {
            table = new Table(identifiers.get(0).Identifier().getText(), identifiers.get(1).Identifier().getText());
        } else {
            table = new Table(identifiers.get(0).Identifier().getText());
        }
        
        String field = ctx.sqlIdentifier().Identifier().getText();
        
        return value.field(table, field);
    }

    @Override
    public Value visitStringValue(SqlParser.StringValueContext ctx) {
        String s = StringGetter.get(ctx.StringLiteral());
        return value.string(s);
    }

    @Override
    public Value visitNumericValue(SqlParser.NumericValueContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitIntegerValue(SqlParser.IntegerValueContext ctx) {
        int i = Integer.parseInt(ctx.IntegerConstant().getText());
        
        return value.numeric(i);
    }
    
    @Override
    public Value visitFloatingValue(SqlParser.FloatingValueContext ctx) {
        double d = Double.parseDouble(ctx.FloatingConstant().getText());
        
        return value.numeric(d);
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

        return value.function(fnc, params.toArray(new Value[params.size()]));
    }
}
