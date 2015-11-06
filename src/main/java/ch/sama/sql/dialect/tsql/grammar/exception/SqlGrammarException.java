package ch.sama.sql.dialect.tsql.grammar.exception;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class SqlGrammarException extends RuntimeException {
    private static String getPosition(ParserRuleContext ctx) {
        Token token = ctx.getStart();
        return token.getLine() + ":" + token.getCharPositionInLine();
    }

    public SqlGrammarException(String message, ParserRuleContext ctx) {
        super(message + " at " + getPosition(ctx));
    }

    public SqlGrammarException(Throwable cause, ParserRuleContext ctx) {
        super(cause.getMessage() + " at " + getPosition(ctx) + cause);
    }

    public SqlGrammarException(String message, Throwable cause, ParserRuleContext ctx) {
        super(message + " at " + getPosition(ctx), cause);
    }

    public SqlGrammarException(String message) {
        super(message);
    }

    public SqlGrammarException(Throwable cause) {
        super(cause);
    }

    public SqlGrammarException(String message, Throwable cause) {
        super(message, cause);
    }
}
