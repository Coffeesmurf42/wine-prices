package dk.zenlike.wineprices.sources;

public interface PriceSource {

    String getName();
    String getUrl();

    String getPriceSelector();
    String getAmountSelector();
    String getWineNameSelector();

}
