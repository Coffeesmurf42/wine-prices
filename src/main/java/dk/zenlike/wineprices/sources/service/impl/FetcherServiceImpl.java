package dk.zenlike.wineprices.sources.service.impl;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.time.LocalDateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dk.zenlike.wineprices.model.SourceConfiguration;

import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.service.FetcherService;

public class FetcherServiceImpl implements FetcherService {

    private static final int CONNECTION_TIMEOUT = 10000;

    @Override
    public WinePrice getWinePrice(String urlStr, SourceConfiguration sourceConfig) {
        WinePrice winePrice = new WinePrice(sourceConfig.getSourceName());

        configureWinePrice(winePrice, sourceConfig, urlStr);

        return winePrice;
    }

    @Override
    public void updateWinePrice(WinePrice winePrice, SourceConfiguration sourceConfig) {
        configureWinePrice(winePrice, sourceConfig, winePrice.getUrl());
    }

    private String getText(Document document, String selector) {
        return document.select(selector).get(0).text();
    }

    private void configureWinePrice(WinePrice winePrice, SourceConfiguration sourceConfig, String urlStr) {
        String priceStr = "";
        String amountStr = "";
        String nameStr = "";

        URL wineUrl = null;

        try {
            wineUrl = new URL(urlStr);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL for wine page");
        }

        if (wineUrl != null) {
            try {
                Document document = Jsoup.parse(wineUrl, CONNECTION_TIMEOUT);

                priceStr = getText(document, sourceConfig.getPriceSelector());
                amountStr = getText(document, sourceConfig.getAmountSelector());
                nameStr = getText(document, sourceConfig.getNameSelector());
            }
            catch (IOException e) {
                System.out.println("IO Exception while connecting to wine page");
            }
        }


        winePrice.setName(nameStr);
        winePrice.setUrl(urlStr);
        winePrice.setPrice(priceStr);
        winePrice.setAmount(amountStr);
        winePrice.setTimestamp(LocalDateTime.now());
    }

}
