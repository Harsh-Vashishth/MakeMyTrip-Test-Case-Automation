package pageobject;

import base.Base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlightSelectionPage extends Base {

	By fromCity = By.id("fromCity");
	By toCity = By.id("toCity");
	By frominputBox = By.xpath("//input[@placeholder='From']");
	By toInputBox = By.xpath("//input[@placeholder='To']");
	By searchBtn = By.xpath("//a[text()='Search']");
	By flightsListPune = By.xpath("//div[contains(@class,'listingCard')]//font[text()='Pune']");
	By flightsListChennai = By.xpath("//div[contains(@class,'listingCard')]//font[text()='Chennai']");
	By datePicker = By.xpath("//div[contains(@class,'DayPicker-Day') and @aria-label='Wed Mar 11 2026']");

    public WebElement fromCityField() {
        return driver.findElement(fromCity);
    }

    public WebElement toCityField() {
        return driver.findElement(toCity);
    }

    public WebElement fromInputBox() {
        return driver.findElement(frominputBox);
    }

    public WebElement toInputBox() {
        return driver.findElement(toInputBox);
    }
    
    public WebElement flightDate() {
    	return driver.findElement(datePicker);
    }

    public WebElement citySuggestion(String city) {
        return driver.findElement(By.xpath("//p[contains(text(),'" + city + "')]"));
    }

    public WebElement searchButton() {
        return driver.findElement(searchBtn);
    }
    
    public List<WebElement> flightsListPune(){
    	return driver.findElements(flightsListPune);
    }
    public List<WebElement> flightsListChennai(){
    	return driver.findElements(flightsListChennai);
    }
}
