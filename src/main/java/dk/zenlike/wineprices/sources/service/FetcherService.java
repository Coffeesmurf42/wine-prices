package dk.zenlike.wineprices.sources.service;

import dk.zenlike.wineprices.prices.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;

public interface FetcherService {

    WinePrice getWinePrice(PriceSource priceSource);

}
