package com.github.dewinjm.monthyearpicker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PresenterTest {
    private PickerView pickerView;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(appContext);
        pickerView = new PickerView(inflater.inflate(R.layout.month_year_picker, null));
    }

    @Test
    public void shouldBuiltWithSimpleInitialization() {
        Presenter presenter = new Presenter(pickerView);
        Calendar calendar = Calendar.getInstance();

        assertEquals(calendar.get(Calendar.MONTH), presenter.getMonth());
        assertEquals(calendar.get(Calendar.YEAR), presenter.getYear());
    }

    @Test
    public void shouldBuiltWithSpecifiedInitialization() {
        int year = 2000;
        int month = 3;
        Presenter presenter = new Presenter(pickerView);
        presenter.init(year, month, null);

        assertEquals(year, presenter.getYear());
        assertEquals(month, presenter.getMonth());
    }

    @Test
    public void shoul() {
        //set ranges date
        int yearMin = 1980;
        int yearMax = 2020;
        int monthMax = 11;
        int monthMin = 0;

        int month = 10;

        Presenter presenter = new Presenter(pickerView);
        presenter.init(yearMax, month, null);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(yearMin, monthMin, 1);
        presenter.setMinDate(calendar.getTimeInMillis());

        calendar.clear();
        calendar.set(yearMax, monthMax, 31);
        presenter.setMaxDate(calendar.getTimeInMillis());

        assertEquals(yearMax, pickerView.getYearSpinner().getMaxValue());
        assertEquals(yearMin, pickerView.getYearSpinner().getMinValue());

        assertEquals(monthMax, pickerView.getMonthSpinner().getMaxValue());
        assertEquals(monthMin, pickerView.getMonthSpinner().getMinValue());
    }
}
