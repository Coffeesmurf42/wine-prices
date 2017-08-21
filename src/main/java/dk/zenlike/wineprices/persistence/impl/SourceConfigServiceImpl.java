package dk.zenlike.wineprices.persistence.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.persistence.SourceConfigService;

import java.util.List;

public class SourceConfigServiceImpl implements SourceConfigService {

    public SourceConfiguration getSourceConfiguration(String source) {
        return ObjectifyService.ofy().load().key(Key.create(SourceConfiguration.class, source)).now();
    }

    public List<SourceConfiguration> getSourceConfigurations() {
        return ObjectifyService.ofy().load().type(SourceConfiguration.class).list();
    }

}
