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

Listening changes in values

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

### Custom title
By default the title will be the selected date. If you want to customize dialog title, add the additional custom title parameter:

```java
String customTitle = "Your Custom Title";
MonthYearPickerDialogFragment dialogFragment = null;

//Simple way
dialogFragment = MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, customTitle);

// or with date ranges:
dialogFragment =  MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, minDate, maxDate, customTitle);
		
dialogFragment.show(getSupportFragmentManager(), null);
```

### Month name format
By default the months names format will short. If you want to use a specific format , add the MonthFormat parameter:

```java
MonthFormat monthFormat = MonthFormat.LONG; //MonthFormat.LONG or MonthFormat.SHORT
//Simple way
dialogFragment = MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, customTitle, monthFormat);

//With date ranges:
dialogFragment =  MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, minDate, maxDate, customTitle, monthFormat);
```

### Accept specify ```Locale```.
If you want to use a specific Locale , add the Locale parameter into the constructions:

```java
Locale locale = new Locale("en-US");

//Simple way
dialogFragment = MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, customTitle, locale);

//With date ranges:
dialogFragment =  MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected, minDate, maxDate, customTitle, locale);
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
  implementation 'com.github.dewinjm:monthyear-picker:1.0.2'
}
```
## License
	Copyright 2018 Dewin J. Martínez
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
