package dk.zenlike.wineprices.model;

import java.io.Serializable;

import java.util.UUID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class WinePrice implements Serializable {

    @Parent Key<WineSource> source;

    @Id private String id;

    private String name;
    private String url;
    private String price;
    private String amount;
    private String wineName;

    @Index private LocalDateTime timestamp;

    // default constructor
    public WinePrice() {};

    public WinePrice(String sourceId) {
        this.id = UUID.randomUUID().toString();
        this.source = Key.create(WineSource.class, sourceId);
    }

    public String getId() {
        return id;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
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
