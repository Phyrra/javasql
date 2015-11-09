package ch.sama.sql.jql.parser;

import ch.sama.sql.jql.visitor.JqlConditionVisitor;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.jql.antlr.JqlLexer;
import ch.sama.sql.jql.antlr.JqlParser;
import ch.sama.sql.query.helper.condition.ICondition;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrJqlConditionBuilder {
    private IValueFactory valueFactory;

    public AntlrJqlConditionBuilder(IValueFactory valueFactory) {
        this.valueFactory = valueFactory;
    }

    public JqlParser parse(String pattern) {
        JqlLexer lexer = new JqlLexer(new ANTLRInputStream(pattern));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JqlParser parser = new JqlParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }

    public JqlGrammarException getParserException(ParseCancellationException e) {
        Throwable cause = e.getCause();

        if (cause instanceof RecognitionException) {
            Token t = ((RecognitionException)cause).getOffendingToken();
            return new JqlGrammarException("Line " + t.getLine() + ":" + t.getCharPositionInLine() + " no viable alternative at input '" + t.getText() + "'");
        } else {
            return new JqlGrammarException(e);
        }
    }

    public ICondition build(String condition) {
        JqlParser parser = parse(condition);

        JqlConditionVisitor visitor = new JqlConditionVisitor(valueFactory);

        try {
            return visitor.visit(parser.condition());
        } catch (ParseCancellationException e) {
            throw getParserException(e);
        } catch(Exception e) {
            throw new JqlGrammarException(e);
        }
    }
}
