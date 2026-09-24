package client;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

    protected static final RestAssuredConfig CONFIG =
            RestAssuredConfig.config()
                    .httpClient(
                            HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", 30000)
                                    .setParam("http.socket.timeout", 30000)
                                    .setParam("http.connection-manager.timeout", 30000)
                    );

    protected RequestSpecification baseRequest() {
        return given()
                .config(CONFIG)
                .header("Content-type", "application/json");
    }
}
