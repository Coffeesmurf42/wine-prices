package dk.zenlike.wineprices.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import dk.zenlike.wineprices.model.WinePrice;

import dk.zenlike.wineprices.sources.PriceSource;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.sources.service.FetcherService;
import dk.zenlike.wineprices.sources.service.WinePriceService;

import dk.zenlike.wineprices.sources.service.impl.FetcherServiceImpl;
import dk.zenlike.wineprices.sources.service.impl.WinePriceServiceImpl;

@WebServlet(name = "PhilipsonWinePrices", value = "/wineprices")
public class WinePricesServlet extends HttpServlet {

    private static WinePriceService winePriceService = new WinePriceServiceImpl();
    private static FetcherService fetcherService = new FetcherServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String source = request.getParameter("source");

        if (source != null && !"".equals(source)) {
            final SourceConfiguration sourceConfig = ObjectifyService.ofy().load().key(Key.create(SourceConfiguration.class, source)).now();

            if (sourceConfig == null) {
                addPhilipsonSourceConfig(); // at least we'll have this one..
                return;
            }

            List<WinePrice> winePrices = getWinePrices(sourceConfig);

            request.setAttribute("winePrices", winePrices);
            request.getRequestDispatcher("wineprices.jsp").forward(request, response);
        }
        else {
            badRequest(response, "No source parameter in request!");
        }

        //addPhilipsonSourceConfig();
    }

    private List<WinePrice> getWinePrices(SourceConfiguration sourceConfig) {
        final List<WinePrice> winePrices = winePriceService.getPrices(sourceConfig);

        // fetch the newest price
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

}
