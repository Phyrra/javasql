package ch.sama.sql.dialect.tsql.grammar.runner;

import ch.sama.sql.dialect.tsql.grammar.antlr.SqlParser;
import ch.sama.sql.dialect.tsql.grammar.exception.SqlGrammarException;
import ch.sama.sql.dialect.tsql.grammar.helper.SqlParserBase;
import ch.sama.sql.dialect.tsql.grammar.visitor.PrintVisitor;
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
