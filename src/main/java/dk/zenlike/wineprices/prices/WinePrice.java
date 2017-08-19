package dk.zenlike.wineprices.prices;

import java.io.Serializable;

public class WinePrice implements Serializable {

    private String name;
    private String url;
    private String price;
    private String amount;
    private String wineName;

    public WinePrice() {};

    public WinePrice(String name, String url, String price, String amount, String wineName) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.amount = amount;
        this.wineName = wineName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    @Override
    public String toString() {
        return "WinePrice{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", wineName='" + wineName + '\'' +
                '}';
    }

}
