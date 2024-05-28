package com.arangodb;

import com.arangodb.entity.ArangoDBVersion;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/version")
public class GreetingResource {

    private final ArangoService arangoService;

    @Inject
    public GreetingResource(ArangoService arangoService) {
        this.arangoService = arangoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArangoDBVersion getVersion() {
        return arangoService.getVersion();
    }
}
