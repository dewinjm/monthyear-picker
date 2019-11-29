package com.github.dewinjm.monthyearpicker;

import android.widget.NumberPicker;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class Presenter implements NumberPicker.OnValueChangeListener {
    static final int DEFAULT_START_YEAR = 1900;
    private static final int DEFAULT_END_YEAR = 2100;
    private Calendar tempDate;
    private Calendar currentDate;
    private Calendar minDate;
    private Calendar maxDate;
    private Locale currentLocale;
    private String[] months;
    private int numberOfMonths;
    private IPickerView pickerView;

    private OnDateChangedListener onDateChangedListener;

    Presenter(IPickerView pickerView) {
        this(pickerView, MonthFormat.SHORT);
    }

    Presenter(IPickerView pickerView, MonthFormat monthFormat) {
        setCurrentLocale(Locale.getDefault(), monthFormat);

        this.pickerView = pickerView;
        pickerView.setShortMonth(months);
        pickerView.setOnValueChanged(this);
        pickerView.setNumberOfMonth(numberOfMonths - 1);

        // set the min date giving priority of the minDate over startYear
        tempDate.clear();
        tempDate.set(DEFAULT_START_YEAR, 0, 1);
        setMinDate(tempDate.getTimeInMillis());

        // set the max date giving priority of the maxDate over endYear
        tempDate.clear();
        tempDate.set(DEFAULT_END_YEAR, 11, 31);
        setMaxDate(tempDate.getTimeInMillis());

        // initialize to current date
        currentDate.setTimeInMillis(System.currentTimeMillis());
        init(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), null);
    }

    private void setCurrentLocale(Locale locale, MonthFormat monthFormat) {
        if (!locale.equals(currentLocale))
            currentLocale = locale;

        tempDate = Util.getCalendarForLocale(tempDate, locale);
        minDate = Util.getCalendarForLocale(minDate, locale);
        maxDate = Util.getCalendarForLocale(maxDate, locale);
        currentDate = Util.getCalendarForLocale(currentDate, locale);
        numberOfMonths = tempDate.getActualMaximum(Calendar.MONTH) + 1;

        switch (monthFormat) {
            case SHORT:
                months = new DateFormatSymbols().getShortMonths();
                break;
            case LONG:
                months = new DateFormatSymbols().getMonths();
                break;
        }

        if (Util.isNumericMonths(months)) {
            // We're in a locale where a date should either be all-numeric, or all-text.
            // All-text would require custom NumberPicker formatters for day and year.
            months = new String[numberOfMonths];
            for (int i = 0; i < numberOfMonths; ++i)
                months[i] = String.format(locale, "%d", i + 1);
        }
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
        tempDate.setTimeInMillis(currentDate.getTimeInMillis());

        // take care of wrapping of days and months to update greater fields
        if (numberPicker == pickerView.getMonthSpinner()) {
            if (oldVal == 11 && newVal == 0) {
                tempDate.add(Calendar.MONTH, 1);
            } else if (oldVal == 0 && newVal == 11) {
                tempDate.add(Calendar.MONTH, -1);
            } else {
                tempDate.add(Calendar.MONTH, newVal - oldVal);
            }
        } else if (numberPicker == pickerView.getYearSpinner()) {
            tempDate.set(Calendar.YEAR, newVal);
        } else {
            throw new IllegalArgumentException();
        }

        // now set the date to the adjusted one
        setDate(tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH));
        updateSpinners();
        notifyDateChanged();
    }

    void init(int year, int monthOfYear, OnDateChangedListener onDateChangedListener) {
        setDate(year, monthOfYear);
        updateSpinners();
        this.onDateChangedListener = onDateChangedListener;
    }

    void setMinDate(long min) {
        tempDate.setTimeInMillis(min);
        if (tempDate.get(Calendar.YEAR) == minDate.get(Calendar.YEAR)
                && tempDate.get(Calendar.DAY_OF_YEAR) != minDate.get(Calendar.DAY_OF_YEAR)) {
            return;
        }
        minDate.setTimeInMillis(min);

        if (currentDate.before(minDate))
            currentDate.setTimeInMillis(minDate.getTimeInMillis());

        updateSpinners();
    }

    void setMaxDate(long max) {
        tempDate.setTimeInMillis(max);
        if (tempDate.get(Calendar.YEAR) == maxDate.get(Calendar.YEAR)
                && tempDate.get(Calendar.DAY_OF_YEAR) != maxDate.get(Calendar.DAY_OF_YEAR)) {
            return;
        }

        maxDate.setTimeInMillis(max);

        if (currentDate.after(maxDate))
            currentDate.setTimeInMillis(maxDate.getTimeInMillis());

        updateSpinners();
    }

    private void setDate(int year, int month) {
        currentDate.set(Calendar.YEAR, year);
        currentDate.set(Calendar.MONTH, month);

        if (currentDate.before(minDate))
            currentDate.setTimeInMillis(minDate.getTimeInMillis());
        else if (currentDate.after(maxDate))
            currentDate.setTimeInMillis(maxDate.getTimeInMillis());
    }

    private void updateSpinners() {
        int monthMin = 0;
        int monthMax = 11;

        if (currentDate.equals(minDate)) {
            monthMin = currentDate.get(Calendar.MONTH);
            monthMax = currentDate.getActualMaximum(Calendar.MONTH);
        } else if (currentDate.equals(maxDate)) {
            monthMin = currentDate.getActualMinimum(Calendar.MONTH);
            monthMax = currentDate.get(Calendar.MONTH);
        }

        pickerView.dateUpdate(PickerField.MONTH,
                monthMax,
                monthMin,
                currentDate);

        pickerView.dateUpdate(PickerField.YEAR,
                maxDate.get(Calendar.YEAR),
                minDate.get(Calendar.YEAR),
                currentDate);
    }

    int getYear() {
        return currentDate.get(Calendar.YEAR);
    }

    int getMonth() {
        return currentDate.get(Calendar.MONTH);
    }

    /**
     * The callback used to indicate the user changed the date.
     */
    public interface OnDateChangedListener {

        /**
         * Called upon a date change.
         *
         * @param year        The year that was set.
         * @param monthOfYear The month that was set (0-11) for compatibility with {@link
         *                    Calendar}.
         */
        void onDateChanged(int year, int monthOfYear);
    }

    /**
     * Notifies the listener, if such, for a change in the selected date.
     */
    private void notifyDateChanged() {
        if (onDateChangedListener != null) {
            onDateChangedListener.onDateChanged(getYear(), getMonth());
        }
    }
}
