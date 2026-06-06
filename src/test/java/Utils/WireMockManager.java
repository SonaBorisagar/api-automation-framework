package Utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockManager {

    private static WireMockServer wireMockServer;

    public static void startServer() {

        wireMockServer = new WireMockServer(
                options()
                        .port(8089)
                        .usingFilesUnderClasspath("")
        );

        wireMockServer.start();
        

        wireMockServer.stubFor(get(urlEqualTo("/products"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"name\":\"Laptop\"},{\"id\":2,\"name\":\"Mouse\"}]")));

        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":1,\"name\":\"John Doe\"}")));

        wireMockServer.stubFor(post(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"User Created\",\"id\":101}")));

        System.out.println("Loaded mappings: "
                + wireMockServer.listAllStubMappings().getMappings().size());

        System.out.println("WireMock started on port 8089");
    }

    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}