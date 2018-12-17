package newproject;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//https://www.guru99.com/first-webdriver-script.html
public class Selenium101 {

    private static final Logger LOG = Logger.getLogger(Selenium101.class.getName());

    Properties props = new Properties();

    public Selenium101() {
    }

    public void initializeProperties() throws IOException {
        props.load(Selenium101.class.getResourceAsStream("/selenium.properties"));
        LOG.info(props.toString());
    }

    public void browse() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        System.setProperty(props.getProperty("driver"), props.getProperty("gecko"));
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get(props.getProperty("url"));
        LOG.info(driver.getTitle());
        driver.close();
    }

}
