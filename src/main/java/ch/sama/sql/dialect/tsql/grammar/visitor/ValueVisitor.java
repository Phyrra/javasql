package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

class ValueVisitor extends SqlBaseVisitor<Value> {
    private TSqlValueFactory valueFactory;
    private ValueListVisitor listVisitor;

    private QueryVisitor queryVisitor;

    public ValueVisitor(TSqlValueFactory value, QueryVisitor queryVisitor) {
        this.valueFactory = value;
        listVisitor = new ValueListVisitor(this);

        this.queryVisitor = queryVisitor;
    }

    @Override
    public Value visitValue(SqlParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAllFields(SqlParser.AllFieldsContext ctx) {
        return TSqlValueFactory.ALL;
    }

    @Override
    public Value visitAllTableFields(SqlParser.AllTableFieldsContext ctx) {
        List<SqlParser.SqlIdentifierContext> identifiers = ctx.table().sqlIdentifier();
        
        if (identifiers.size() > 1) {
            return valueFactory.table(new Table(identifiers.get(0).Identifier().getText(), identifiers.get(1).Identifier().getText()));
        } else {
            return valueFactory.table(new Table(identifiers.get(0).Identifier().getText()));
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
        return valueFactory.type(TYPE.INT_TYPE);
    }
    
    @Override
    public Value visitFloatType(SqlParser.FloatTypeContext ctx) {
        return valueFactory.type(TYPE.FLOAT_TYPE);
    }
    
    @Override
    public Value visitDatetimeType(SqlParser.DatetimeTypeContext ctx) {
        return valueFactory.type(TYPE.DATETIME_TYPE);
    }
    
    @Override
    public Value visitVarcharType(SqlParser.VarcharTypeContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitVarcharTypeIntMax(SqlParser.VarcharTypeIntMaxContext ctx) {
        int max = Integer.parseInt(ctx.IntegerConstant().getText());
        
        return valueFactory.type(TYPE.VARCHAR_TYPE(max));
    }
    
    @Override
    public Value visitVarcharTypeMax(SqlParser.VarcharTypeMaxContext ctx) {
        return valueFactory.type(TYPE.VARCHAR_MAX_TYPE);
    }
    
    @Override
    public Value visitVarcharTypeNoMax(SqlParser.VarcharTypeNoMaxContext ctx) {
        return valueFactory.type(TYPE.VARCHAR_TYPE);
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

            return valueFactory.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitMultiplicativeExpression(SqlParser.MultiplicativeExpressionContext ctx) {
        Value rhs = visit(ctx.negateExpression());

        if (ctx.multiplicativeOperator() != null) {
            Value lhs = visit(ctx.multiplicativeExpression());
            String op = ctx.multiplicativeOperator().getText();

            return valueFactory.combine(op, lhs, rhs);
        }

        return rhs;
    }

    @Override
    public Value visitNegateExpression(SqlParser.NegateExpressionContext ctx) {
        Value val = visit(ctx.primaryExpression());

        if (ctx.negateOperator() != null) {
            String op = ctx.negateOperator().getText();

            // TODO: Some sort of "combine"?
            return valueFactory.plain(op + val.getValue());
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
        
        return valueFactory.bracket(val);
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
        return valueFactory.field(field);
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
        
        return valueFactory.field(table, field);
    }

    @Override
    public Value visitStringValue(SqlParser.StringValueContext ctx) {
        String s = StringGetter.get(ctx.StringLiteral());
        return valueFactory.string(s);
    }

    @Override
    public Value visitNumericValue(SqlParser.NumericValueContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public Value visitIntegerValue(SqlParser.IntegerValueContext ctx) {
        int i = Integer.parseInt(ctx.IntegerConstant().getText());
        
        return valueFactory.numeric(i);
    }
    
    @Override
    public Value visitFloatingValue(SqlParser.FloatingValueContext ctx) {
        double d = Double.parseDouble(ctx.FloatingConstant().getText());
        
        return valueFactory.numeric(d);
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

        return valueFactory.function(fnc, params.toArray(new Value[params.size()]));
    }

    @Override
    public Value visitQueryValue(SqlParser.QueryValueContext ctx) {
        IQuery query = queryVisitor.visit(ctx.statement());

        return valueFactory.query(query);
    }

    @Override
    public Value visitCastValue(SqlParser.CastValueContext ctx) {
        Value value = visit(ctx.expression());
        IType type = (IType) visit(ctx.dataType()).getSource();

        return valueFactory.cast(value, type);
    }
}
