package synergyboat_solutions.pages;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Durations;
import synergyboat_solutions.utility.*;

public class HomePage {

    // variables
    static WebDriver driver;

    // constructor

    public HomePage(WebDriver driver) {

        HomePage.driver = driver;

    }

    /**
     * Navigates to the home page specified by the given URL.
     *
     * This method attempts to open the URL of the home page and returns the status
     * of the operation.
     * If an exception occurs during this process, it is caught and handled by a
     * custom exception handling method.
     *
     * @param url the URL of the home page to navigate to.
     * @return true if the URL was successfully opened, false otherwise.
     */
    public boolean getHomePage(String url) {

        // variables
        // Boolean variable to hold the operation status.
        boolean status = false;

        try {
            // Attempt to open the URL of the home page.
            status = Methods.getUrlPage(url);

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }
        // Return the status of the operation.
        return status;

    }

    /**
     * Validates if the user can select a specified departure date.
     *
     * This method checks if the given departure date is valid and selectable. It
     * first attempts to find
     * the date element using the specified month and year. If the date element is
     * found, it checks if
     * the element is disabled or not. If the date is not disabled, the method
     * scrolls to the element,
     * waits until it is clickable, and then clicks it. The status of whether the
     * date was selectable
     * or not is returned.
     *
     * @param monthAndYear the month and year in which to find the departure date.
     * @param userDate     the specific date to validate.
     * @return true if the date is selectable, false otherwise.
     */
    public boolean validateDepartureDate(String monthAndYear, String userDate) {

        // Boolean variable to hold the validation status.
        boolean status = false;

        try {
            // Attempt to select the date element based on the provided month and year.
            WebElement selectedDateElement = Methods.selectDate(monthAndYear, userDate);

            // System.out.println("selected date element : "+selectedDateElement);
            if (selectedDateElement != null) {
                // Find the parent div element of the selected date element.
                WebElement elementDisabled = selectedDateElement
                        .findElement(By.xpath(".//ancestor::div[@class='DayPicker-Day']"));
                // System.out.println("actual date element : "+elementDisabled);
                // Get the aria-label attribute of the date element.

                String elementLabeltext = elementDisabled.getAttribute("aria-label");
                System.out.println("date element is label text : " + elementLabeltext);
                // Get the aria-disabled attribute of the date element to check if it is
                // disabled.
                String elementDisabledtext = elementDisabled.getAttribute("aria-disabled");
                System.out.println("date element is disabled : " + elementDisabledtext);
                // Check if the date element is not disabled.
                if (elementDisabledtext.equals("true")) {
                    status = false;
                } else {

                    // status = true;
                    // Scroll to the element.
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].scrollIntoView(true);", elementDisabled);

                    // Wait until the element is clickable
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
                    wait.until(ExpectedConditions.elementToBeClickable(elementDisabled));

                    // Click the date element.
                     elementDisabled.click();
                 
                    // Wait for 6 seconds after clicking the date element.
                    Thread.sleep(6000);
                    //validate selected date
                   status= Methods.validateSelectedDate(userDate, monthAndYear);
                }
            }

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);

        } finally {
            // Print the validation status to the console.
            System.out.println("user able to select  date : " + status);

        }

        // Return the validation status.
        return status;
    }

    /**
     * Validates if the default fare is selected on the webpage.
     *
     * This method checks if a specified default fare is present and selected among
     * the listed fares.
     * It searches for the fare elements using a defined XPath, iterates through the
     * list to find a
     * match with the given default fare, and then checks if the matching fare is
     * selected. If an
     * exception occurs during this process, it is caught and handled by a custom
     * exception handling method.
     *
     * @param defaultFair the fare value that is expected to be selected.
     * @return true if the default fare is found and selected, false otherwise.
     */
    public boolean validateDefaultFair(String defaultFair) {

        // Boolean variable to hold the validation status.
        boolean status = false;

        // XPath to locate the elements representing the fare values.
        String DEFAULT_FAIR_XPATH = "//div[@class='fareCardItem ']//div[2]//div[1]";

        try {
            // Find all elements matching the XPath (list of fares).
            List<WebElement> listOfAllFairs = driver.findElements(By.xpath(DEFAULT_FAIR_XPATH));

            // Wait for 5 seconds.
            Thread.sleep(5000);
            // Iterate through each fare element.
            for (WebElement eachFair : listOfAllFairs) {

                // Get the text content of each fare element.
                String eachFairText = eachFair.getText();
                // Check if the text matches the default fare.
                if (eachFairText.equalsIgnoreCase(defaultFair)) {
                    // Find the input element preceding the fare element.
                    WebElement defaultFairIsSelected = eachFair.findElement(By.xpath(".//preceding::input[1]"));
                    System.out.println("default fair is selected check : " + defaultFairIsSelected.isSelected());
                    // Check if the default fare is selected.
                    if (defaultFairIsSelected.isSelected()) {
                        status = true;
                    }
                    break;

                }
            }

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        } finally {
            // Print the validation status to the console.
            System.out.println("default fair is selected : " + status);

        }
        // Return the validation status.
        return status;
    }

    /**
     * Validates if the 'To' and 'From' locations are correctly switched on the
     * webpage.
     *
     * This method checks if the 'To' and 'From' locations have been correctly
     * swapped after clicking the flip icon.
     * It first waits for the flip icon to be clickable, clicks it, then validates
     * the new locations of 'From' and 'To'.
     * If an exception occurs during this process, it is caught and handled by a
     * custom exception handling method.
     *
     * @param expectedFrom         the expected 'From' location after the switch.
     * @param expectedTo           the expected 'To' location after the switch.
     * @param expectedFromLocation the actual text of the 'From' location to
     *                             validate.
     * @param expectedToLocation   the actual text of the 'To' location to validate.
     * @return true if the 'To' and 'From' locations are correctly switched, false
     *         otherwise.
     */

    public static boolean validateToAndFromSwitch(String expectedFrom, String expectedTo, String expectedFromLocation,
            String expectedToLocation) {

        // Boolean variable to hold the validation status
        boolean status = false;
        // XPath to locate the flip icon, 'From' input, 'To' input, and the selected
        // location.
        String FLIP_ICON_XPATH = "//span[@class='fltSwipCircle']";
        String FROM_XPATH = "//*[@id='fromCity']";
        String TO_XPATH = "//*[@id='toCity']";
        String SELECTED_LOCATION = ".//following::span[1]";
        try {
            // Wait for 2 seconds before proceeding.
            Thread.sleep(2000);
            // Wait for the flip icon element to be clickable and then click it.
            WebElement flipIconElement = Methods.waitForElement(FLIP_ICON_XPATH);
            flipIconElement.click();
            // Wait for 5 seconds after clicking the flip icon.
            Thread.sleep(5000);
            // Find the 'From' element and get the text of the selected location.
            WebElement fromElement = driver.findElement(By.xpath(FROM_XPATH));
            String actualSelectedFromLocationText = fromElement.findElement(By.xpath(SELECTED_LOCATION)).getText();
            // Validate the 'From' location text.
            status = Methods.validateExpectedText(expectedFromLocation, actualSelectedFromLocationText);
            // If the 'From' location is valid, proceed to validate the 'To' location.
            if (status) {
                WebElement toElement = driver.findElement(By.xpath(TO_XPATH));
                String actualSelectedToLocationText = toElement.findElement(By.xpath(SELECTED_LOCATION)).getText();
                status = Methods.validateExpectedText(expectedToLocation, actualSelectedToLocationText);
            }

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        } finally {
            // Print the validation status to the console.
            System.out.println("validated to and from switch successfully : " + status);
        }
        // Return the validation status.
        return status;

    }

    /**
     * Executes a search operation and handles the "OKAY, GOT IT!" button.
     *
     * This method performs a search by clicking on the search button and then
     * handles any pop-up
     * or overlay by clicking the "OKAY, GOT IT!" button if it appears. If an
     * exception occurs during
     * this process, it is caught and handled by a custom exception handling method.
     */
    public static void searchResult() {

        // variable
        // XPath to locate the search button and the "OKAY, GOT IT!" button.
        String SEARCH_BUTTON = "//a[text()='Search']";
        String GOT_IT_BUTTON_XPATH = "//button[text()='OKAY, GOT IT!']";
        try {
            // Wait for the search button to be present.
            Methods.waitForElement(SEARCH_BUTTON);
            // Find the search button element and click it.
            WebElement seachButtonElement = driver.findElement(By.xpath(SEARCH_BUTTON));
            seachButtonElement.click();
            // Wait for the "OKAY, GOT IT!" button to be present.
            Methods.waitForElement(GOT_IT_BUTTON_XPATH);
            // Find the "OKAY, GOT IT!" button element and click it.
            WebElement gotItButton = driver.findElement(By.xpath(GOT_IT_BUTTON_XPATH));
            gotItButton.click();
        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }
    }

}
