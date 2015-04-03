package ch.sama.sql.grammar.runner;

import ch.sama.sql.grammar.antlr.SqlParser;
import ch.sama.sql.grammar.exception.SqlGrammarException;
import ch.sama.sql.grammar.helper.SqlParserBase;
import ch.sama.sql.grammar.visitor.PrintVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AntlrSqlPrinter extends SqlParserBase {
    public String run(String pattern) {
        SqlParser parser = parse(pattern);

        PrintVisitor visitor = new PrintVisitor();

        try {
            visitor.visit(parser.fullStatement());

            return visitor.get();
        } catch (ParseCancellationException e) {
            throw getParserException(e);
        } catch(Exception e) {
            throw new SqlGrammarException(e);
        }
    }
}
