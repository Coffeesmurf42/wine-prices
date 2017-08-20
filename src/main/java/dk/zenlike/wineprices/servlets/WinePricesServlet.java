package dk.zenlike.wineprices.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.zenlike.wineprices.model.WinePrice;

import dk.zenlike.wineprices.sources.PriceSource;

import dk.zenlike.wineprices.sources.service.FetcherService;
import dk.zenlike.wineprices.sources.service.PriceSourceService;

import dk.zenlike.wineprices.sources.service.impl.FetcherServiceImpl;
import dk.zenlike.wineprices.sources.service.impl.PhilipsonPriceSourceServiceImpl;

@WebServlet(name = "PhilipsonWinePrices", value = "/wineprices")
public class WinePricesServlet extends HttpServlet {

    private static PriceSourceService priceSourceService = new PhilipsonPriceSourceServiceImpl();
    private static FetcherService fetcherService = new FetcherServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String source = request.getParameter("source");

        if (source != null && !"".equals(source)) {
            List<WinePrice> winePrices = getWinePrices(source);

            request.setAttribute("winePrices", winePrices);
            request.getRequestDispatcher("wineprices.jsp").forward(request, response);
        }
        else {
            badRequest(response, "No source parameter in request!");
        }
    }

    private List<WinePrice> getWinePrices(String source) {
        // TODO: Fetch prices based on just a source ID like Philipson - we shouldn't care about source configuration and fetching prices at this point
        List<WinePrice> winePrices = new ArrayList<>();

        WinePrice currWinePrice;

        for (PriceSource priceSource: priceSourceService.getPriceSources()) {
            currWinePrice = fetcherService.getWinePrice(priceSource);
            winePrices.add(currWinePrice);
        }

        return winePrices;
    }

    private void badRequest(HttpServletResponse response, String msg) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
    }

}
