package dk.zenlike.wineprices.sources.config.impl;

import dk.zenlike.wineprices.sources.config.SourceConfiguration;

public class PhilipsonSourceConfiguration implements SourceConfiguration {

    public static final String SOURCE_NAME = "Philipson";

    private static final String PRICE_SELECTOR = ".price";
    private static final String AMOUNT_SELECTOR = ".product-price-info";
    private static final String NAME_SELECTOR = ".wine-name";

    @Override
    public String getSourceName() {
        return SOURCE_NAME;
    }

    @Override
    public String getPriceSelector() {
        return PRICE_SELECTOR;
    }

    @Override
    public String getAmountSelector() {
        return AMOUNT_SELECTOR;
    }

    @Override
    public String getNameSelector() {
        return NAME_SELECTOR;
    }
}
