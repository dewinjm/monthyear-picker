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
        if (locale == null)
            throw new IllegalArgumentException("You must specify the Locate value");

        if (oldCalendar == null)
            return Calendar.getInstance(locale);

        long currentTimeMillis = oldCalendar.getTimeInMillis();
        Calendar newCalendar = Calendar.getInstance(locale);
        newCalendar.setTimeInMillis(currentTimeMillis);
        return newCalendar;
    }

    /**
     * Valid if the list of months is numeric.
     *
     * @param value Array of month.
     */
    public static boolean isNumericMonths(String[] value) {
        return Character.isDigit(value[Calendar.JANUARY].charAt(0));
    }
}
