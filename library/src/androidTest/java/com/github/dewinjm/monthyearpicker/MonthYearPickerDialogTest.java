package com.github.dewinjm.monthyearpicker;

import android.app.AlertDialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MonthYearPickerDialogTest {

    @Test
    public void builder() throws Exception {
        final int year = 2001;
        final int month = 6;
        final Context appContext = InstrumentationRegistry.getTargetContext();
        final String title = String.format(Locale.getDefault(),
                "%s - %s",
                new DateFormatSymbols().getMonths()[month].toUpperCase(),
                year);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MonthYearPickerDialog dialog = new MonthYearPickerDialog(appContext,
                        year,
                        month,
                        null);

                dialog.create();

                assertEquals(title, dialog.getTitle());
                assertEquals(dialog.getButton(AlertDialog.BUTTON_POSITIVE).getVisibility(), View.VISIBLE);
                assertEquals(dialog.getButton(AlertDialog.BUTTON_NEGATIVE).getVisibility(), View.VISIBLE);
            }
        });
    }

    @Test
    public void builderCustomTitle() throws Exception {
        final int year = 2001;
        final int month = 6;
        final Context appContext = InstrumentationRegistry.getTargetContext();
        final String title = "Custom Title";

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MonthYearPickerDialog dialog = new MonthYearPickerDialog(appContext,
                        year,
                        month,
                        null);

                dialog.createTitle("Custom Title");
                dialog.create();

                assertEquals(title, dialog.getTitle());
                assertEquals(dialog.getButton(AlertDialog.BUTTON_POSITIVE).getVisibility(), View.VISIBLE);
                assertEquals(dialog.getButton(AlertDialog.BUTTON_NEGATIVE).getVisibility(), View.VISIBLE);
            }
        });
    }

    @Test
    public void clickPositiveButtonWithSpecificValue() throws Exception {
        final int YEAR = 0;
        final int MONTH = 1;
        final int[] result = new int[2];
        final int yearSelect = 2001;
        final int monthSelect = 9;
        final int timeout = 10;
        final CountDownLatch latch = new CountDownLatch(1);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MonthYearPickerDialog dialog = new MonthYearPickerDialog(
                        InstrumentationRegistry.getTargetContext(),
                        yearSelect,
                        monthSelect,
                        new MonthYearPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int year, int monthOfYear) {
                                result[YEAR] = year;
                                result[MONTH] = monthOfYear;
                                latch.countDown();
                            }
                        });

                dialog.create();
                //Clicking in Positive Button
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
            }
        });

        assertTrue(latch.await(timeout, SECONDS));
        assertEquals(result[YEAR], yearSelect);
        assertEquals(result[MONTH], monthSelect);
    }

    @Test
    public void clickPositiveButtonWithDefaultValue() throws Exception {
        final int YEAR = 0;
        final int MONTH = 1;
        final int[] result = new int[2];

        final int timeout = 10;
        final CountDownLatch latch = new CountDownLatch(1);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MonthYearPickerDialog dialog = new MonthYearPickerDialog(
                        InstrumentationRegistry.getTargetContext(),
                        new MonthYearPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int year, int monthOfYear) {
                                result[YEAR] = year;
                                result[MONTH] = monthOfYear;
                                latch.countDown();
                            }
                        });

                dialog.create();
                //Clicking in Positive Button
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
            }
        });

        assertTrue(latch.await(timeout, SECONDS));
        assertEquals(result[YEAR], Presenter.DEFAULT_START_YEAR);

        int january = 0;
        assertEquals(result[MONTH], january);
    }


    @Test
    public void clickNegativeButtonDismissesDialog() throws Exception {
        final int YEAR = 0;
        final int MONTH = 1;
        final int[] result = new int[2];

        final int timeout = 10;
        final CountDownLatch latch = new CountDownLatch(1);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MonthYearPickerDialog dialog = new MonthYearPickerDialog(
                        InstrumentationRegistry.getTargetContext(),
                        new MonthYearPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int year, int monthOfYear) {
                                result[YEAR] = year;
                                result[MONTH] = monthOfYear;
                            }
                        });

                dialog.create();
                //Clicking in Negative Button
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
                latch.countDown();
            }
        });

        assertTrue(latch.await(timeout, SECONDS));
        int valueNoTSelect = 0;
        assertEquals(result[YEAR], valueNoTSelect);
        assertEquals(result[MONTH], valueNoTSelect);
    }
}
