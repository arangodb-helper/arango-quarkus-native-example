package org.acme.quickstart;

import com.arangodb.ArangoDB;
import com.arangodb.Protocol;
import com.arangodb.mapping.ArangoJack;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

/**
 * @author Michele Rastelli
 */
@Dependent
public class ArangoConfiguration {

    @Produces
    public ArangoDB arangoDB() {
        return new ArangoDB.Builder()
                .password("test")
                .serializer(new ArangoJack())
                .useProtocol(Protocol.HTTP_JSON)
                .build();
    }

}
