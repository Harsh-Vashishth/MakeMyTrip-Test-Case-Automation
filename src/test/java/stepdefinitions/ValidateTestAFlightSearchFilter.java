package stepdefinitions;

import io.cucumber.java.en.*;
import utils.ExcelUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import pageobject.FlightSelectionPage;
import base.Base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidateTestAFlightSearchFilter extends Base {

    private static final Logger log = LogManager.getLogger(ValidateTestAFlightSearchFilter.class);

    private FlightSelectionPage page;
    private WebDriverWait wait;

    private String fromCity;
    private String toCity;

    public ValidateTestAFlightSearchFilter() {

        page = new FlightSelectionPage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        ExcelUtils excel = new ExcelUtils("MakeMyTrip.xlsx", "Sheet1");

        Map<String, String> data = excel.getRowData(1);

        fromCity = data.get("From");
        toCity = data.get("To");
    }


    @Given("the user is on the MakeMyTrip flight search page")
    public void the_user_is_on_the_MakeMyTrip_flight_search_page() {

        try {

            log.info("User landed on flight search page");

            wait.until(ExpectedConditions.elementToBeClickable(page.closePopUp())).click();;
        }

        catch (Exception e) {

            log.error("Given step failed", e);
            Assert.fail("Given step failed");
        }
    }

    @When("the user selects Pune as the departure city")
    public void the_user_selects_Pune_as_the_departure_city() {

        try {

            log.info("Selecting departure city...");

            wait.until(ExpectedConditions.visibilityOf(page.fromCityField())).click();
            
            Thread.sleep(3000);
          
            wait.until( ExpectedConditions.visibilityOf(page.fromInputBox())).sendKeys(fromCity);
            System.out.println(fromCity+" entered");

            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(page.citySuggestion())).click();

            log.info("Departure selected: " + fromCity);
        }

        catch (Exception e) {

            log.error("Departure failed", e);
            e.printStackTrace();
            Assert.fail("Departure failed");
        }
    }



    @When("selects Chennai as the destination city")
    public void selects_Chennai_as_the_destination_city() {

        try {
            log.info("Selecting destination city...");

            wait.until(ExpectedConditions.visibilityOf(page.toCityField())).click();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.visibilityOf(page.toInputBox())).sendKeys(toCity);
            System.out.println(toCity+" entered");
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(page.citySuggestion())).click();

            log.info("Destination selected: " + toCity);
        }

        catch (Exception e) {

            log.error("Destination selection failed", e);
            Assert.fail("Destination failed");
        }
    }
    
    @Then("cheapest flight in next 15 days is picked")
    public void cheapest_flight_in_next_15_days_is_picked() {
    	try {
    		Thread.sleep(2000);
    		List<WebElement> prices = page.flightPrice();
    		List<Integer> values = new ArrayList<>();
    		
    		for(int i = 0; i< 15; i++) {
    			String text = prices.get(i).getText();
    			String clean = text.replaceAll("[^0-9]", ""); //removed commas from prices using regex
    			values.add(Integer.valueOf(clean));
    		}
    		
    		Collections.sort(values);
    		int cheapest = values.get(0);
    		
    		System.out.println("Cheapest flight: "+ cheapest);
    	}
    	catch(Exception e){
    		log.error("Cheapest Price Calculation failed",e);
    		Assert.fail("Cheapest Price Calculation failed");
    	}
    }
}
