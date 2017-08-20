package dk.zenlike.wineprices.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

@Entity
public class WineSource implements Serializable {

    @Id public String sourceId;

}
