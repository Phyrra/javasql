package ch.sama.sql.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class WhiteSpaceSplitter implements ISplitter {
    @Override
    public List<String> split(String s) {
        // Lifted from: http://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
        // Adapted for quote escapes

        List<String> list = new ArrayList<String>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"((?:[^\"\\\\]|\\\\\")*)\"|'((?:[^'\\\\]|\\\\')*)'");
        Matcher regexMatcher = regex.matcher(s);

        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                list.add(
                        regexMatcher.group(1).replace("\\\"", "\"")
                );
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                list.add(
                        regexMatcher.group(2).replace("\\'", "'")
                );
            } else {
                // Add unquoted word
                list.add(
                        regexMatcher.group()
                );
            }
        }

        return list;
    }

    @Override
    public String join(List<String> list) {
        return list.stream()
                .map(s -> "\"" + s.replace("\"", "\\\"") + "\"")
                .collect(Collectors.joining(" "));
    }
}
