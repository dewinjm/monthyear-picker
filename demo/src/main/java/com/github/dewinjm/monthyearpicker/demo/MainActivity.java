package com.github.dewinjm.monthyearpicker.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int currentYear;
    private int yearSelected;
    private int monthSelected;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        textView = findViewById(R.id.tv_result);
        final CheckBox dateRangeCheckBox = findViewById(R.id.cbDateRange);
        final CheckBox CustomTitleCheckBox = findViewById(R.id.cbCustomTitle);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMonthYearPickerDialogFragment(dateRangeCheckBox.isChecked(),
                        CustomTitleCheckBox.isChecked());
            }
        });

        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        yearSelected = currentYear;
        monthSelected = calendar.get(Calendar.MONTH);

        updateViews();
    }

    private MonthYearPickerDialogFragment createDialog(boolean customTitle) {
        return MonthYearPickerDialogFragment
                .getInstance(monthSelected,
                        yearSelected,
                        customTitle ? getString(R.string.custom_title).toUpperCase() : null);
    }

    private MonthYearPickerDialogFragment createDialogWithRanges(boolean customTitle) {
        final int minYear = 2010;
        final int maxYear = currentYear;
        final int maxMoth = 11;
        final int minMoth = 0;
        final int minDay = 1;
        final int maxDay = 31;
        long minDate;
        long maxDate;

        Calendar calendar = Calendar.getInstance();

        calendar.clear();
        calendar.set(minYear, minMoth, minDay);
        minDate = calendar.getTimeInMillis();

        calendar.clear();
        calendar.set(maxYear, maxMoth, maxDay);
        maxDate = calendar.getTimeInMillis();

        return MonthYearPickerDialogFragment
                .getInstance(monthSelected,
                        yearSelected,
                        minDate,
                        maxDate,
                        customTitle ? getString(R.string.custom_title).toUpperCase() : null);
    }

    private void displayMonthYearPickerDialogFragment(boolean withRanges, boolean customTitle) {
        MonthYearPickerDialogFragment dialogFragment = withRanges ?
                createDialogWithRanges(customTitle) :
                createDialog(customTitle);

        dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int year, int monthOfYear) {
                monthSelected = monthOfYear;
                yearSelected = year;
                updateViews();
            }
        });

        dialogFragment.show(getSupportFragmentManager(), null);
    }

    private void updateViews() {
        String month = new DateFormatSymbols().getMonths()[monthSelected];
        textView.setText(String.format("%s / %s", month, yearSelected));
    }
}
