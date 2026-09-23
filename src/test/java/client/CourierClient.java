package client;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String COURIER_PATH = "/api/v1/courier";

    private static final RestAssuredConfig CONFIG =
            RestAssuredConfig.config()
                    .httpClient(
                            HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", 30000)
                                    .setParam("http.socket.timeout", 30000)
                                    .setParam("http.connection-manager.timeout", 30000)
                    );

    public Response createCourier(Courier courier) {
        return given()
                .config(CONFIG)
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH);
    }

    public Response loginCourier(CourierCredentials credentials) {
        return given()
                .config(CONFIG)
                .header("Content-type", "application/json")
                .body(credentials)
                .post(COURIER_PATH + "/login");
    }

    public Response deleteCourier(int id) {
        return given()
                .config(CONFIG)
                .delete(COURIER_PATH + "/" + id);
    }
}