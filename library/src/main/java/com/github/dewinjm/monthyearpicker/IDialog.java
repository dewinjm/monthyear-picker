package com.github.dewinjm.monthyearpicker;

public interface IDialog {
    void setSelectedYear(int selectedYear);

    void setYearRange(int minYear, int maxYear);

    void setMaxYear(int maxYear);

    void setMinYear(int minYear);

    void setSelectedMonth(int selectedMonth);

    void setMonthRange(int minMonth, int maxMonth);

    void createTitle();
}
