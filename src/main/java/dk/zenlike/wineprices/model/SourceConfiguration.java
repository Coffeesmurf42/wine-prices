package dk.zenlike.wineprices.model;

import com.googlecode.objectify.annotation.Entity;

import com.googlecode.objectify.annotation.Id;

@Entity
public class SourceConfiguration {

    @Id private String sourceName;

    private String priceSelector;
    private String amountSelector;
    private String nameSelector;

    // default constructor
    public SourceConfiguration() {};

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getPriceSelector() {
        return priceSelector;
    }

    public void setPriceSelector(String priceSelector) {
        this.priceSelector = priceSelector;
    }

    public String getAmountSelector() {
        return amountSelector;
    }

    public void setAmountSelector(String amountSelector) {
        this.amountSelector = amountSelector;
    }

    public String getNameSelector() {
        return nameSelector;
    }

    public void setNameSelector(String nameSelector) {
        this.nameSelector = nameSelector;
    }

    @Override
    public String toString() {
        return "SourceConfiguration{" +
                "sourceName='" + sourceName + '\'' +
                ", priceSelector='" + priceSelector + '\'' +
                ", amountSelector='" + amountSelector + '\'' +
                ", nameSelector='" + nameSelector + '\'' +
                '}';
    }

}