package dk.zenlike.wineprices.persistence.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.persistence.WinePriceService;

public class WinePriceServiceImpl implements WinePriceService {

    @Override
    public WinePrice getWinePriceById(String id) {
        return ObjectifyService.ofy().load().key(Key.create(WinePrice.class, id)).now();
    }

    @Override
    public WinePrice getWinePriceByName(String name) {
        return ObjectifyService.ofy().load().type(WinePrice.class).filter("name", name).first().now();
    }

    @Override
    public void saveWinePrice(WinePrice winePrice) {
        ObjectifyService.ofy().save().entity(winePrice).now();
    }

    @Override
    public void deleteWinePrice(WinePrice winePrice) {
        ObjectifyService.ofy().delete().entity(winePrice).now();
    }

}
