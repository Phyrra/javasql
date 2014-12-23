package ch.sama.sql.grammar.visitor;

import ch.sama.sql.grammar.antlr.SqlBaseVisitor;
import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.query.base.*;

import java.util.ArrayList;
import java.util.List;

public class QueryVisitor extends SqlBaseVisitor<Void> {
    private StatementVisitor stmtVisitor;

    private List<IQuery> queryList;

    public QueryVisitor(IQueryFactory factory) {
        this.stmtVisitor = new StatementVisitor(factory);

        this.queryList = new ArrayList<IQuery>();
    }

    public List<IQuery> getQueryList() {
        return queryList;
    }

    public IQuery joinQueryList() {
        return queryList.get(0); // TODO: THIS..
    }

    @Override
    public Void visitFullStatement(SqlParser.FullStatementContext ctx) {
        if (ctx.cteStatementHead() != null) {
            visit(ctx.cteStatementHead());
        }

        visit(ctx.unionStatement());

        return null;
    }

    @Override
    public Void visitCteStatementHead(SqlParser.CteStatementHeadContext ctx) {
        visit(ctx.cteStatementBlock());

        return null;
    }

    @Override
    public Void visitCteStatementBlock(SqlParser.CteStatementBlockContext ctx) {
        queryList.add(stmtVisitor.visit(ctx.cteStatement()));

        if (ctx.cteStatementBlock() != null) {
            visit(ctx.cteStatementBlock());
        }

        return null;
    }

    @Override
    public Void visitUnionStatement(SqlParser.UnionStatementContext ctx) {
        queryList.add(stmtVisitor.visit(ctx.statement()));

        if (ctx.unionStatement() != null) {
            visit(ctx.unionStatement());
        }

        return null;
    }
}
