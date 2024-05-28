package com.arangodb;


import com.arangodb.config.ArangoConfigProperties;
import com.arangodb.config.HostDescription;
import jakarta.enterprise.context.Dependent;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;

@Dependent
@ConfigProperties(prefix = "adb")
public class ArangoConfig implements ArangoConfigProperties {
    private Optional<List<HostDescription>> hosts;
    private Optional<Protocol> protocol;
    private Optional<String> password;
    private Optional<Boolean> useSsl;

    @ConfigProperty(name = "ssl.trustStoreFile")
    private String trustStoreFile;

    @ConfigProperty(name = "ssl.trustStorePassword")
    private String trustStorePassword;

    @ConfigProperty(name = "ssl.trustStoreType")
    private String trustStoreType;

    @Override
    public Optional<List<HostDescription>> getHosts() {
        return hosts;
    }

    @Override
    public Optional<Protocol> getProtocol() {
        return protocol;
    }

    @Override
    public Optional<String> getPassword() {
        return password;
    }

    @Override
    public Optional<Boolean> getUseSsl() {
        return useSsl;
    }

    public String getTrustStoreFile() {
        return trustStoreFile;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public String getTrustStoreType() {
        return trustStoreType;
    }
}
