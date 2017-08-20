package dk.zenlike.wineprices.sources.config;

public interface SourceConfiguration {

    String getSourceName();

    String getPriceSelector();
    String getAmountSelector();
    String getNameSelector();

}
