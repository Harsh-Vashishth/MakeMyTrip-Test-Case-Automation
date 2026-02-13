package base;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {

    public static WebDriver driver;
    public static Properties prop;

    public static void initializeDriver() throws Exception {

        prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\harsh\\Documents\\workspace-spring-tool-suite-4-4.29.1.RELEASE\\testingproject\\src\\main\\java\\data.properties"));


        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public static void openApplication() {
        driver.get(prop.getProperty("url"));
    }

    public static void quitDriver() {
        if (driver != null)
           driver.quit();
    }
}
