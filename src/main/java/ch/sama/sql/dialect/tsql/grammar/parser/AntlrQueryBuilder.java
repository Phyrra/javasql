package ch.sama.sql.dialect.tsql.grammar.parser;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.dialect.tsql.grammar.visitor.QueryVisitor;
import ch.sama.sql.query.base.IQuery;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrQueryBuilder extends SqlParserBase {
    private TSqlQueryFactory builder;

    public AntlrQueryBuilder() {
        this.builder = new TSqlQueryFactory();
    }

    public IQuery build(String pattern) {
        SqlParser parser = parse(pattern);

        QueryVisitor visitor = new QueryVisitor(builder);

        try {
            return visitor.visit(parser.fullStatement());
        } catch (ParseCancellationException e) {
            throw getParserException(e);
        } catch(Exception e) {
            throw new SqlGrammarException(e);
        }
    }
}
