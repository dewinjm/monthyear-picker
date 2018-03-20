package com.github.dewinjm.monthyearpicker;

import org.junit.Test;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Test
    public void getCalendarByLocaleDefault() {
        Locale locale = Locale.getDefault();
        Calendar calendar = Util.getCalendarForLocale(null, locale);
        assertNotNull(calendar);
    }

    @Test
    public void getCalendarByLocale() {
        Locale locale = new Locale("en", "US");
        Calendar calendar = Calendar.getInstance(locale);

        Calendar result = Util.getCalendarForLocale(calendar, locale);
        assertEquals(result.getTime(), result.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCalendarByNullLocale() {
        Util.getCalendarForLocale(Calendar.getInstance(), null);
    }

    @Test
    public void isNotNumericMonths() {
        String[] months = new DateFormatSymbols().getShortMonths();
        assertFalse(Util.isNumericMonths(months));
    }

    @Test
    public void isNumericMonths() {
        String[] months = {"1", "2", "3"};
        assertTrue(Util.isNumericMonths(months));
    }
}
