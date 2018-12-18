package HelloSeleniumWorld;

import browse.Selenium101;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.Properties;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String... args) throws IOException {
        new App().init();
    }

    private void init() throws IOException {
        LOG.fine("started app");
        properties.load(App.class.getResourceAsStream("/selenium.properties"));
        Selenium101 s = Selenium101.defaultPage(properties);
        s.browseToURL();
    }
}