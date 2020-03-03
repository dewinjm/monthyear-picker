package com.github.dewinjm.monthyearpicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class Dialog extends AlertDialog implements IDialog,
        DialogInterface.OnClickListener,
        Presenter.OnDateChangedListener {
    private Presenter presenter;
    private View view;
    private int selectedYear;
    private int selectedMonth;
    private int minMonth;
    private int maxMonth;
    private int minYear;
    private int maxYear;

    Dialog(Context context) {
        super(context);

        Context themeContext = getContext();
        LayoutInflater inflater = LayoutInflater.from(themeContext);
        view = inflater.inflate(R.layout.month_year_picker, null);
        setView(view);

        setButton(BUTTON_POSITIVE, themeContext.getString(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getString(android.R.string.cancel), this);

        presenter = new Presenter(new PickerView(view), MonthFormat.LONG);
    }

    private long getTimeInMillis(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTimeInMillis();
    }

    private void setDateRange() {
        if (minYear <= 0)
            minYear = Presenter.DEFAULT_START_YEAR;

        if (maxYear <= 0)
            maxYear = Presenter.DEFAULT_END_YEAR;

        if (minMonth < 0)
            minMonth = Calendar.JANUARY;

        if (maxMonth < 0 || maxMonth > Calendar.DECEMBER)
            maxMonth = Calendar.DECEMBER;

        if (minMonth > maxMonth)
            throw new IllegalArgumentException("Maximum month is less then minimum month");

        if (maxYear < minYear)
            throw new IllegalArgumentException("Maximum year is less then minimum year");

        this.presenter.setMinDate(getTimeInMillis(minYear, minMonth));
        this.presenter.setMaxDate(getTimeInMillis(maxYear, maxMonth));
    }

    private void init() {
        createTitle();
        setDateRange();

        if (selectedYear == 0)
            selectedYear = presenter.getYear();

        this.presenter.init(selectedYear, selectedMonth, this);
    }

    @Override
    public void show() {
        if (view == null) {
            dismiss();
            return;
        }

        init();
        super.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onDateChanged(int year, int monthOfYear) {

    }

    @Override
    public void setSelectedYear(int selectedYear) {
        this.selectedYear = selectedYear;
    }

    @Override
    public void setSelectedMonth(int selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    @Override
    public void setMonthRange(int minMonth, int maxMonth) {
        if (minMonth > maxMonth)
            throw new IllegalArgumentException("Maximum month is less then minimum month");

        this.minMonth = minMonth;
        this.maxMonth = maxMonth;
    }

    @Override
    public void setYearRange(int minYear, int maxYear) {
        if (maxYear < minYear)
            throw new IllegalArgumentException("Maximum year is less then minimum year");

        setMinYear(minYear);
        setMaxYear(maxYear);
    }

    @Override
    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    @Override
    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    @Override
    public void createTitle() {
        Locale locale = Locale.getDefault();
        String month = new DateFormatSymbols().getMonths()[selectedMonth].toUpperCase(locale);
        setTitle(String.format(locale, "%s - %s", month, selectedYear));
    }
}
