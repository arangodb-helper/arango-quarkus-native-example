package com.arangodb;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

/**
 * @author Michele Rastelli
 */
@Dependent
public class ArangoProvider {

    @Produces
    public ArangoDB arangoDB(@ConfigProperties final ArangoConfig config) throws Exception {
        return new ArangoDB.Builder()
                .loadProperties(config)
                .sslContext(createSslContext(config))
                .build();
    }

    private static SSLContext createSslContext(ArangoConfig config) throws Exception {
        var ks = KeyStore.getInstance(config.getTrustStoreType());
        ks.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(config.getTrustStoreFile()),
                config.getTrustStorePassword().toCharArray());
        var tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        var sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        return sc;
    }
}
