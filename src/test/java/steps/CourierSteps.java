package steps;

import client.CourierClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierSteps {

    private static final CourierClient courierClient = new CourierClient();

    @Step("Создать курьера")
    public static Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Создать курьера и проверить, что он создан")
    public static Response createCourierAndCheck(Courier courier) {
        Response response = courierClient.createCourier(courier);
        response.then().statusCode(SC_CREATED).body("ok", equalTo(true));
        return response;
    }

    @Step("Логин курьера")
    public static Response loginCourier(CourierCredentials credentials) {
        return courierClient.loginCourier(credentials);
    }

    @Step("Залогинить курьера и получить id")
    public static int loginCourierAndGetId(CourierCredentials credentials) {
        return courierClient.loginCourier(credentials)
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
    }

    @Step("Удалить курьера по id")
    public static void deleteCourier(int id) {
        courierClient.deleteCourier(id)
                .then()
                .statusCode(SC_OK);
    }
}