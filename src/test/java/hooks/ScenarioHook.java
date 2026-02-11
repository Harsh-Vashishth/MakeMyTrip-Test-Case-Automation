package hooks;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.Base;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

public class ScenarioHook extends Base {

    @Before
    public void setup() throws Exception {

        initializeDriver();
        openApplication();
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
    	 if (!scenario.isFailed()) {
			  	Thread.sleep(4000);
	            byte[] screenshot =
	                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	            Allure.addAttachment(
	                    "Screenshot",
	                    new ByteArrayInputStream(screenshot)
	            );
	        }
        quitDriver();
    }
    
	@BeforeAll
	public static void suiteStart() {
		System.out.println("Test suite started");
	}
	@AfterAll
	public static void suiteEnd() {
		System.out.println("Test suite ended");
	}
}
