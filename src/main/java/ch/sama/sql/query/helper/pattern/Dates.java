package ch.sama.sql.query.helper.pattern;

import ch.sama.sql.query.exception.BadParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
    private static SimpleDateFormat createSDF(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        return sdf;
    }

    public static final SimpleDateFormat[] GER_DATE = { createSDF("dd.MM.yyyy") };
    public static final SimpleDateFormat[] GER_DATE_TIME = { createSDF("dd.MM.yyyy HH:mm:ss"), createSDF("dd.MM.yyyy HH:mm") };

    public static final SimpleDateFormat[] ISO_DATE = { createSDF("yyyy-MM-dd") };
    public static final SimpleDateFormat[] ISO_DATE_TIME = { createSDF("yyyy-MM-dd HH:mm:ss"), createSDF("yyyy-MM-dd HH:mm") };

    private static Date parse(String s, SimpleDateFormat[] accepted) {
        for (SimpleDateFormat sdf : accepted) {
            Date d;

            try {
                d = sdf.parse(s);
            } catch (ParseException e) {
                d = null;
            }

            if (d != null) {
                return d;
            }
        }

        return null;
    }

    private static boolean isDate(String s, SimpleDateFormat[] accepted) {
        return s != null && parse(s, accepted) != null;
    }

    public static boolean isGerDate(String s) {
        return isDate(s, GER_DATE);
    }

    public static boolean isGerDateTime(String s) {
        return isDate(s, GER_DATE_TIME);
    }

    public static boolean isIsoDate(String s) {
        return isDate(s, ISO_DATE);
    }

    public static boolean isIsoDateTime(String s) {
        return isDate(s, ISO_DATE_TIME);
    }

    public static boolean isKnownDate(String s) {
        return isGerDate(s) || isGerDateTime(s) || isIsoDate(s) || isIsoDateTime(s);
    }

    private static SimpleDateFormat[] getFormat(String s) {
        if (isGerDateTime(s)) {
            return GER_DATE_TIME;
        } else if (isGerDate(s)) {
            return GER_DATE;
        } else if (isIsoDateTime(s)) {
            return ISO_DATE_TIME;
        } else if (isIsoDate(s)) {
            return ISO_DATE;
        } else {
            throw new BadParameterException("Bad date format: " + s);
        }
    }

    public static String convertDate(String s, SimpleDateFormat dst) {
        return dst.format(parse(s, getFormat(s)));
    }

    public static String toIsoDate(String s) {
        return convertDate(s, ISO_DATE[0]);
    }

    public static String toIsoDateTime(String s) {
        return convertDate(s, ISO_DATE_TIME[0]);
    }

    public static String toGerDate(String s) {
        return convertDate(s, GER_DATE[0]);
    }

    public static String toGerDateTime(String s) {
        return convertDate(s, GER_DATE_TIME[0]);
    }
}
