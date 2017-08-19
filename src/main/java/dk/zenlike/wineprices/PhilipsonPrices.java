package dk.zenlike.wineprices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.zenlike.wineprices.prices.WinePrice;

import dk.zenlike.wineprices.sources.PriceSource;

import dk.zenlike.wineprices.sources.service.FetcherService;
import dk.zenlike.wineprices.sources.service.PriceSourceService;

import dk.zenlike.wineprices.sources.service.impl.FetcherServiceImpl;
import dk.zenlike.wineprices.sources.service.impl.PriceSourceServiceImpl;

@WebServlet(name = "PhilipsonWinePrices", value = "/philipson")
public class PhilipsonPrices extends HttpServlet {

    private static PriceSourceService priceSourceService = new PriceSourceServiceImpl();
    private static FetcherService fetcherService = new FetcherServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("winePrices", getWinePrices());

        request.getRequestDispatcher("wineprices.jsp").forward(request, response);
    }

    public static List<WinePrice> getWinePrices() {
        List<WinePrice> winePrices = new ArrayList<>();

        WinePrice currWinePrice;

        for (PriceSource priceSource: priceSourceService.getPriceSources()) {
            currWinePrice = fetcherService.getWinePrice(priceSource);
            winePrices.add(currWinePrice);
        }

        return winePrices;
    }

}
