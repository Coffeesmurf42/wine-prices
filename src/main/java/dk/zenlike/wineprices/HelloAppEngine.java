package dk.zenlike.wineprices;

import com.google.appengine.api.utils.SystemProperty;
import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.service.PriceSourceService;
import dk.zenlike.wineprices.sources.service.impl.PhilipsonPriceSourceServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {



        Properties properties = System.getProperties();

        response.setContentType("text/plain");
        response.getWriter().println("Hello App Engine - Standard using "
                + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
    }

    public static String getInfo() {
        return "Version: " + System.getProperty("java.version")
                + " OS: " + System.getProperty("os.name")
                + " User: " + System.getProperty("user.name");
    }

    public static String getPrice() {
        String priceStr = "";

        PriceSourceService priceSourceService = new PhilipsonPriceSourceServiceImpl();
        PriceSource priceSource = priceSourceService.getPriceSources().get(0);

        final String wineUrlStr = priceSource.getUrl();

        URL wineUrl = null;

        try {
            wineUrl = new URL(wineUrlStr);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL for wine page");
        }

        if (wineUrl != null) {
            try {
                Document document = Jsoup.parse(wineUrl, 10000);
                Elements elements = document.select(".price");
                Element priceElement = elements.get(0);
                priceStr = priceElement.text();
            }
            catch (IOException e) {
                System.out.println("IO Exception while connecting to wine page");
            }
        }


        return priceStr;
    }



}
