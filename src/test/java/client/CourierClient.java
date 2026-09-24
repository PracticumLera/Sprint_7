package client;

import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

public class CourierClient extends BaseClient {

    private static final String COURIER_PATH = "/api/v1/courier";

    public Response createCourier(Courier courier) {
        return baseRequest()
                .body(courier)
                .post(COURIER_PATH);
    }

    public Response loginCourier(CourierCredentials credentials) {
        return baseRequest()
                .body(credentials)
                .post(COURIER_PATH + "/login");
    }

    public Response deleteCourier(int id) {
        return baseRequest()
                .delete(COURIER_PATH + "/" + id);
    }

    public Response deleteCourierWithoutId() {
        return baseRequest()
                .delete(COURIER_PATH + "/");
    }
}