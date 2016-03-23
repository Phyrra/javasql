package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
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
    public Void visitFullStatement(SqlParser.FullStatementContext ctx) {
        appendIndented("sql");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitDataStatement(SqlParser.DataStatementContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitUnionStatement(SqlParser.UnionStatementContext ctx) {
        if (ctx.unionStatement() != null) {
            visit(ctx.unionStatement());
            appendIndented("union");
        }

        visit(ctx.statement());

        return null;
    }

    @Override
    public Void visitCteStatementHead(SqlParser.CteStatementHeadContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitCteStatementBlock(SqlParser.CteStatementBlockContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitCteStatement(SqlParser.CteStatementContext ctx) {
        appendIndented("cte");
        indent();

        visitChildren(ctx);

        unindent();

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
    public Void visitSelectAllStatement(SqlParser.SelectAllStatementContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitSelectTopStatement(SqlParser.SelectTopStatementContext ctx) {
        appendIndented("top " + ctx.IntegerConstant().getText());

        visitChildren(ctx);

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
    public Void visitOffsetStatement(SqlParser.OffsetStatementContext ctx) {
        appendIndented("offset");
        indent();

        appendIndented(ctx.IntegerConstant().getText());
        unindent();

        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitFetchStatement(SqlParser.FetchStatementContext ctx) {
        appendIndented("fetch");
        indent();

        appendIndented(ctx.IntegerConstant().getText());
        unindent();

        visitChildren(ctx);

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
    public Void visitCastValue(SqlParser.CastValueContext ctx) {
        appendIndented("cast");
        indent();

        visitChildren(ctx);

        unindent();

        return null;
    }

    @Override
    public Void visitArgumentList(SqlParser.ArgumentListContext ctx) {
        visit(ctx.argument());
        safeVisit(ctx.argumentList());

        return null;
    }
    
    @Override
    public Void visitArgument(SqlParser.ArgumentContext ctx) {
        appendIndented("argument");
        indent();
        
        visitChildren(ctx);
        
        unindent();
        
        return null;
    }
    
    @Override
    public Void visitDataType(SqlParser.DataTypeContext ctx) {
        appendIndented("data type: " + ctx.getText());
        
        return null;
    }

    @Override
    public Void visitConstArg(SqlParser.ConstArgContext ctx) {
        appendIndented("const arg: " + ctx.getText());

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
        appendIndented("table field");
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
    public Void visitTableSource(SqlParser.TableSourceContext ctx) {
        appendIndented("table");
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
    public Void visitAliasedTable(SqlParser.AliasedTableContext ctx) {
        visitChildren(ctx);

        return null;
    }

    @Override
    public Void visitAliasedStatement(SqlParser.AliasedStatementContext ctx) {
        visitChildren(ctx);

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
        appendIndented("by");
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
