package dk.zenlike.wineprices.sources.service.impl;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.impl.PriceSourceImpl;
import dk.zenlike.wineprices.sources.service.WinePriceService;

import java.util.ArrayList;
import java.util.List;

public class WinePriceServiceImpl implements WinePriceService {

    public List<WinePrice> getPrices(SourceConfiguration sourceConfig) {
        List<WinePrice> winePrices = new ArrayList<>();

        final String priceSelector = sourceConfig.getPriceSelector();
        final String amountSelector = sourceConfig.getAmountSelector();
        final String nameSelector = sourceConfig.getNameSelector();

        WinePrice soulo2013Price = new WinePrice(sourceConfig.getSourceName());
        WinePrice soulo2012Price = new WinePrice(sourceConfig.getSourceName());
        WinePrice guigalPrice = new WinePrice(sourceConfig.getSourceName());
        WinePrice guigalDitPrice = new WinePrice(sourceConfig.getSourceName());

        soulo2013Price.setUrl("http://www.philipsonwine.com/2013-suolo-tenuta-di-argiano/65701313");
        soulo2012Price.setUrl("http://www.philipsonwine.com/2012-suolo-tenuta-di-argiano/65703312");
        guigalPrice.setUrl("http://www.philipsonwine.com/2013-saint-joseph-rouge-guigal/47991313");
        guigalDitPrice.setUrl("http://www.philipsonwine.com/2014-saint-joseph-rouge-lieu-dit-saint-joseph-e-guigal/48001314");

        winePrices.add(soulo2013Price);
        winePrices.add(soulo2012Price);
        winePrices.add(guigalPrice);
        winePrices.add(guigalDitPrice);

        return winePrices;
    }

}
