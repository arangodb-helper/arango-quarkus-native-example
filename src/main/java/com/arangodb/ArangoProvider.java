package com.arangodb;

import com.arangodb.http.HttpProtocolConfig;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

/**
 * @author Michele Rastelli
 */
@ApplicationScoped
public class ArangoProvider {

    private final Vertx vertx;

    @Inject
    public ArangoProvider(Vertx vertx) {
        this.vertx = vertx;
    }

    @Produces
    @Dependent
    public ArangoDB arangoDB(@ConfigProperties final ArangoConfig config) throws Exception {
        return new ArangoDB.Builder()
                .loadProperties(config)
                .sslContext(createSslContext(config))
                .protocolConfig(HttpProtocolConfig.builder().vertx(vertx).build())
                .build();
    }

    public void close(@Disposes ArangoDB arangoDB) {
        arangoDB.shutdown();
    }

    private static SSLContext createSslContext(ArangoConfig config) throws Exception {
        var ks = KeyStore.getInstance(config.getTrustStoreType());
        try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config.getTrustStoreFile())) {
            ks.load(is, config.getTrustStorePassword().toCharArray());
        }
        var tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        var sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        return sc;
    }
}
