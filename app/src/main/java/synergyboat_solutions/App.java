/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package synergyboat_solutions;

import org.openqa.selenium.WebDriver;

import synergyboat_solutions.pages.FlightPage;
import synergyboat_solutions.pages.HomePage;
import synergyboat_solutions.utility.Methods;

public class App {

    // Verify date selection is disabled for dates < today

    static WebDriver driver;

    /**
     * Test case to verify invalid date selection on a specific webpage.
     * 
     * This method navigates to the given URL, initializes the HomePage object, and
     * verifies
     * the departure date selection using the provided month and date.
     * 
     * @param url       the URL of the webpage to navigate to
     * @param monthYear the month and year for the departure date selection
     * @param date      the specific date to select for departure
     * @return true if the test case passes (validates date selection), false
     *         otherwise
     */
    public static boolean Testcase01_verifyInvalidDateSelection(String url, String monthYear, String date) {

        // variables

        boolean status = false;

        try {
            // Initialize the HomePage object with the WebDriver instance
            HomePage homePage = new HomePage(driver);
            // Navigate to the provided URL and validate if the homepage is loaded
            // successfully
            status = homePage.getHomePage(url);
            // Validate the departure date selection on the homepage using the provided
            // month and date
            status = homePage.validateDepartureDate(monthYear, date);

        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the execution of the test
            // case
            Methods.catchException(e);
        } finally {
            // Quit the WebDriver instance after completing the test case execution
            Methods.quitDriver();
        }
        // Return the status of the test case execution
        return status;

    }

    // Verify by default that regular fare is selected when a user lands on this
    // page
    /**
     * Test case to verify that the regular fare is selected by default on a
     * specific webpage.
     * 
     * This method navigates to the given URL, initializes the HomePage object, and
     * verifies
     * that the default fare selection matches the expected regular fare.
     * 
     * @param url         the URL of the webpage to navigate to
     * @param defaultFair the expected default fare to validate
     * @return true if the test case passes (validates default fare selection),
     *         false otherwise
     */
    public static boolean Testcase02_verifyRegularFairSelectedAsDefault(String url, String defaultFair) {

        // variables
        // Variable to track the status of the test case
        boolean status = false;

        try {
            // Initialize the HomePage object with the WebDriver instance
            HomePage homePage = new HomePage(driver);
            // Navigate to the provided URL and validate if the homepage is loaded
            // successfully
            status = homePage.getHomePage(url);
            // Validate that the default fare selected matches the expected regular fare
            status = homePage.validateDefaultFair(defaultFair);

        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the execution of the test
            // case
            Methods.catchException(e);
        } finally {
            // Quit the WebDriver instance after completing the test case execution
            Methods.quitDriver();
        }
        // Return the status of the test case execution
        return status;

    }

    // Verify user can click on the swap button which will reverse the To & From
    // section
    /**
     * Test case to verify the functionality of swapping To & From locations on a
     * specific webpage.
     * 
     * This method navigates to the given URL, initializes the HomePage object,
     * selects locations for
     * expected From and To values, validates the departure date selection, verifies
     * the swap functionality
     * of To & From sections, and returns true if all verifications pass.
     * 
     * @param url                  the URL of the webpage to navigate to
     * @param monthYear            the month and year for the departure date
     *                             selection
     * @param date                 the specific date to select for departure
     * @param expectedFrom         the expected From location to select
     * @param expectedTo           the expected To location to select
     * @param expectedFromLocation the expected location text for From
     * @param expectedToLocation   the expected location text for To
     * @return true if all verifications pass (including location selection and swap
     *         functionality), false otherwise
     */
    public static boolean Testcase03_verifyToAndFromSwitchFunctionality(String url, String monthYear, String date,
            String expectedFrom, String expectedTo, String expectedFromLocation, String expectedToLocation) {

        // variables
        // Variable to track the status of the test case
        boolean status = false;

        try {
            // Initialize the HomePage object with the WebDriver instance
            HomePage homePage = new HomePage(driver);
            // Navigate to the provided URL and validate if the homepage is loaded
            // successfully
            status = homePage.getHomePage(url);
            // Select the expected From and To locations on the homepage
            status = Methods.selectLocation(expectedFrom, expectedTo, expectedFromLocation, expectedToLocation);
            // Validate the departure date selection using the provided month and date
            status = homePage.validateDepartureDate(monthYear, date);
            // Verify the swap functionality of To & From sections
            status = HomePage.validateToAndFromSwitch(expectedTo, expectedFrom, expectedToLocation,
                    expectedFromLocation);
            // Quit the WebDriver instance after completing the test case execution
            Methods.quitDriver();

        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the execution of the test
            // case
            Methods.catchException(e);
        } finally {

        }

        return status;

    }

