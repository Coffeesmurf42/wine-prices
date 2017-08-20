package dk.zenlike.wineprices.sources.service.impl;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.time.LocalDateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.config.impl.PhilipsonSourceConfiguration;
import dk.zenlike.wineprices.sources.service.FetcherService;

public class FetcherServiceImpl implements FetcherService {

    private static final int CONNECTION_TIMEOUT = 10000;

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
                Document document = Jsoup.parse(wineUrl, CONNECTION_TIMEOUT);

                priceStr = getText(document, priceSource.getPriceSelector());
                amountStr = getText(document, priceSource.getAmountSelector());
                nameStr = getText(document, priceSource.getWineNameSelector());
            }
            catch (IOException e) {
                System.out.println("IO Exception while connecting to wine page");
            }
        }

        WinePrice winePrice = new WinePrice(PhilipsonSourceConfiguration.SOURCE_NAME);

        winePrice.setName(nameStr);
        winePrice.setUrl(priceSource.getUrl());
        winePrice.setPrice(priceStr);
        winePrice.setAmount(amountStr);
        winePrice.setTimestamp(LocalDateTime.now());

        return winePrice;
    }

    private String getText(Document document, String selector) {
        return document.select(selector).get(0).text();
    }

}
