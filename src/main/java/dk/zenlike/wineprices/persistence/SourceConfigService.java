package dk.zenlike.wineprices.persistence;

import java.util.List;

import dk.zenlike.wineprices.model.SourceConfiguration;

public interface SourceConfigService {

    SourceConfiguration getSourceConfiguration(String source);

    List<SourceConfiguration> getSourceConfigurations();

}
