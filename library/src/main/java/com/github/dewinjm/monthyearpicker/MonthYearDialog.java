package com.github.dewinjm.monthyearpicker;

import android.content.Context;

import java.util.Calendar;

public class MonthYearDialog implements IBuilder {
    private Dialog dialog;
    private int minMonth;
    private int maxMonth;

    public MonthYearDialog(Context context) {
        this.dialog = new Dialog(context);
    }

    /**
     * Minimum enable month in picker (0-11 for compatibility with Calender.MONTH or
     * Calendar.JANUARY, Calendar.FEBRUARY etc).
     *
     * @param minMonth
     * @return MonthYearDialog
     */
    @Override
    public MonthYearDialog setMinMonth(int minMonth) {
        if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER)
            this.minMonth = minMonth;
        else
            throw new IllegalArgumentException("Month out of range please send months between" +
                    " Calendar.JANUARY, Calendar.DECEMBER");
        return this;
    }

    @Override
    public MonthYearDialog setMaxMonth(int maxMonth) {
        if (maxMonth <= Calendar.DECEMBER && maxMonth >= Calendar.JANUARY)
            this.maxMonth = maxMonth;
        else
            throw new IllegalArgumentException("Month out of range please send months between " +
                    "Calendar.JANUARY, Calendar.DECEMBER");
        return this;
    }

    @Override
    public MonthYearDialog setMonthRange(int minMonth, int maxMonth) {
        return this;
    }

    @Override
    public MonthYearDialog setSelectedMonth(int selectedMonth) {
        if (selectedMonth >= Calendar.JANUARY && selectedMonth <= Calendar.DECEMBER)
            this.dialog.setSelectedMonth(selectedMonth);
        else
            throw new IllegalArgumentException("Month out of range please send months between Calendar.JANUARY, Calendar.DECEMBER");

        return this;
    }

    /**
     * Starting year in the picker.
     *
     * @param minYear
     * @return MonthYearDialog
     */
    @Override
    public MonthYearDialog setMinYear(int minYear) {
        this.dialog.setMinYear(minYear);
        return this;
    }

    @Override
    public MonthYearDialog setMaxYear(int maxYear) {
        this.dialog.setMaxYear(maxYear);
        return this;
    }

    /**
     * Starting and ending year show in picker
     *
     * @param minYear starting year
     * @param maxYear ending year
     * @return MonthYearDialog
     */
    @Override
    public MonthYearDialog setYearRange(int minYear, int maxYear) {
        if (maxYear < minYear)
            throw new IllegalArgumentException("Maximum year is less then minimum year");

        this.dialog.setMinYear(minYear);
        this.dialog.setMaxYear(maxYear);
        return this;
    }

    @Override
    public MonthYearDialog setSelectedYear(int selectedYear) {
        this.dialog.setSelectedYear(selectedYear);
        return this;
    }

    @Override
    public MonthYearDialog setTitle(String title) {
        return this;
    }

    @Override
    public MonthYearDialog setMonthFormat(MonthFormat monthFormat) {
        return this;
    }

    @Override
    public MonthYearDialog setOnDateSetListener(MonthYearPickerDialog.OnDateSetListener onDateSetListener) {
        return this;
    }

    @Override
    public Dialog build() {
        return this.dialog;
    }
}
