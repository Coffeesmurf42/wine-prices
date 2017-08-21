package dk.zenlike.wineprices.sources.service.impl;

import dk.zenlike.wineprices.sources.PriceSource;
import dk.zenlike.wineprices.sources.config.SourceConfiguration;
import dk.zenlike.wineprices.sources.config.impl.PhilipsonSourceConfiguration;
import dk.zenlike.wineprices.sources.impl.PriceSourceImpl;
import dk.zenlike.wineprices.sources.service.PriceSourceService;

import java.util.ArrayList;
import java.util.List;

public class PhilipsonPriceSourceServiceImpl implements PriceSourceService {

    private static final SourceConfiguration sourceConfig = new PhilipsonSourceConfiguration();

    public List<PriceSource> getPriceSources() {
        List<PriceSource> priceSources = new ArrayList<>();

        PriceSource soulo2013PriceSource = new PriceSourceImpl("Suolo 2013", "http://www.philipsonwine.com/2013-suolo-tenuta-di-argiano/65701313", sourceConfig.getPriceSelector(), sourceConfig.getAmountSelector(), sourceConfig.getNameSelector());
        PriceSource soulo2012PriceSource = new PriceSourceImpl("Suolo 2012", "http://www.philipsonwine.com/2012-suolo-tenuta-di-argiano/65703312", sourceConfig.getPriceSelector(), sourceConfig.getAmountSelector(), sourceConfig.getNameSelector());

        PriceSource guigalPriceSource = new PriceSourceImpl("Guigal St. Joseph", "http://www.philipsonwine.com/2013-saint-joseph-rouge-guigal/47991313", sourceConfig.getPriceSelector(), sourceConfig.getAmountSelector(), sourceConfig.getNameSelector());
        PriceSource guigalDitPriceSource = new PriceSourceImpl("Guigal St. Joseph Lieu Dit", "http://www.philipsonwine.com/2014-saint-joseph-rouge-lieu-dit-saint-joseph-e-guigal/48001314", sourceConfig.getPriceSelector(), sourceConfig.getAmountSelector(), sourceConfig.getNameSelector());

        priceSources.add(soulo2013PriceSource);
        priceSources.add(soulo2012PriceSource);
        priceSources.add(guigalPriceSource);
        priceSources.add(guigalDitPriceSource);

        return priceSources;
    }

}
