package dk.zenlike.wineprices.sources.impl;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import dk.zenlike.wineprices.sources.PriceSource;

@Entity
public class PriceSourceImpl implements PriceSource {

    @Id private String id;

    @Index private String name;
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
