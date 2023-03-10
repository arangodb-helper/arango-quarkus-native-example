package org.acme.quickstart;


import com.arangodb.Protocol;
import com.arangodb.config.ArangoConfigProperties;
import com.arangodb.config.HostDescription;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

@Dependent
@ConfigProperties(prefix = "adb")
public class ArangoConfig implements ArangoConfigProperties {
    private Optional<List<HostDescription>> hosts;
    private Optional<Protocol> protocol;
    private Optional<String> password;

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
}
