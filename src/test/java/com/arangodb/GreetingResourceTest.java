package com.arangodb;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.TestProfile;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

abstract class GreetingResourceTest {
    @Test
    void testVersionEndpoint() {
        String server = given()
                .when().get("/version")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("server");
        assertThat(server).isEqualTo("arango");
    }
}

@QuarkusTest
@TestProfile(ProtocolProfile.HTTP_JSON.class)
class HttpJsonGreetingResourceTest extends GreetingResourceTest {
}

@QuarkusTest
@TestProfile(ProtocolProfile.HTTP2_JSON.class)
class Http2JsonGreetingResourceTest extends GreetingResourceTest {
}
