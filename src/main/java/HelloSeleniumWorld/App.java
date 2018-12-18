
package HelloSeleniumWorld;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import browse.Selenium101;
import java.net.MalformedURLException;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Selenium101 s = new Selenium101();

    public static void main(String... args)  {
        new App().init();
    }

    private void init()  {
        LOG.info("started app");
        try {
            s.initializeProperties();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            LOG.severe("failed to load properties file");
        }
        try {
            s.browse();
        } catch (MalformedURLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "the URL failed to load", ex);
        }
    }
}