    // Verify user can click on the swap button which will reverse the To & From
    // section
    /**
     * Test case to verify the major flow of user actions on a flight search page.
     * 
     * This method navigates to the given URL, initializes HomePage and FlightPage
     * objects,
     * selects locations for expected From and To values, validates the departure
     * date selection,
     * performs a search, verifies if results are not found, changes the From
     * location, performs
     * another search, counts the total number of flights before and after sorting
     * by airline,
     * sorts the results by expected airline, and verifies if the number of flights
     * after sorting
     * matches the expected count before sorting.
     * 
     * @param url                  the URL of the webpage to navigate to
     * @param monthYear            the month and year for the departure date
     *                             selection
     * @param date                 the specific date to select for departure
     * @param expectedFrom         the expected From location to select initially
     * @param expectedTo           the expected To location to select
     * @param expectedFromLocation the expected location text for initial From
     *                             selection
     * @param expectedToLocation   the expected location text for To selection
     * @param changedFromLocation  the changed From location text to select after
     *                             initial search
     * @param sortByairline        the airline name to sort the search results
     * @return true if all verifications pass during the major flow of actions,
     *         false otherwise
     */
    public static boolean Testcase04_Major_flow(String url, String monthYear, String date, String expectedFrom,
            String expectedTo, String expectedFromLocation, String expectedToLocation, String changedFromLocation,
            String sortByairline) {

        // variables
        // Variable to track the status of the test case
        boolean status = false;

        try {
            // Initialize the HomePage and FlightPage objects with the WebDriver instance
            HomePage homePage = new HomePage(driver);
            FlightPage flightPage = new FlightPage(driver);
            // Navigate to the provided URL and validate if the homepage is loaded
            // successfully
            status = homePage.getHomePage(url);
            // Select the expected From and To locations on the homepage
            status = Methods.selectLocation(expectedFrom, expectedTo, expectedFromLocation, expectedToLocation);
            // Validate the departure date selection using the provided month and date
            status = homePage.validateDepartureDate(monthYear, date);
            // Perform a search on the homepage
            HomePage.searchResult();
            // Verify if no flights are found
            status = Methods.validateNotFound();
            // Change the From location to the specified changedFromLocation
            status = Methods.selectLocation(changedFromLocation, expectedTo, expectedFromLocation, expectedToLocation);
            // Perform another search after changing the From location
            HomePage.searchResult();
            // Count the total number of flights before sorting by airline
            int flightCountBeforeSort = flightPage.countTotalFlights();
            // Sort the flight results by the expected airline name
            flightPage.sortResultsByExpectedAirline(sortByairline);
            // Count the total number of flights after sorting by airline
            int flightCountAfterSort = flightPage.countTotalFlights();
            // Verify if the number of flights after sorting matches the expected count
            // before sorting
            status = flightPage.verifyNumberOfFlightsForFilter(flightCountAfterSort, flightCountBeforeSort);

        } catch (Exception e) {
            // Catch and handle any exceptions that occur during the execution of the test
            // case
            Methods.catchException(e);
        } finally {
            // Quit the WebDriver instance after completing the test case execution
            Methods.quitDriver();
        }

        return status;

    }

    public static void main(String[] args) {

        driver = Methods.invokeDriver();
        String url = "https://www.makemytrip.com/";
        String defaultFair = "regular";
        String monthAndYear = "August 2024";
        String date = "4";
        String from = "delhi";
        String to = "jaipur";
        String expectedFromLocation = "Aligarh Airport";
        String expectedToLocation = "Jaipur";
        String changedFromLocation = "New Delhi";
        String sortByAirline = " IndiGo   ";

        // <======================Testcase01 Verify date selection is disabled for dates
        // < today ================>

        // Testcase01_verifyInvalidDateSelection(url);

        // <======================Testcase02 Verify by default that regular fare is
        // selected when a user lands on this ================>

        // Testcase02_verifyRegularFairSelectedAsDefault(url, defaultFair)

        // <======================Testcase03 - Verify by default that regular fare is
        // selected when a user lands on this ================>

        Testcase03_verifyToAndFromSwitchFunctionality(url, monthAndYear, date, from, to, expectedFromLocation,
                expectedToLocation);

        // <======================Testcase04 - Verify by default that regular fare is
        // selected when a user lands on this ================>

        // Testcase04_Major_flow(url, monthAndYear, date, from, to,
        // expectedFromLocation, expectedToLocation,
        // changedFromLocation, sortByAirline);

    }
}
