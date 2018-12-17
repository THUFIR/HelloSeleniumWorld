
package HelloSeleniumWorld;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newproject.Selenium101;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private Selenium101 s = new Selenium101();

    public static void main(String... args)  {
        new App().init();
    }

    private void init()  {
        LOGGER.info("started app");
        try {
            s.initializeProperties();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.severe("failed to load properties file");
        }
        s.browse();
    }
}
