# Month and Year Picker
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![jitpack](https://jitpack.io/v/dewinjm/monthyear-picker.svg)](https://jitpack.io/v/dewinjm/monthyear-picker.svg)
[![Build Status](https://api.travis-ci.org/dewinjm/monthyear-picker.svg?branch=master)](https://travis-ci.org/dewinjm/monthyear-picker)

Month and Year picker library for Anndroid

Simple Picker | With ranges Picker
--- | ---
![Simple Picker](https://raw.github.com/dewinjm/monthyear-picker/develop/demo/src/main/res/drawable/img_02.png) | ![With ranges Picker](https://raw.github.com/dewinjm/monthyear-picker/develop/demo/src/main/res/drawable/img_01.png)

## Example
Show MonthYear-picker in a simple way

``` java
int yearSelected;
int monthSelected;

//Set default values
Calendar calendar = Calendar.getInstance();
yearSelected = calendar.get(Calendar.YEAR);
monthSelected = calendar.get(Calendar.MONTH);

MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected);
                
dialogFragment.show(getSupportFragmentManager(), null);
```

listening changes in values

``` java
dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
  @Override
  public void onDateSet(int year, int monthOfYear) {
    // do something
  }
});

```
### Show with Ranges
Note: the values of the ranges are in milliseconds.

The monthOfYear value is 0 for the first month and the last is 11, (0 = January, 11= December).

```java

// Use the calendar for create ranges
Calendar calendar = Calendar.getInstance();

calendar.clear();
calendar.set(2010, 0, 1); // Set minimum date to show in dialog
long minDate = calendar.getTimeInMillis(); // Get milliseconds of the modified date

calendar.clear();
calendar.set(2018, 11, 31); // Set maximum date to show in dialog
long maxDate = calendar.getTimeInMillis(); // Get milliseconds of the modified date

// Create instance with date ranges values        
MonthYearPickerDialogFragment dialogFragment =  MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, minDate, maxDate);
                
dialogFragment.show(getSupportFragmentManager(), null);
```


## Download
Add the repository to your project **build.gradle**:
``` gradle
repositories {
  jcenter()
  maven {
    url "https://jitpack.io"
  }
}
```
And add the library to your module **build.gradle**:
``` gradle
dependencies {
  compile 'com.github.dewinjm:retrofit-helper:1.0.1'
}
```
