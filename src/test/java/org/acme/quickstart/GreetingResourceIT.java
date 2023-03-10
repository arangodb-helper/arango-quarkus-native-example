package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
class HttpJsonGreetingResourceIT extends HttpJsonGreetingResourceTest {
    // Execute the same tests but in packaged mode.
}

@QuarkusIntegrationTest
class Http2JsonGreetingResourceIT extends Http2JsonGreetingResourceTest {
    // Execute the same tests but in packaged mode.
}
