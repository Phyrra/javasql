package ch.sama.sql.grammar.helper;

import ch.sama.sql.grammar.antlr.SqlLexer;
import ch.sama.sql.grammar.antlr.SqlParser;
import org.antlr.v4.runtime.*;

public abstract class SqlParserHelper {
    public SqlParser parse(String pattern) {
        SqlLexer lexer = new SqlLexer(new ANTLRInputStream(pattern));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SqlParser parser = new SqlParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }
}
