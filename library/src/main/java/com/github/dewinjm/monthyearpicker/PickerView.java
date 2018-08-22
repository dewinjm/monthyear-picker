package com.github.dewinjm.monthyearpicker;

import android.view.View;
import android.widget.NumberPicker;

import java.util.Arrays;
import java.util.Calendar;

public class PickerView implements IPickerView {
    private final NumberPicker monthSpinner;
    private final NumberPicker yearSpinner;
    private String[] shortMonths;

    PickerView(View view) {
        monthSpinner = view.findViewById(R.id.myp_month);
        monthSpinner.setMinValue(0);
        monthSpinner.setOnLongPressUpdateInterval(200);

        yearSpinner = view.findViewById(R.id.myp_year);
        yearSpinner.setOnLongPressUpdateInterval(100);
    }

    private void updateMonthSpinners(int max, int min, Calendar current) {
        monthSpinner.setDisplayedValues(null);
        monthSpinner.setMinValue(min);
        monthSpinner.setMaxValue(max);
        monthSpinner.setWrapSelectorWheel(min == 0);

        // make sure the month names are a zero based array
        // with the months in the month spinner
        String[] displayedValues = Arrays.copyOfRange(
                shortMonths, monthSpinner.getMinValue(),
                monthSpinner.getMaxValue() + 1);

        monthSpinner.setDisplayedValues(displayedValues);

        // set the spinner values
        monthSpinner.setValue(current.get(Calendar.MONTH));
    }

    private void updateYearSpinners(int max, int min, Calendar current) {
        // year spinner range does not change based on the current date
        yearSpinner.setMinValue(min);
        yearSpinner.setMaxValue(max);
        yearSpinner.setWrapSelectorWheel(false);

        // set the spinner values
        yearSpinner.setValue(current.get(Calendar.YEAR));
    }

    @Override
    public void setNumberOfMonth(int numberOfMonths) {
        monthSpinner.setMaxValue(numberOfMonths);
    }

    @Override
    public void dateUpdate(PickerField field, int max, int min, Calendar current) {
        switch (field) {
            case YEAR:
                updateYearSpinners(max, min, current);
                break;
            case MONTH:
                updateMonthSpinners(max, min, current);
                break;
        }
    }

    @Override
    public void setShortMonth(String[] shortMonths) {
        this.shortMonths = shortMonths;
        monthSpinner.setDisplayedValues(shortMonths);
    }

    @Override
    public void setOnValueChanged(NumberPicker.OnValueChangeListener onValueChangeListener) {
        monthSpinner.setOnValueChangedListener(onValueChangeListener);
        yearSpinner.setOnValueChangedListener(onValueChangeListener);
    }

    @Override
    public NumberPicker getMonthSpinner() {
        return monthSpinner;
    }

    @Override
    public NumberPicker getYearSpinner() {
        return yearSpinner;
    }

    @Override
    public void monthSetValue(int month) {
        monthSpinner.setValue(month);
    }

    @Override
    public void yearSetValue(int year) {
        yearSpinner.setValue(year);
    }
}
