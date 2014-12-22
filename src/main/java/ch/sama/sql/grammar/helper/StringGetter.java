package ch.sama.sql.grammar.helper;

import org.antlr.v4.runtime.tree.TerminalNode;

public class StringGetter {
    public static String get(TerminalNode sl) {
        String s = sl.getText();

        return s.substring(1, s.length() - 1).replace("''", "'");
    }
}
