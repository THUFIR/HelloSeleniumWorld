package browse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private Properties properties = new Properties();
    private FirefoxBinary firefoxBinary = null;
    private FirefoxOptions firefoxOptions = null;
    private WebDriver webDriver = null;
    private URL url = null;
    private String driver = null;
    private String gecko = null;
    private final List<String> commandLineOptions = new ArrayList<>();
    private String username = null;
    private String password = null;

    private Selenium101() {
    }

    private Selenium101(Properties properties) throws MalformedURLException {
        this.properties = properties;
        LOG.fine(properties.toString());
        url = new URL(properties.getProperty("url"));
        driver = properties.getProperty("driver");
        gecko = properties.getProperty("gecko");
        username = properties.getProperty("usr");
        password = properties.getProperty("pwd");
        String commandLineOption = properties.getProperty("option01");
        commandLineOptions.add(commandLineOption);
    }

    public static Selenium101 defaultPage(Properties properties) throws MalformedURLException {
        return new Selenium101(properties);
    }

    public void browseToURL() throws MalformedURLException {
        firefoxBinary = new FirefoxBinary();
        commandLineOptions.forEach((commandLineOption) -> {
            LOG.fine(commandLineOption.toString());
            firefoxBinary.addCommandLineOptions(commandLineOption);
        });
        System.setProperty(driver, gecko);
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        webDriver = new FirefoxDriver(firefoxOptions);
        webDriver.get(url.toString());
        LOG.fine(webDriver.getTitle());
        LOG.fine(webDriver.getCurrentUrl().toLowerCase());
        login();
        getStatusText();
        webDriver.close();
    }

    private void login() {
        WebElement userName_editbox = webDriver.findElement(By.id("usr"));
        WebElement password_editbox = webDriver.findElement(By.id("pwd"));
        WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Login']"));
        LOG.fine(username);
        LOG.fine(password);
        userName_editbox.sendKeys(username);
        password_editbox.sendKeys(password);
        submit_button.click();

    }

    private void getStatusText() {
        String statusText = webDriver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
        LOG.info(statusText);
        assert "WELCOME :)".equals(statusText) : "fails";
    }
}
