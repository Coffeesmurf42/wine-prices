package dk.zenlike.wineprices.sources.service.impl;

import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.impl.PriceSourceImpl;
import dk.zenlike.wineprices.sources.service.PriceSourceService;

import java.util.ArrayList;
import java.util.List;

public class PriceSourceServiceImpl implements PriceSourceService {

    public List<PriceSource> getPriceSources() {
        List<PriceSource> priceSources = new ArrayList<>();

        PriceSource soulo2013PriceSource = new PriceSourceImpl("Suolo 2013", "http://www.philipsonwine.com/2013-suolo-tenuta-di-argiano/65701313", ".price", ".product-price-info", ".wine-name");
        PriceSource soulo2012PriceSource = new PriceSourceImpl("Suolo 2012", "http://www.philipsonwine.com/2012-suolo-tenuta-di-argiano/65703312", ".price", ".product-price-info", ".wine-name");

        priceSources.add(soulo2013PriceSource);
        priceSources.add(soulo2012PriceSource);

        return priceSources;
    }

}
