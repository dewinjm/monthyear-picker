package com.github.dewinjm.monthyearpicker;

import android.support.annotation.IntRange;

import java.util.Calendar;

/**
 * Builder interface for specific methods to create the MonthYearPickerDialog object.
 */
public interface IBuilder {
    MonthYearDialog setMinMonth(int minMonth);

    MonthYearDialog setMaxMonth(int maxMonth);

    MonthYearDialog setMonthRange(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                          int minMonth,
                                  @IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                          int maxMonth);

    MonthYearDialog setSelectedMonth(int selectedMonth);

    MonthYearDialog setMinYear(int minYear);

    MonthYearDialog setMaxYear(int maxYear);

    MonthYearDialog setYearRange(int minYear, int maxYear);

    MonthYearDialog setSelectedYear(int selectedYear);

    MonthYearDialog setTitle(String title);

    MonthYearDialog setMonthFormat(MonthFormat monthFormat);

    MonthYearDialog setOnDateSetListener(MonthYearPickerDialog.OnDateSetListener onDateSetListener);

    Dialog build();
}
