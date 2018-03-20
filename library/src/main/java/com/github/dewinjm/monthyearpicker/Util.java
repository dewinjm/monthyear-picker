package com.github.dewinjm.monthyearpicker;

import java.util.Calendar;
import java.util.Locale;

public class Util {
    /**
     * Gets a calendar for locale bootstrapped with the value of a given calendar.
     *
     * @param oldCalendar The old calendar.
     * @param locale      The locale.
     */
    public static Calendar getCalendarForLocale(Calendar oldCalendar, Locale locale) {
        if (oldCalendar == null) {
            return Calendar.getInstance(locale);
        } else {
            long currentTimeMillis = oldCalendar.getTimeInMillis();
            Calendar newCalendar = Calendar.getInstance(locale);
            newCalendar.setTimeInMillis(currentTimeMillis);
            return newCalendar;
        }
    }

    public static boolean usingNumericMonths(String[] value) {
        return Character.isDigit(value[Calendar.JANUARY].charAt(0));
    }
}
