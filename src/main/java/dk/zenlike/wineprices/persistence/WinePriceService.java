package dk.zenlike.wineprices.persistence;

import dk.zenlike.wineprices.model.WinePrice;

public interface WinePriceService {

    WinePrice getWinePriceById(String id);
    WinePrice getWinePriceByName(String name);

    void saveWinePrice(WinePrice winePrice);

    void deleteWinePrice(WinePrice winePrice);

}
