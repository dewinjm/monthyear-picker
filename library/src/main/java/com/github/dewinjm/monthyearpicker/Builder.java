package com.github.dewinjm.monthyearpicker;

/**
 * Builder interface for specific methods to create the MonthYearPickerDialog object.
 */
public interface Builder {
    void setMinMonth(int minMonth);

    void setMaxMonth(int maxMonth);

    void setMinYear(int minYear);

    void setMaxYear(int maxYear);

    void setSelectedMonth(int selectedMonth);

    void setActivatedYear(int activatedMonth);

    void setTitle(String title);

    void setMonthFormat(MonthFormat monthFormat);

    void setOnDateSetListener(MonthYearPickerDialog.OnDateSetListener onDateSetListener);
}
