package stepdefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import pageobject.FlightSelectionPage;
import base.Base;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidateTestAFlightSearchFilter extends Base {

    private static final Logger log = LogManager.getLogger(ValidateTestAFlightSearchFilter.class);

    FlightSelectionPage page = new FlightSelectionPage();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Given("the user is on the MakeMyTrip flight search page")
    public void the_user_is_on_the_makemytrip_flight_search_page() {

        try {
            log.info("User is on MakeMyTrip flight search page");
            Thread.sleep(3000);
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("span.commonModal__close"))
                );
            Thread.sleep(3000);

                closeBtn.click();
            
        }

        catch (Exception e) {
            log.error("Failed in Given step: ", e);
            Assert.fail("Given step failed");
        }
    }

    @When("the user selects {string} as the departure city")
    public void the_user_selects_as_the_departure_city(String city) {

        try {
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(page.fromCityField())).click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.visibilityOf(page.fromInputBox())).sendKeys(city);
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(page.citySuggestion(city))).click();

            log.info("Departure city selected successfully");
        }

        catch (Exception e) {
            log.error("Departure selection failed: ", e);
            Assert.fail("Departure step failed");
        }
    }

    @When("selects {string} as the destination city")
    public void selects_as_the_destination_city(String city) {

        try {
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(page.toCityField())).click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.visibilityOf(page.toInputBox())).sendKeys(city);
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(page.citySuggestion(city))).click();
            
            log.info("Destination city selected successfully");
        }

        catch (Exception e) {
            log.error("Destination selection failed: ", e);
            Assert.fail("Destination step failed");
        }
    }
    
    @When("date is picked")
    public void date_is_picked() {
    	try {
    		Thread.sleep(2000);
    		wait.until(ExpectedConditions.elementToBeClickable(page.flightDate())).click();
    	}
    	catch(Exception e) {
    		log.error("Date selection failed: ", e);
            Assert.fail("Date step failed");
    	}
    }

    @When("clicks on the search button")
    public void clicks_on_the_search_button() {

        try {

            log.info("Clicking search button");
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(page.searchButton())).click();
            System.out.println(driver.getCurrentUrl());
        }

        catch (Exception e) {
            log.error("Search click failed", e);
            Assert.fail("Search click failed");
        }
    }

    @Then("all listed flights should match the selected locations")
    public void all_listed_flights_should_match_the_selected_locations() {

        try {

            log.info("Validating filtered flight results for Pune → Chennai");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.listingCard")));
            List<WebElement> departureLocation = page.flightsListPune();
            List<WebElement> arrivalLocation = page.flightsListChennai();

            
            Assert.assertTrue(departureLocation.size() > 0 && arrivalLocation.size() > 0,"No flight results found!");

            for (WebElement route : departureLocation) {

                String routeText = route.getText();


                Assert.assertTrue(
                        routeText.contains("Pune"),"Invalid route found: " + routeText);
            }
            for (WebElement route : arrivalLocation) {

                String routeText = route.getText();


                Assert.assertTrue(
                        routeText.contains("Chennai"),"Invalid route found: " + routeText);
            }

            log.info("All flights correctly filtered: Pune → Chennai");

        }

        catch (Exception e) {

            log.error("Flight validation failed: ", e);
            Assert.fail("Validation failed");
        }
    }

}
