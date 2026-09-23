package client;

import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    public Response getOrders() {
        return given()
                .when()
                .get("/api/v1/orders");
    }

    public Response getOrderByTrack(int track) {
        return given()
                .queryParam("t", track)
                .when()
                .get("/api/v1/orders/track");
    }

    public Response acceptOrder(int id, int courierId) {
        return given()
                .queryParam("courierId", courierId)
                .when()
                .put("/api/v1/orders/accept/" + id);
    }
}
