package pageobject;

import base.Base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlightSelectionPage extends Base {

	By fromCity = By.id("fromCity");
	By toCity = By.id("toCity");
	By frominputBox = By.id("fromCity");
	By toInputBox = By.id("toCity");
	By pricePicker = By.xpath(" //div[contains(@class,'DayPicker-Day')]//p[2][normalize-space()!='']");
	By closeBtn =  By.cssSelector("span.commonModal__close");

	public WebElement closePopUp() {
		return driver.findElement(closeBtn);
	}
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
    
    public List<WebElement> flightPrice() {
    	return driver.findElements(pricePicker);
    }

    public WebElement citySuggestion() {
        return driver.findElement(By.xpath("//div[contains(@class,'revampedSuggestionHeader')][1]"));
    }

   
}
