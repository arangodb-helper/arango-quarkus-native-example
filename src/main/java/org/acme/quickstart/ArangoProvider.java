package org.acme.quickstart;

import com.arangodb.ArangoDB;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

/**
 * @author Michele Rastelli
 */
@Dependent
public class ArangoProvider {

    @Produces
    public ArangoDB arangoDB(@ConfigProperties final ArangoConfig config) {
        return new ArangoDB.Builder()
                .loadProperties(config)
                .build();
    }

}
