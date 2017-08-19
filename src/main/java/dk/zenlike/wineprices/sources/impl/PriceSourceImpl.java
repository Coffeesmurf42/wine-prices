package dk.zenlike.wineprices.sources.impl;

import dk.zenlike.wineprices.sources.PriceSource;

public class PriceSourceImpl implements PriceSource {

    private String name;
    private String url;

    private String priceSelector;
    private String amountSelector;
    private String wineNameSelector;

    public PriceSourceImpl(String name, String url, String priceSelector, String amountSelector, String wineNameSelector) {
        this.name = name;
        this.url = url;
        this.priceSelector = priceSelector;
        this.amountSelector = amountSelector;
        this.wineNameSelector = wineNameSelector;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getPriceSelector() {
        return priceSelector;
    }

    @Override
    public String getAmountSelector() {
        return amountSelector;
    }

    @Override
    public String getWineNameSelector() {
        return wineNameSelector;
    }

}
