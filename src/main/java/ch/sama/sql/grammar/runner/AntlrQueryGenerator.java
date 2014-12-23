package ch.sama.sql.grammar.runner;

import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.SqlParserHelper;
import ch.sama.sql.grammar.visitor.PrintVisitor;
import ch.sama.sql.grammar.visitor.QueryVisitor;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryFactory;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrQueryGenerator extends SqlParserHelper {
    private IQueryFactory factory;

    public AntlrQueryGenerator(IQueryFactory factory) {
        this.factory = factory;
    }

    public IQuery generate(String pattern) {
        SqlParser parser = parse(pattern);

        QueryVisitor visitor = new QueryVisitor(factory);

        try {
            visitor.visit(parser.fullStatement());

            return visitor.joinQueryList();
        } catch (ParseCancellationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RecognitionException) {
                Token t = ((RecognitionException)cause).getOffendingToken();
                throw new SqlGrammarException("Line " + t.getLine() + ":" + t.getCharPositionInLine() + " no viable alternative at input '" + t.getText() + "'");
            } else {
                throw new SqlGrammarException(e);
            }
        } catch(Exception e) {
            throw new SqlGrammarException(e);
        }
    }
}
