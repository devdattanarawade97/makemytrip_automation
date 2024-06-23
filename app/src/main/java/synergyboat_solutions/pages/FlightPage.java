package synergyboat_solutions.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import synergyboat_solutions.utility.*;

public class FlightPage {

    // variables
    static WebDriver driver;

    // constructor
    public FlightPage(WebDriver driver) {

        FlightPage.driver = driver;
    }

    // sort the flights by airlines
    /**
     * Sorts the flights by the specified airline.
     *
     * This method locates a list of airlines on a webpage using XPath, then
     * iterates through the list
     * to find the airline that matches the expected airline name. If a match is
     * found, it scrolls to
     * the element representing that airline. If an exception occurs during this
     * process, it is caught
     * and handled by a custom exception handling method.
     *
     * @param expectedAirline the name of the airline to sort by.
     */
    public void sortResultsByExpectedAirline(String expectedAirline) {

        // XPath to locate the list of airlines on the webpage.
        String AIRLINES_LIST_XPATH = "//p[text()='Airlines']";

        try {
            // Find all elements matching the XPath (list of airlines).
            List<WebElement> listOfAirlines = driver.findElements(By.xpath(AIRLINES_LIST_XPATH));
            // Iterate through each airline element.
            for (WebElement eachAirline : listOfAirlines) {
                // Get the text content of each airline element.
                String eachAirlineText = eachAirline.getText();
                // Check if the text matches the expected airline.
                if (eachAirlineText.equals(expectedAirline)) {
                    // Scroll to the element representing the expected airline.
                    Methods.mouseScrollForElement(eachAirline);
                    // Exit the loop once the expected airline is found.
                    break;
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }
    }

    /**
     * Counts the total number of flights displayed on the webpage.
     *
     * This method uses an XPath to locate elements representing flight details on a
     * webpage.
     * It counts the total number of these elements and returns the count. If an
     * exception
     * occurs during this process, it is caught and handled by a custom exception
     * handling method.
     *
     * @return the total number of flights found on the webpage.
     */

    public int countTotalFlights() {

        // variables
        // XPath to locate the elements representing flight details.
        String VIEW_FLIGHT_DETAILS_XPATH = "//span[text()='View Flight Details']";
        int flightCount = 0;
        try {
            // Count the total number of elements matching the XPath.
            flightCount = Methods.getTotalNumberOfElementCount(VIEW_FLIGHT_DETAILS_XPATH);
            // Print the total count of flights to the console.
            System.out.println("total flights count : " + flightCount);

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        }
        // Return the total number of flights.
        return flightCount;
    }

    /**
     * Validates the number of flights before and after applying a filter.
     *
     * This method compares the expected number of flights with the actual number of
     * flights
     * after a filter is applied. If the numbers match, it returns true; otherwise,
     * false.
     * If an exception occurs during this process, it is caught and handled by a
     * custom
     * exception handling method. The result of the validation is printed to the
     * console.
     *
     * @param expectedNumber the expected number of flights after applying the
     *                       filter.
     * @param actualNumber   the actual number of flights found after applying the
     *                       filter.
     * @return true if the expected number matches the actual number, false
     *         otherwise.
     */
    public boolean verifyNumberOfFlightsForFilter(int expectedNumber, int actualNumber) {

        // Boolean variable to hold the validation status.
        boolean status = false;
        try {
            // Validate if the expected number matches the actual number of flights.
            status = Methods.validateExpectedNumber(expectedNumber, actualNumber);

        } catch (Exception e) {
            // Handle any exceptions that occur during the process.
            Methods.catchException(e);
        } finally {
            // Print the validation status to the console.
            System.out.println("number of flights before and after filter are equal  : " + status);
        }
        // Return the validation status.
        return status;
    }

}
