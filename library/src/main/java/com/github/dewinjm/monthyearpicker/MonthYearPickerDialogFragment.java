package com.github.dewinjm.monthyearpicker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class MonthYearPickerDialogFragment extends DialogFragment {
    public static final int NULL_INT = -1;
    private static final String ARG_MONTH = "month";
    private static final String ARG_YEAR = "year";
    private static final String ARG_TITLE = "title";
    private static final String ARG_MIN_DATE = "min_date";
    private static final String ARG_MAX_DATE = "max_date";
    private static final String ARG_LOCALE = "locale";
    private static final String ARG_MONTH_FORMAT = "monthFormat";

    private MonthYearPickerDialog.OnDateSetListener onDateSetListener;

    public MonthYearPickerDialogFragment() {
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year  Year to show as selected
     * @param month Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @return the fragment instance
     */
    public static MonthYearPickerDialogFragment getInstance(int month, int year) {
        return getInstance(month, year, NULL_INT, NULL_INT, null);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year  Year to show as selected
     * @param month Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param title set custom title
     * @return the fragment instance
     */
    public static MonthYearPickerDialogFragment getInstance(int month, int year, String title) {
        return getInstance(month, year, NULL_INT, NULL_INT, title);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year   Year to show as selected
     * @param month  Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param title  set custom title.
     * @param locale set specific locale from a language code.
     * @return the fragment instance
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            String title,
                                                            Locale locale) {
        return getInstance(month, year, NULL_INT, NULL_INT, title, locale, MonthFormat.SHORT);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year        Year to show as selected
     * @param month       Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param title       set custom title.
     * @param monthFormat Set month format strings.
     * @return the fragment instance
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            String title,
                                                            MonthFormat monthFormat) {
        return getInstance(month, year, NULL_INT, NULL_INT, title, null, monthFormat);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year        Year to show as selected
     * @param month       Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param title       set custom title.
     * @param locale      set specific locale from a language code.
     * @param monthFormat Set month format strings.
     * @return the fragment instance
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            String title,
                                                            Locale locale,
                                                            MonthFormat monthFormat) {
        return getInstance(month, year, NULL_INT, NULL_INT, title, locale, monthFormat);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year    Year to show as selected
     * @param month   Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param minDate set the min date in milliseconds which should be less then initial date set.
     * @param maxDate set the max date in milliseconds which should not be less then current date.
     * @return MonthYearPickerDialogFragment
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            long minDate,
                                                            long maxDate) {
        return getInstance(month, year, minDate, maxDate, null);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year    Year to show as selected
     * @param month   Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param minDate set the min date in milliseconds which should be less then initial date set.
     * @param maxDate set the max date in milliseconds which should not be less then current date.
     * @param title   set custom title
     * @return MonthYearPickerDialogFragment
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            long minDate,
                                                            long maxDate,
                                                            String title) {
        return getInstance(month, year, minDate, maxDate, title, MonthFormat.SHORT);
    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year    Year to show as selected
     * @param month   Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param minDate set the min date in milliseconds which should be less then initial date set.
     * @param maxDate set the max date in milliseconds which should not be less then current date.
     * @param title   set custom title.
     * @param locale  set specific locale from a language code.
     * @return MonthYearPickerDialogFragment
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            long minDate,
                                                            long maxDate,
                                                            String title,
                                                            Locale locale) {
        return getInstance(month, year, minDate, maxDate, title, locale, MonthFormat.SHORT);

    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year    Year to show as selected
     * @param month   Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param minDate set the min date in milliseconds which should be less then initial date set.
     * @param maxDate set the max date in milliseconds which should not be less then current date.
     * @param title   set custom title.
     * @param monthFormat Set month format strings.
     * @return MonthYearPickerDialogFragment
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            long minDate,
                                                            long maxDate,
                                                            String title,
                                                            MonthFormat monthFormat) {
        return getInstance(month, year, minDate, maxDate, title, null, monthFormat);

    }

    /**
     * Create a new instance of the DialogFragment
     *
     * @param year        Year to show as selected
     * @param month       Month to show as selected, the months are from (0-11) where 0 is January and 11 is December.
     * @param minDate     set the min date in milliseconds which should be less then initial date set.
     * @param maxDate     set the max date in milliseconds which should not be less then current date.
     * @param title       set custom title.
     * @param locale      set specific locale from a language code.
     * @param monthFormat Set month format strings.
     * @return MonthYearPickerDialogFragment
     */
    public static MonthYearPickerDialogFragment getInstance(int month,
                                                            int year,
                                                            long minDate,
                                                            long maxDate,
                                                            String title,
                                                            Locale locale,
                                                            MonthFormat monthFormat) {
        MonthYearPickerDialogFragment datePickerDialogFragment = new
                MonthYearPickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_MONTH, month);
        bundle.putInt(ARG_YEAR, year);
        bundle.putLong(ARG_MIN_DATE, minDate);
        bundle.putLong(ARG_MAX_DATE, maxDate);
        bundle.putString(ARG_TITLE, title);
        bundle.putSerializable(ARG_MONTH_FORMAT, monthFormat);

        if (locale != null)
            bundle.putSerializable(ARG_LOCALE, locale);

        datePickerDialogFragment.setArguments(bundle);
        return datePickerDialogFragment;
    }

    /**
     * Get callback of the year and month selected.
     */
    public void setOnDateSetListener(MonthYearPickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle dataBundle = getArguments();

        if (dataBundle == null)
            throw new AssertionError("Object cannot be null");

        int year = dataBundle.getInt(ARG_YEAR);
        int month = dataBundle.getInt(ARG_MONTH);
        long minDate = dataBundle.getLong(ARG_MIN_DATE);
        long maxDate = dataBundle.getLong(ARG_MAX_DATE);
        String title = dataBundle.getString(ARG_TITLE);
        MonthFormat monthFormat = (MonthFormat) dataBundle.getSerializable(ARG_MONTH_FORMAT);

        Locale locale = Locale.getDefault();

        checkForValidMinDate(year, month, minDate);
        checkForValidMaxDate(year, month, maxDate);

        if (dataBundle.containsKey(ARG_LOCALE))
            locale = (Locale) dataBundle.getSerializable(ARG_LOCALE);

        Locale.setDefault(locale);

        MonthYearPickerDialog simpleDatePickerDialog = new MonthYearPickerDialog(
                getActivity(),
                year,
                month,
                monthFormat,
                onDateSetListener);

        if (minDate != NULL_INT)
            simpleDatePickerDialog.setMinDate(minDate);

        if (maxDate != NULL_INT)
            simpleDatePickerDialog.setMaxDate(maxDate);

        if (title != null && !title.isEmpty())
            simpleDatePickerDialog.createTitle(title);

        return simpleDatePickerDialog;
    }

    private void checkForValidMinDate(int year, int month, long minDate) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, year);
        c.add(Calendar.MONTH, month);

        if (c.getTimeInMillis() < minDate)
            throw new IllegalArgumentException(
                    "The min date should be less than initial date set");
    }

    private void checkForValidMaxDate(int year, int month, long maxDate) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, year);
        c.add(Calendar.MONTH, month);

        if (c.getTimeInMillis() < maxDate)
            throw new IllegalArgumentException(
                    "The max date should not be less than current date.");
    }
}
