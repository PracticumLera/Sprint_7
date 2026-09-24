package client;

import io.restassured.response.Response;
import model.Order;

public class OrderClient extends BaseClient {

    private static final String ORDER_PATH = "/api/v1/orders";

    public Response createOrder(Order order) {
        return baseRequest()
                .body(order)
                .post(ORDER_PATH);
    }

    public Response getOrders() {
        return baseRequest()
                .get(ORDER_PATH);
    }

    public Response getOrderByTrack(int track) {
        return baseRequest()
                .queryParam("t", track)
                .get(ORDER_PATH + "/track");
    }

    public Response acceptOrder(int id, int courierId) {
        return baseRequest()
                .queryParam("courierId", courierId)
                .put(ORDER_PATH + "/accept/" + id);
    }

    public Response acceptOrderWithoutCourierId(int id) {
        return baseRequest()
                .put(ORDER_PATH + "/accept/" + id);
    }

    public Response acceptOrderWithoutId(int courierId) {
        return baseRequest()
                .queryParam("courierId", courierId)
                .put(ORDER_PATH + "/accept/");
    }

    public Response cancelOrder(int track) {
        return baseRequest()
                .put(ORDER_PATH + "/cancel?track=" + track);
    }
}