package ch.sama.sql.dialect.tsql.grammar.exception;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class NotImplementedException extends RuntimeException {
    private static String getPosition(ParserRuleContext ctx) {
        Token token = ctx.getStart();
        return token.getLine() + ":" + token.getCharPositionInLine();
    }

    public NotImplementedException(String message, ParserRuleContext ctx) {
        super(message + " at " + getPosition(ctx));
    }

    public NotImplementedException(Throwable cause, ParserRuleContext ctx) {
        super(cause.getMessage() + " at " + getPosition(ctx) + cause);
    }

    public NotImplementedException(String message, Throwable cause, ParserRuleContext ctx) {
        super(message + " at " + getPosition(ctx), cause);
    }
}
