package ch.sama.sql.grammar.runner;

import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.SqlParserBase;
import ch.sama.sql.grammar.visitor.QueryVisitor;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryFactory;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrQueryGenerator extends SqlParserBase {
    private IQueryFactory factory;

    public AntlrQueryGenerator(IQueryFactory factory) {
        this.factory = factory;
    }

    public IQuery generate(String pattern) {
        SqlParser parser = parse(pattern);

        QueryVisitor visitor = new QueryVisitor(factory);

        try {
            return visitor.visit(parser.fullStatement());
        } catch (ParseCancellationException e) {
            throw getParserException(e);
        } catch(Exception e) {
            throw new SqlGrammarException(e);
        }
    }
}
