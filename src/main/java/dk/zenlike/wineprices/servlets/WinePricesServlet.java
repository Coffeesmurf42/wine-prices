package dk.zenlike.wineprices.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import dk.zenlike.wineprices.model.WinePrice;

import dk.zenlike.wineprices.model.SourceConfiguration;

import dk.zenlike.wineprices.persistence.SourceConfigService;
import dk.zenlike.wineprices.persistence.WinePriceService;
import dk.zenlike.wineprices.persistence.impl.SourceConfigServiceImpl;
import dk.zenlike.wineprices.persistence.impl.WinePriceServiceImpl;
import dk.zenlike.wineprices.sources.service.FetcherService;

import dk.zenlike.wineprices.sources.service.impl.FetcherServiceImpl;

@WebServlet(name = "WinePrices", value = "/wineprices")
public class WinePricesServlet extends HttpServlet {

    private static WinePriceService winePriceService = new WinePriceServiceImpl();
    private static FetcherService fetcherService = new FetcherServiceImpl();
    private static SourceConfigService sourceConfigService = new SourceConfigServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String source = request.getParameter("source");

        if (source != null && !"".equals(source)) {
            final SourceConfiguration sourceConfig = sourceConfigService.getSourceConfiguration(source);

            if (sourceConfig == null) {
                addPhilipsonSourceConfig(); // at least we'll have this one..
                return;
            }

            final List<WinePrice> winePrices = getWinePrices(sourceConfig);

            request.setAttribute("winePrices", winePrices);
            request.setAttribute("wineCount", winePrices.size());
            request.setAttribute("sourceName", sourceConfig.getSourceName());

            request.getRequestDispatcher("wineprices.jsp").forward(request, response);
        }
        else {
            badRequest(response, "No source parameter in request!");
        }
    }

    private List<WinePrice> getWinePrices(SourceConfiguration sourceConfig) {
        final List<WinePrice> winePrices = winePriceService.getWinePricesBySource(sourceConfig.getSourceName());

        // fetch the newest prices
        for (WinePrice currWinePrice: winePrices) {
            fetcherService.updateWinePrice(currWinePrice, sourceConfig);
        }

        return winePrices;
    }

    private void badRequest(HttpServletResponse response, String msg) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
    }

    private void serverError(HttpServletResponse response, String msg) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }

    private void addPhilipsonSourceConfig() {
        SourceConfiguration sourceConfiguration = new SourceConfiguration();

        sourceConfiguration.setSourceName("Philipson");
        sourceConfiguration.setPriceSelector(".price");
        sourceConfiguration.setAmountSelector(".product-price-info");
        sourceConfiguration.setNameSelector(".wine-name");

        ObjectifyService.ofy().save().entity(sourceConfiguration).now();
    }

    private void addWines(SourceConfiguration sourceConfig) {
        WinePrice soulo2013Price = new WinePrice(sourceConfig.getSourceName());
        WinePrice soulo2012Price = new WinePrice(sourceConfig.getSourceName());
        WinePrice guigalPrice = new WinePrice(sourceConfig.getSourceName());
        WinePrice guigalDitPrice = new WinePrice(sourceConfig.getSourceName());

        soulo2013Price.setUrl("http://www.philipsonwine.com/2013-suolo-tenuta-di-argiano/65701313");
        soulo2012Price.setUrl("http://www.philipsonwine.com/2012-suolo-tenuta-di-argiano/65703312");
        guigalPrice.setUrl("http://www.philipsonwine.com/2013-saint-joseph-rouge-guigal/47991313");
        guigalDitPrice.setUrl("http://www.philipsonwine.com/2014-saint-joseph-rouge-lieu-dit-saint-joseph-e-guigal/48001314");

        winePriceService.saveWinePrice(soulo2013Price);
        winePriceService.saveWinePrice(soulo2012Price);
        winePriceService.saveWinePrice(guigalPrice);
        winePriceService.saveWinePrice(guigalDitPrice);
    }

}
