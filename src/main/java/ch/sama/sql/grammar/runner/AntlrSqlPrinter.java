package ch.sama.sql.grammar.runner;

import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.SqlParserHelper;
import ch.sama.sql.grammar.visitor.PrintVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrSqlPrinter extends SqlParserHelper {
    public String run(String pattern) {
        SqlParser parser = parse(pattern);

        PrintVisitor visitor = new PrintVisitor();

        try {
            visitor.visit(parser.fullStatement());

            return visitor.get();
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
