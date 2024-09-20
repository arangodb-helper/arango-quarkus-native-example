package com.arangodb;

import com.arangodb.config.ProtocolConfig;
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
import java.lang.reflect.InvocationTargetException;
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
                .protocolConfig(createProtocolConfig())
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

    private ProtocolConfig createProtocolConfig() {
        var builder = HttpProtocolConfig.builder();
        if (!PackageVersion.SHADED) {
            // Invoking com.arangodb.http.HttpProtocolConfig.Builder.vertx(Vertx) with reflection to avoid compilation
            // errors when using the shaded profile. This is equivalent to: builder.vertx(vertx)
            try {
                //noinspection JavaReflectionMemberAccess
                HttpProtocolConfig.Builder.class
                        .getDeclaredMethod("vertx", Vertx.class)
                        .invoke(builder, vertx);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return builder.build();
    }

}
