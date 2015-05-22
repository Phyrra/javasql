package ch.sama.sql.query.helper.pattern;

import ch.sama.sql.query.exception.BadParameterException;

public class Dates {
    private static final String D_EU = "([1-9]|0[1-9]|[1-9][0-9])";
    private static final String EU = D_EU + "\\." + D_EU + "\\.\\d{4}";

    private static final String D_ISO = "(0[1-9]|[1-9][0-9])";
    private static final String ISO = "\\d{4}-" + D_ISO + "-" + D_ISO;

    public static final String EU_DATE = "^" + EU + "$";
    public static final String EU_DATE_TIME = "^" + EU + "\\s\\d{2}:\\d{2}(:\\d{2})?$";
    public static final String ISO_DATE = "^" + ISO + "$";
    public static final String ISO_DATE_TIME = "^" + ISO + "\\s\\d{2}:\\d{2}(:\\d{2})?$";

    public static boolean isEuropeanDate(String s) {
        return s != null && s.matches(EU_DATE);
    }

    public static boolean isEuropeanDateTime(String s) {
        return s != null && s.matches(EU_DATE_TIME);
    }

    public static boolean isIsoDate(String s) {
        return s != null && s.matches(ISO_DATE);
    }

    public static boolean isIsoDateTime(String s) {
        return s != null && s.matches(ISO_DATE_TIME);
    }

    private static String[] getEuropeanDateParts(String s) {
        String[] parts = s.split("\\.");

        for (int i = 0; i < 2; ++i) {
            if (parts[i].length() < 2) {
                parts[i] = "0" + parts[i];
            }
        }

        return parts;
    }

    private static String[] getIsoDateParts(String s) {
        return s.split("-");
    }

    private static String getTime(String s) {
        if (s == null || s.length() == 0) {
            return "00:00:00";
        } else if (s.matches("^\\d{2}:\\d{2}")) {
            return s + ":00";
        } else {
            return s;
        }
    }

    public static String europeanToIsoDate(String s) {
        if (isEuropeanDate(s)) {
            String[] parts = getEuropeanDateParts(s);

            return parts[2] + "-" + parts[1] + "-" + parts[0];
        } else if (isEuropeanDateTime(s)) {
            String[] parts = getEuropeanDateParts(s.split(" ")[0]);

            return parts[2] + "-" + parts[1] + "-" + parts[0];
        } else {
            throw new BadParameterException("Bad date format: " + s);
        }
    }

    public static String europeanToIsoDateTime(String s) {
        if (isEuropeanDate(s)) {
            String[] parts = getEuropeanDateParts(s);

            return parts[2] + "-" + parts[1] + "-" + parts[0] + " " + getTime(null);
        } else if (isEuropeanDateTime(s)) {
            String[] dt = s.split(" ");
            String[] parts = getEuropeanDateParts(dt[0]);

            return parts[2] + "-" + parts[1] + "-" + parts[0] + " " + getTime(dt[1]);
        } else {
            throw new BadParameterException("Bad date format: " + s);
        }
    }

    public static String isoToEuropeanDate(String s) {
        if (isIsoDate(s)) {
            String[] parts = getIsoDateParts(s);

            return parts[2] + "." + parts[1] + "." + parts[0];
        } else if (isIsoDateTime(s)) {
            String[] parts = getIsoDateParts(s.split(" ")[0]);

            return parts[2] + "." + parts[1] + "." + parts[0];
        } else {
            throw new BadParameterException("Bad date format: " + s);
        }
    }

    public static String isoToEuropeanDateTime(String s) {
        if (isIsoDate(s)) {
            String[] parts = getIsoDateParts(s);

            return parts[2] + "." + parts[1] + "." + parts[0] + " " + getTime(null);
        } else if (isIsoDateTime(s)) {
            String[] dt = s.split(" ");
            String[] parts = getIsoDateParts(dt[0]);

            return parts[2] + "." + parts[1] + "." + parts[0] + " " + getTime(dt[1]);
        } else {
            throw new BadParameterException("Bad date format: " + s);
        }
    }
}
