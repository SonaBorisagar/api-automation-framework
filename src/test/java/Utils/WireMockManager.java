package Utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WireMockManager {

    public static WireMockServer wireMockServer;
    
    @BeforeClass 
    public static void start() {

        try {
            wireMockServer = new WireMockServer(
                    WireMockConfiguration.options()
                            .port(8081)
                            .usingFilesUnderClasspath("mappings")
            );

            wireMockServer.start();
            
            System.out.println("WireMock started on port: " + wireMockServer.port());
            
            System.out.println("WireMock running: " + wireMockServer.isRunning());
            
            WireMock.configureFor("localhost", 8081);
            WireMock.resetAllRequests();

            // ✅ ADD ALL STUBS HERE 👇

            stubFor(post(urlEqualTo("/users"))
            	    .willReturn(aResponse()
            	        .withStatus(201)
            	        .withHeader("Content-Type", "application/json")
            	        .withBody("{\"id\":101,\"message\":\"User created\"}")
            	    ));

            stubFor(get(urlPathMatching("/users/.*"))
            	    .willReturn(aResponse()
            	        .withStatus(200)
            	        .withHeader("Content-Type", "application/json")
            	        .withBody("{\"id\":1,\"name\":\"John Doe\"}")
            	    ));
            
            stubFor(get(urlEqualTo("/users"))
            	    .willReturn(aResponse()
            	        .withStatus(200)
            	        .withHeader("Content-Type", "application/json")
            	        .withBody("{\"id\":1},{\"id\":2}")
            	    ));

            stubFor(put(urlMatching("/users/.*"))
            	    .willReturn(aResponse()
            	        .withStatus(200)
            	        .withHeader("Content-Type", "application/json")
            	        .withBody("{\"message\":\"User Updated\"}")
            	    ));

            stubFor(delete(urlMatching("/users/.*"))
            	    .willReturn(aResponse()
            	        .withStatus(204)
            	    ));
            
            
            stubFor(get(urlEqualTo("/products"))
            	    .willReturn(aResponse()
            	        .withStatus(200)
            	        .withHeader("Content-Type", "application/json")
            	        .withBody("[\n" +
            	            "  {\n" +
            	            "    \"id\": 1,\n" +
            	            "    \"name\": \"Laptop\"\n" +
            	            "  },\n" +
            	            "  {\n" +
            	            "    \"id\": 2,\n" +
            	            "    \"name\": \"Mouse\"\n" +
            	            "  }\n" +
            	        "]")
            	    ));
            
            System.out.println("WireMock started successfully");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("WireMock failed to start");
        }
    }

    @AfterClass
    public static void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
    
    
}