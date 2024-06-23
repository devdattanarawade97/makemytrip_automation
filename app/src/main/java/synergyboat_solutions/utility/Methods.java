
package synergyboat_solutions.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Methods {

    // variables
    static WebDriver driver;

    // constructor

    // invoke driver

    public static WebDriver invokeDriver() {

        boolean status = false;
        try {

            // local driver setting
            driver = new ChromeDriver();

            // final DesiredCapabilities capabilities = new DesiredCapabilities();
            // capabilities.setBrowserName("chrome");
            // URL url = new URL("http://localhost:4444/wd/hub");
            // driver = new RemoteWebDriver(url, capabilities);

            driver.manage().window().maximize();
            status = true;

        } catch (Exception e) {

            catchException(e);
        } finally {
            System.out.println("driver invoked : " + status);
        }
        return driver;
    }

    // quit driver
    public static void quitDriver() {

        try {
            if (driver != null) {

                driver.quit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            catchException(e);
        }
    }

    /**
     * Navigates to a specified URL and validates if the page is loaded correctly.
     *
     * This method attempts to open the given URL and verifies if the current URL of
     * the driver matches the expected URL.
     * It returns the status of the operation. If an exception occurs during this
     * process, it is caught and handled
     * by a custom exception handling method.
     *
     * @param url the URL of the page to navigate to.
     * @return true if the URL was successfully loaded, false otherwise.
     */
    public static boolean getUrlPage(String url) {

        // variables
        // Boolean variable to hold the operation status.
        boolean status = false;
        // StringBuilder to build the status message.
        StringBuilder builder = new StringBuilder();
        try {
            // Navigate to the specified URL.
            driver.get(url);
            // Wait for 5 seconds.
            Thread.sleep(5000);
            // Get the current URL of the driver.
            String currentUrl = driver.getCurrentUrl();
            // Check if the current URL matches the expected URL.
            if (currentUrl.equals(url)) {
                status = true;

            }

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            catchException(e);
        } finally {
            // Append the status message to the StringBuilder and print it to the console.
            builder.append("Url Loaded successfully : ").append(status);
            System.out.println(builder);
        }
        // Return the status of the operation.
        return status;

    }

    // catch exceptions
    /**
     * Handles and logs exceptions that occur during the execution of methods.
     *
     * This method logs the exception message and stack trace to the console.
     * It is used to catch and handle any exceptions that occur within other
     * methods.
     *
     * @param e the exception that occurred.
     */
    public static void catchException(Exception e) {

        // variables

        // Log the exception message to the console.
        System.out.println("Exception Occured : " + e.getMessage());
        // Log the stack trace of the exception to the console.
        System.out.println("Stack trace : ");
        e.printStackTrace();
    }

    // wait for element
    /**
     * Waits for an element identified by XPath to become visible on the webpage.
     *
     * This method waits for a maximum of 60 seconds for the element specified by
     * the given XPath to become
     * visible on the webpage. Once the element is found and visible, it is
     * returned. If an exception occurs
     * during this process, it is caught and handled by a custom exception handling
     * method.
     *
     * @param xpath the XPath expression used to locate the element.
     * @return the WebElement object once it is visible, or null if it is not found
     *         within the specified time.
     */
    public static WebElement waitForElement(String xpath) {

        // variable
        // Variable to hold the WebElement object.
        WebElement element = null;
        try {
            // Create a WebDriverWait instance with a timeout of 60 seconds.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            // Wait until the element located by the XPath expression becomes visible.
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            catchException(e);
        }
        // Return the WebElement object once it is visible, or null if not found.
        return element;
    }

    /**
     * Performs mouse scroll to bring an element into view and clicks on it using
     * Actions class.
     *
     * This method uses the Actions class to perform a mouse scroll to bring the
     * specified WebElement into view
     * and then clicks on it. If an exception occurs during this process, it is
     * caught and handled by a custom
     * exception handling method.
     *
     * @param element the WebElement to which mouse scroll and click action is
     *                performed.
     */
    public static void mouseScrollForElement(WebElement element) {

        try {
            // Create an Actions instance to perform mouse actions.
            Actions actions = new Actions(driver);
            // Scroll to the specified element and click on it.
            actions.scrollToElement(element).click().perform();

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            catchException(e);
        }
    }

    // mouse movement for element
    /**
     * Performs mouse movement to hover over and click on a WebElement using Actions
     * class.
     *
     * This method uses the Actions class to move the mouse pointer to hover over
     * the specified WebElement
     * and then clicks on it. If an exception occurs during this process, it is
     * caught and handled by a custom
     * exception handling method.
     *
     * @param element the WebElement on which mouse movement and click action is
     *                performed.
     */
    public static void mouseMovementForElement(WebElement element) {

        try {
            // Create an Actions instance to perform mouse actions.
            Actions actions = new Actions(driver);
            // Move the mouse to hover over the specified element and click on it.
            actions.moveToElement(element).click().perform();

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            catchException(e);
        }

    }

    // validate selected element
    /**
     * Validates if the actual text contains the expected text.
     *
     * This method compares the expected text with the actual text and returns true
     * if the actual text contains
     * the expected text (case-sensitive). It prints both expected and actual texts
     * to the console for debugging purposes.
     * If an exception occurs during this process, it is caught and handled by a
     * custom exception handling method.
     *
     * @param expectedText the text expected to be found within the actual text.
     * @param actualText   the actual text to be validated.
     * @return true if the actual text contains the expected text, false otherwise.
     */
    public static boolean validateExpectedText(String expectedText, String actualText) {

        // variable
        // Boolean variable to hold the validation status.
        boolean status = false;

        try {
            // Print expected and actual texts to the console for debugging.
            System.out.println("expected : " + expectedText + " | actual : " + actualText);
            // Check if the actual text contains the expected text.
            if (actualText.contains(expectedText)) {
                status = true;
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }
        // Return the validation status.
        return status;

    }

    // validate expected number
    /**
     * Validates if the actual number matches the expected number.
     *
     * This method compares the expected number with the actual number and returns
     * true if they are equal.
     * If an exception occurs during this process, it is caught and handled by a
     * custom exception handling method.
     *
     * @param expectedNumber the number expected to match the actual number.
     * @param actualNumber   the actual number to be validated.
     * @return true if the actual number matches the expected number, false
     *         otherwise.
     */
    public static boolean validateExpectedNumber(int expectedNumber, int actualNumber) {

        // variable
        // Boolean variable to hold the validation status.
        boolean status = false;

        try {
            // Compare the expected number with the actual number.
            if (expectedNumber == actualNumber) {
                status = true;
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }

        // Return the validation status.
        return status;

    }

    // select date
    /**
     * Selects a date on the calendar based on the expected month and year, and
     * expected date.
     *
     * This method navigates through the calendar to find and select the specified
     * date based on the expected
     * month and year, and the expected date. It returns the WebElement of the
     * selected date element once it
     * is found and selected. If an exception occurs during this process, it is
     * caught and handled by a custom
     * exception handling method.
     *
     * @param expectedMonthAndYear the month and year in MM YYYY format expected to
     *                             match the calendar display.
     * @param expectedDate         the specific date expected to be selected on the
     *                             calendar.
     * @return the WebElement representing the selected date element, or null if the
     *         date is not found or selected.
     */
    public static WebElement selectDate(String expectedMonthAndYear, String expectedDate) {
        // variables
        // Variables to track if month and date are found
        boolean monthFound = false;
        boolean dateFound = false;
        // XPath expressions for different elements in the calendar
        String DEPARTURE_DATE_XPATH = "//label[@for='departure']//parent::div";
        String CALENDAR_XPATH = "//div[@class='DayPicker']";
        String ALL_VISIBLE_DATES_XPATH = ".//descendant::div[@class='dateInnerCell']//p";
        String LIST_OF_ALL_MONTHS_XPATH = "//div[@class='DayPicker-Month']";
        String NEXT_MONTH_BUTTON_XPATH = "//span[@aria-label='Next Month']";
        String MONTH_TEXT_XPATH = ".//descendant::div[@class='DayPicker-Caption']//div";
        WebElement selectedDateElement = null;

        String eachMonthText = "";
        WebElement selectedMonthElement = null;

        try {
            // Wait for departure date element to be clickable
            Thread.sleep(3000);
            WebElement departureDateElement = driver.findElement(By.xpath(DEPARTURE_DATE_XPATH));

            // Click on departure date element using JavaScript
            String script = "var event = new MouseEvent('click', {bubbles: true, cancelable: true});" +
                    "event.stopPropagation = function() { event.cancelBubble = true; };" +
                    "arguments[0].dispatchEvent(event);";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script, departureDateElement);
            Thread.sleep(10000);
            // Loop until the expected month and year are found or the date is selected
            do {
                // Get all month elements
                List<WebElement> listOfAllMonths = driver.findElements(By.xpath(LIST_OF_ALL_MONTHS_XPATH));
                System.out.println("list of months elements: " + listOfAllMonths.size());

                for (WebElement eachMonth : listOfAllMonths) {

                    eachMonthText = eachMonth.findElement(By.xpath(MONTH_TEXT_XPATH)).getText().replace(" ", "");
                    System.out.println("actual each Month text : " + eachMonthText + "| expected each Month text : "
                            + expectedMonthAndYear.replace(" ", ""));

                    // Compare each month text with expected month and year
                    if (eachMonthText.equalsIgnoreCase(expectedMonthAndYear.replace(" ", ""))) {
                        monthFound = true;
                        selectedMonthElement = eachMonth;
                        break;
                    }
                }

                if (monthFound) {
                    // Get all visible dates for the selected month
                    List<WebElement> listOfAllDates = selectedMonthElement
                            .findElements(By.xpath(ALL_VISIBLE_DATES_XPATH));

                    System.out.println("all dates list size " + listOfAllDates.size());
                    Thread.sleep(2000);

                    for (WebElement eachDate : listOfAllDates) {
                        String eachDateText = eachDate.getText();
                        System.out.println("actual date : " + eachDateText + " | " + "expected date : " + expectedDate);
                        // Compare each date text with expected date
                        if (eachDateText.equalsIgnoreCase(expectedDate)) {
                            selectedDateElement = eachDate;
                            dateFound = true;
                            break;
                        }

                    }
                    if (dateFound) {
                        break;// Exit the loop if date is found
                    }
                } else {

                    // If expected month and year are not found, navigate to next month
                    WebElement nextButtonElement = driver.findElement(By.xpath(NEXT_MONTH_BUTTON_XPATH));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].scrollIntoView(true);", nextButtonElement);
                    // Wait until the next button is clickable and click on it
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                    wait.until(ExpectedConditions.elementToBeClickable(nextButtonElement));

                    nextButtonElement.click();
              
                    Thread.sleep(5000);
                }
            } while (!monthFound || !dateFound);

        } catch (Exception e) {
            Methods.catchException(e);
        } finally {
            // Print status message after date selection attempt
            System.out.println("date selected successfully : " + dateFound);
        }
        // Return the WebElement representing the selected date element
        return selectedDateElement;
    }

    /**
     * Selects the expected 'From' and 'To' locations on the webpage.
     *
     * This method selects the expected 'From' and 'To' locations by interacting
     * with the respective input fields,
     * searching for the expected locations, and validating the selected locations
     * against the expected ones. It returns
     * true if both 'From' and 'To' locations are successfully selected as expected.
     * If an exception occurs during this process,
     * it is caught and handled by a custom exception handling method.
     *
     * @param expectedFrom         the text to be entered in the 'From' location
     *                             input field.
     * @param expectedTo           the text to be entered in the 'To' location input
     *                             field.
     * @param expectedFromLocation the location expected to be selected in the
     *                             'From' location dropdown list.
     * @param expectedToLocation   the location expected to be selected in the 'To'
     *                             location dropdown list.
     * @return true if both 'From' and 'To' locations are successfully selected as
     *         expected, false otherwise.
     */
    public static boolean selectLocation(String expectedFrom, String expectedTo, String expectedFromLocation,
            String expectedToLocation) {
        // variables
        // Variable to track the status of location selection
        boolean status = false;

        // XPath expressions for different elements related to location selection
        String FLIP_ICON_XPATH = "//span[@class='fltSwipCircle']";
        String FROM_XPATH = "//*[@id='fromCity']";
        String TO_XPATH = "//*[@id='toCity']";
        String FROM_SELECT_LIST_XPATH = "//*[@id='fromCity']//following::div[4]";
        String TO_SELECT_LIST_XPATH = "//*[@id='toCity']//following::div[4]";
        String FROM_SEARCHED_LIST = "//*[@id='fromCity']//following::div[4]//descendant::li//p//span[1]//span[1]";
        String TO_SEARCHED_LIST = "//*[@id='toCity']//following::div[4]//descendant::li//p//span[1]//span[1]";
        // String LOCATION_XPATH = ".//p//span[1]//span[1]";
        String SELECTED_LOCATION = ".//following::span[1]";
        try {
            // Wait for elements to be interactable
            Thread.sleep(3000);

            // Locate and interact with 'From' element
            WebElement fromElement = driver.findElement(By.xpath(FROM_XPATH));
            Methods.mouseScrollForElement(fromElement);

            fromElement.sendKeys(expectedFrom);
            Thread.sleep(3000);

            // Search for the expected 'From' location in the dropdown list
            List<WebElement> SearchedFromList = driver.findElements(By.xpath(FROM_SEARCHED_LIST));
            for (WebElement eachResult : SearchedFromList) {

                String actualFromLocationText = eachResult.getText();
                System.out.println("actual from location text : " + actualFromLocationText);
                // Click on the expected 'From' location and validate
                if (actualFromLocationText.equalsIgnoreCase(expectedFromLocation)) {

                    eachResult.click();
                    Thread.sleep(3000);
                    String actualSelectedLocationText = fromElement.findElement(By.xpath(SELECTED_LOCATION)).getText();
                    status = Methods.validateExpectedText(expectedFromLocation, actualSelectedLocationText);

                    System.out.println("expected from location and actual from location are equal : " + status);
                    break;
                }
            }
            // If 'From' location is successfully selected, proceed to select 'To' location
            if (status) {

                WebElement toElement = Methods.waitForElement(TO_XPATH);
                Methods.mouseScrollForElement(toElement);
                toElement.sendKeys(expectedTo);
                Thread.sleep(3000);
                // Search for the expected 'To' location in the dropdown list
                List<WebElement> SearchedToList = driver.findElements(By.xpath(TO_SEARCHED_LIST));
                for (WebElement eachResult : SearchedToList) {
                    // WebElement actualToLocationElement =
                    // eachResult.findElement(By.xpath(LOCATION_XPATH));
                    String actualToLocationText = eachResult.getText();
                    System.out.println("actual to location text : " + actualToLocationText);
                    // Click on the expected 'To' location and validate
                    if (actualToLocationText.equalsIgnoreCase(expectedToLocation)) {
                        eachResult.click();
                        Thread.sleep(3000);
                        String actualSelectedLocationText = toElement.findElement(By.xpath(SELECTED_LOCATION))
                                .getText();
                        status = Methods.validateExpectedText(expectedToLocation, actualSelectedLocationText);
                        System.out.println("expected to location and actual to location are equal : " + status);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            Methods.catchException(e);
        } finally {
            // Print status message after location selection attempt
            System.out.println("user selected location successfully  : " + status);
        }
        // Return the status of location selection
        return status;
    }

    // validate not found
    /**
     * Validates if a 'Not Found' error message is displayed.
     * 
     * This method checks if an error message with class 'error-title' is displayed
     * on the webpage.
     * If the error message is found, it sets the status to true and clicks on the
     * 'Go Back' link.
     * Finally, it prints a message indicating whether flights were not found based
     * on the status.
     * 
     * @return true if the 'Not Found' error message is displayed and 'Go Back' link
     *         is clicked, false otherwise.
     */
    public static boolean validateNotFound() {
        // Variable to track the status of error message visibility
        boolean status = false;
        // XPath expressions for error message and 'Go Back' link
        String ERROR_TITLE_XPATH = "//p[@class='error-title']";
        String GO_BACK_XPATH = "//a[text()='Go Back']";
        try {
            // Wait for error message element to be visible
            Methods.waitForElement(ERROR_TITLE_XPATH);
            WebElement errorTitle = driver.findElement(By.xpath(ERROR_TITLE_XPATH));
            // If error message is displayed, set status to true and click on 'Go Back' link
            if (errorTitle.isDisplayed()) {
                status = true;
                WebElement goBackXpath = driver.findElement(By.xpath(GO_BACK_XPATH));
                goBackXpath.click();
            }

        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the process
            Methods.catchException(e);
        } finally {
            // Print status message after attempting to validate 'Not Found' error
            System.out.println("flights not found  : " + status);
        }
        // Return the status of error message visibility and 'Go Back' link click
        return status;
    }

    // get total number of webelement count
    /**
     * Gets the total number of WebElements based on the given XPath.
     * 
     * This method waits for the WebElements identified by the XPath expression to
     * be visible,
     * retrieves all matching WebElements, counts them, and returns the total count.
     * 
     * @param countXpath the XPath expression to locate the WebElements
     * @return the total number of WebElements found based on the XPath, or 0 if an
     *         exception occurs
     */
    public static int getTotalNumberOfElementCount(String countXpath) {

        // variable
        // Variable to store the total count of WebElements
        int totalCount = 0;
        try {
            // Wait for elements identified by the XPath to be visible
            Methods.waitForElement(countXpath);
            // Retrieve all WebElements matching the XPath
            List<WebElement> totalElementList = driver.findElements(By.xpath(countXpath));
            // Count the total number of WebElements found
            totalCount = totalElementList.size();
        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the process
            Methods.catchException(e);
        }
        // Return the total count of WebElements
        return totalCount;
    }


    //validate selected date 

    public static boolean validateSelectedDate(String expectedDate,String expectedMonthAndYear) {
        
        //variables
        boolean status = false;
        String ACTUAL_DATE_XPATH = "//label[@for='departure']//parent::div//descendant::span[2]";
        String ACTUAL_MONTH_XPATH = "//label[@for='departure']//parent::div//descendant::span[3]";
        String ACTUAL_YEAR_XPATH = "//label[@for='departure']//parent::div//descendant::span[4]";
        try {
            Methods.waitForElement(ACTUAL_MONTH_XPATH);
            WebElement actualDateElement = driver.findElement(By.xpath(ACTUAL_DATE_XPATH));
            WebElement actualMonthElement = driver.findElement(By.xpath(ACTUAL_MONTH_XPATH));
            WebElement actualYearElement = driver.findElement(By.xpath(ACTUAL_YEAR_XPATH));
            String actualDateText = actualDateElement.getText();
            String actualMonthText = actualMonthElement.getText();
            String actualYearText = actualYearElement.getText();
            String completeActualDateText = actualDateText + actualMonthText + actualYearText;

            // Append the date to the month and year
            String fullDate = expectedDate + " " + expectedMonthAndYear;
            
            // Define the input date format
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            
            // Parse the full date string into a LocalDate object
            LocalDate localDate = LocalDate.parse(fullDate, inputFormatter);
            
            // Define the output date format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dMMMyy");
            
            // Format the LocalDate object into the desired output format
         
            String formattedDate =  localDate.format(outputFormatter);
    
            System.out.println("Formatted Date: " + formattedDate);  // Output: Formatted Date: 4Aug24
            String expectedCompleteDateText = formattedDate;

            if (expectedCompleteDateText.equalsIgnoreCase(completeActualDateText)) {
                System.out.println("expected date : "+expectedCompleteDateText+" | actual date : "+completeActualDateText);
                status = true;
            }
        } catch (Exception e) {
            Methods.catchException(e);
        } finally {
            System.out.println("selected date validated successfully : "+status);
        }
        
        return status;
    }

}
