package dk.zenlike.wineprices.sources.service;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;

import java.util.List;

public interface WinePriceService {

    List<WinePrice> getPrices(SourceConfiguration sourceConfig);

}
