package ch.sama.sql.dialect.tsql.grammar.parser;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlLexer;
import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.dialect.tsql.grammar.exception.SqlGrammarException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

abstract class SqlParserBase {
    public SqlParser parse(String pattern) {
        SqlLexer lexer = new SqlLexer(new ANTLRInputStream(pattern));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SqlParser parser = new SqlParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }

    public SqlGrammarException getParserException(ParseCancellationException e) {
        Throwable cause = e.getCause();

        if (cause instanceof RecognitionException) {
            Token t = ((RecognitionException)cause).getOffendingToken();
            return new SqlGrammarException("Line " + t.getLine() + ":" + t.getCharPositionInLine() + " no viable alternative at input '" + t.getText() + "'");
        } else {
            return new SqlGrammarException(e);
        }
    }
}
