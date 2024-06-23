# Automated Testing Framework Documentation

## Introduction

This document provides an overview and documentation for the automated testing framework developed using Java and Selenium WebDriver. The framework includes utility methods, page objects, and test cases designed to validate various functionalities of a web application.

## Methods Overview

### `Methods.java`

- **`quitDriver()`**: Ensures the WebDriver instance is properly closed.
- **`catchException(Exception e)`**: Logs exceptions and prints stack traces.
- **`waitForElement(String xpath)`**: Waits for an element to be visible.
- **`mouseScrollForElement(WebElement element)`**: Scrolls to and clicks an element using Actions.
- **`validateExpectedText(String expectedText, String actualText)`**: Validates if actual text contains expected text.
- **`validateExpectedNumber(int expectedNumber, int actualNumber)`**: Validates if expected number equals actual number.
- **`getUrlPage(String url)`**: Loads a URL and validates its loading.

### `HomePage.java`

- **`getHomePage(String url)`**: Loads the home page of the web application.
- **`validateDepartureDate(String monthAndYear, String userDate)`**: Validates departure date selection.
- **`validateDefaultFair(String defaultFair)`**: Validates default fare selection on the page.
- **`validateToAndFromSwitch(String expectedFrom, String expectedTo, String expectedFromLocation, String expectedToLocation)`**: Validates the switching functionality between From and To locations.

### `FlightPage.java`

- **`countTotalFlights()`**: Counts the total number of flights displayed on the page.
- **`sortResultsByExpectedAirline(String airlineName)`**: Sorts flight results by the expected airline.
- **`verifyNumberOfFlightsForFilter(int expectedNumber, int actualNumber)`**: Verifies the number of flights before and after applying a filter.

## Test Cases Overview

### `TestCases.java`

- **`Testcase01_verifyInvalidDateSelection(String url, String monthYear, String date)`**: Verifies invalid date selection on the home page.
- **`Testcase02_verifyRegularFairSelectedAsDefault(String url, String defaultFair)`**: Verifies regular fare is selected as default on the home page.
- **`Testcase03_verifyToAndFromSwitchFunctionality(String url, String monthYear, String date, String expectedFrom, String expectedTo, String expectedFromLocation, String expectedToLocation)`**: Verifies the functionality to switch between From and To locations.
- **`Testcase04_Major_flow(String url, String monthYear, String date, String expectedFrom, String expectedTo, String expectedFromLocation, String expectedToLocation, String changedFromLocation, String sortByAirline)`**: Tests the major flow of selecting locations, validating dates, searching, sorting, and verifying flight results.



## Running Tests with Gradle

To run the automated tests using Gradle, execute the following command in the terminal:

```bash
cd makemytrip
gradle test
```


## Conclusion

This automated testing framework provides comprehensive coverage for testing various aspects of a web application related to flight booking. It includes utility methods for handling WebDriver operations, page objects encapsulating page-specific functionalities, and test cases validating critical user journeys. The framework enhances testing efficiency, reliability, and maintainability by leveraging Java, Selenium WebDriver, and TestNG for automated testing.
