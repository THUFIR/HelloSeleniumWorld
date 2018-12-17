package newproject;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
        System.setProperty(props.getProperty("driver"), props.getProperty("gecko"));
        WebDriver driver = new FirefoxDriver();
        driver.get(props.getProperty("url"));
        LOG.info(driver.getTitle());
        driver.close();
    }

}
