package dk.zenlike.wineprices.persistence;

import dk.zenlike.wineprices.model.WinePrice;

import java.util.List;

public interface WinePriceService {

    WinePrice getWinePriceById(String id);

    WinePrice getWinePriceByName(String name);

    List<WinePrice> getWinePricesBySource(String source);

    void saveWinePrice(WinePrice winePrice);

    void deleteWinePrice(WinePrice winePrice);

}
