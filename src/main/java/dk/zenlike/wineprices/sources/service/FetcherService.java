package dk.zenlike.wineprices.sources.service;

import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;

public interface FetcherService {

    WinePrice getWinePrice(PriceSource priceSource);

}
