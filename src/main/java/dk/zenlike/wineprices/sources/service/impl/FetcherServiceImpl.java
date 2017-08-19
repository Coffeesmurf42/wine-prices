package dk.zenlike.wineprices.sources.service.impl;

import dk.zenlike.wineprices.prices.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.service.FetcherService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FetcherServiceImpl implements FetcherService {

    @Override
    public WinePrice getWinePrice(PriceSource priceSource) {
        String priceStr = "";
        String amountStr = "";
        String nameStr = "";

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

                Element priceElement = document.select(priceSource.getPriceSelector()).get(0);
                Element amountElement = document.select(priceSource.getAmountSelector()).get(0);
                Element nameElement = document.select(priceSource.getWineNameSelector()).get(0);

                priceStr = priceElement.text();
                amountStr = amountElement.text();
                nameStr = nameElement.text();
            }
            catch (IOException e) {
                System.out.println("IO Exception while connecting to wine page");
            }
        }

        return new WinePrice(priceSource.getName(), priceSource.getUrl(), priceStr, amountStr, nameStr);
    }

}
