package dk.zenlike.wineprices.sources.service;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;

public interface FetcherService {

    WinePrice getWinePrice(String urlStr, SourceConfiguration sourceConfig);

    void updateWinePrice(WinePrice winePrice, SourceConfiguration sourceConfig);

}
