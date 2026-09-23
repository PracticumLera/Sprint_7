package client;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDER_PATH = "/api/v1/orders";

    private static final RestAssuredConfig CONFIG =
            RestAssuredConfig.config()
                    .httpClient(
                            HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", 30000)
                                    .setParam("http.socket.timeout", 30000)
                                    .setParam("http.connection-manager.timeout", 30000)
                    );

    public Response createOrder(Order order) {
        return given()
                .config(CONFIG)
                .header("Content-type", "application/json")
                .body(order)
                .post(ORDER_PATH);
    }

    public Response getOrders() {
        return given()
                .config(CONFIG)
                .get(ORDER_PATH);
    }

    public Response getOrderByTrack(int track) {
        return given()
                .config(CONFIG)
                .queryParam("t", track)
                .get(ORDER_PATH + "/track");
    }

    public Response acceptOrder(int id, int courierId) {
        return given()
                .config(CONFIG)
                .queryParam("courierId", courierId)
                .put(ORDER_PATH + "/accept/" + id);
    }

    public Response cancelOrder(int track) {
        return given()
                .config(CONFIG)
                .put(ORDER_PATH + "/cancel?track=" + track);
    }
}