package com.arangodb;

import com.arangodb.entity.ArangoDBVersion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


/**
 * @author Michele Rastelli
 */
@ApplicationScoped
public class ArangoService {

    private final ArangoDB arango;

    @Inject
    public ArangoService(final ArangoDB arango) {
        this.arango = arango;
    }

    public ArangoDBVersion getVersion() {
        return arango.getVersion();
    }

}
