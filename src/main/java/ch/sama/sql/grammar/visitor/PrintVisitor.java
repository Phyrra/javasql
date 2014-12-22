package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.StringGetter;
import org.antlr.v4.runtime.ParserRuleContext;

public class PrintVisitor extends SqlBaseVisitor<Void> {
    private int indent;
    private StringBuilder builder;

    public PrintVisitor() {
        indent = 0;
        builder = new StringBuilder();
    }

    public String get() {
        return builder.toString();
    }

    private void indent() {
        ++indent;
    }

    private void unindent() {
        --indent;
    }

    private String getIndent() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indent; ++i) {
            sb.append("\t");
        }

        return sb.toString();
    }

    private void appendIndent() {
        builder.append(getIndent());
    }

    private void appendNL() {
        builder.append("\r\n");
    }

    private void appendIndented(String s) {
        appendIndent();
        builder.append(s);
        appendNL();
    }

    private void safeVisit(ParserRuleContext ctx) {
        if (ctx != null) {
            visit(ctx);
        }
    }

    @Override
    public Void visitSqlIdentifier(SqlParser.SqlIdentifierContext ctx) {
        appendIndented(ctx.Identifier().getText());

        return null;
    }

    @Override
    public Void visitStatement(SqlParser.StatementContext ctx) {
        appendIndented("statement");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitSelectStatement(SqlParser.SelectStatementContext ctx) {
        appendIndented("select");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitFromStatement(SqlParser.FromStatementContext ctx) {
        appendIndented("from");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitJoinStatement(SqlParser.JoinStatementContext ctx) {
        appendIndented("join");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitJoinType(SqlParser.JoinTypeContext ctx) {
        appendIndented(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Void visitWhereStatement(SqlParser.WhereStatementContext ctx) {
        appendIndented("where");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitOrderStatement(SqlParser.OrderStatementContext ctx) {
        appendIndented("order");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitPrimaryValue(SqlParser.PrimaryValueContext ctx) {
        appendIndented("primary");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitStringValue(SqlParser.StringValueContext ctx) {
        appendIndented("string: " + StringGetter.get(ctx.StringLiteral()));

        return null;
    }

    @Override
    public Void visitNumericValue(SqlParser.NumericValueContext ctx) {
        appendIndented("numeric: " + ctx.getText());

        return null;
    }

    @Override
    public Void visitFunctionValue(SqlParser.FunctionValueContext ctx) {
        appendIndented("function");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitQueryValue(SqlParser.QueryValueContext ctx) {
        appendIndented("query");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitArgumentList(SqlParser.ArgumentListContext ctx) {
        visit(ctx.expression());
        safeVisit(ctx.argumentList());

        return null;
    }

    @Override
    public Void visitAliasedValue(SqlParser.AliasedValueContext ctx) {
        appendIndented("aliased");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitValue(SqlParser.ValueContext ctx) {
        appendIndented("value");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitAllFields(SqlParser.AllFieldsContext ctx) {
        appendIndented("all");

        return null;
    }

    @Override
    public Void visitAllTableFields(SqlParser.AllTableFieldsContext ctx) {
        appendIndented("all");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitField(SqlParser.FieldContext ctx) {
        appendIndented("field");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitTableField(SqlParser.TableFieldContext ctx) {
        appendIndented("table");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitExpression(SqlParser.ExpressionContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitAdditiveExpression(SqlParser.AdditiveExpressionContext ctx) {
        if (ctx.additiveOperator() != null) {
            visit(ctx.additiveExpression());
            appendIndented(ctx.additiveOperator().getText());
        }

        visit(ctx.multiplicativeExpression());

        return null;
    }

    @Override
    public Void visitMultiplicativeExpression(SqlParser.MultiplicativeExpressionContext ctx) {
        if (ctx.multiplicativeOperator() != null) {
            visit(ctx.multiplicativeExpression());
            appendIndented(ctx.multiplicativeOperator().getText());
        }

        visit(ctx.negateExpression());

        return null;
    }

    @Override
    public Void visitNegateExpression(SqlParser.NegateExpressionContext ctx) {
        if (ctx.negateOperator() != null) {
            appendIndented("-");
        }

        visit(ctx.primaryExpression());

        return null;
    }

    @Override
    public Void visitPrimaryExpression(SqlParser.PrimaryExpressionContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitParExpression(SqlParser.ParExpressionContext ctx) {
        appendIndented("(");
        indent();

        visit(ctx.expression());

        unindent();
        appendIndented(")");

        return null;
    }

    @Override
    public Void visitValueList(SqlParser.ValueListContext ctx) {
        visit(ctx.value());
        safeVisit(ctx.valueList());

        return null;
    }

    @Override
    public Void visitSource(SqlParser.SourceContext ctx) {
        appendIndented("source");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitPrimarySource(SqlParser.PrimarySourceContext ctx) {
        appendIndented("primary");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitAliasedSource(SqlParser.AliasedSourceContext ctx) {
        appendIndented("aliased");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitSourceList(SqlParser.SourceListContext ctx) {
        visit(ctx.source());
        safeVisit(ctx.sourceList());

        return null;
    }

    @Override
    public Void visitDefaultOrderValue(SqlParser.DefaultOrderValueContext ctx) {
        appendIndented("default");
        indent();

        visit(ctx.expression());

        unindent();

        return null;
    }

    @Override
    public Void visitAscOrderValue(SqlParser.AscOrderValueContext ctx) {
        appendIndented("asc");
        indent();

        visit(ctx.expression());

        unindent();

        return null;
    }

    @Override
    public Void visitDescOrderValue(SqlParser.DescOrderValueContext ctx) {
        appendIndented("desc");
        indent();

        visit(ctx.expression());

        unindent();

        return null;
    }

    @Override
    public Void visitOrderValue(SqlParser.OrderValueContext ctx) {
        appendIndented("order");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitOrderList(SqlParser.OrderListContext ctx) {
        visit(ctx.orderValue());
        safeVisit(ctx.orderList());

        return null;
    }

    @Override
    public Void visitCondition(SqlParser.ConditionContext ctx) {
        appendIndented("condition");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }
}
