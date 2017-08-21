package dk.zenlike.wineprices.persistence;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import com.googlecode.objectify.ObjectifyService;

import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.model.SourceConfiguration;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(WinePrice.class);
        ObjectifyService.register(SourceConfiguration.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }
}