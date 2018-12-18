package browse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Selenium101 {

    private static final Logger LOG = Logger.getLogger(Selenium101.class.getName());

    Properties props = new Properties();
    FirefoxBinary firefoxBinary = null;
    FirefoxOptions firefoxOptions = null;
    WebDriver webDriver = null;

    public Selenium101() {
    }

    public void initializeProperties() throws IOException {
        props.load(Selenium101.class.getResourceAsStream("/selenium.properties"));
        LOG.info(props.toString());
    }

    public void browse() throws MalformedURLException {
        URL url = new URL(props.getProperty("url"));
        firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        System.setProperty(props.getProperty("driver"), props.getProperty("gecko"));
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        webDriver = new FirefoxDriver(firefoxOptions);
        webDriver.get(url.toString());
        LOG.info(webDriver.getTitle());
        LOG.info(webDriver.getCurrentUrl().toLowerCase());
        login();
        getStatusText();
        webDriver.close();
    }

    private void login() {
        String username = props.getProperty("usr");
        String password = props.getProperty("pwd");
        WebElement userName_editbox = webDriver.findElement(By.id("usr"));
        WebElement password_editbox = webDriver.findElement(By.id("pwd"));
        WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Login']"));
        userName_editbox.sendKeys(username);
        password_editbox.sendKeys(password);
        submit_button.click();

    }

    private void getStatusText() {
        String statusText = webDriver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
        LOG.info(statusText);
        assert "WELCOME :)".equals(statusText);
    }
}
