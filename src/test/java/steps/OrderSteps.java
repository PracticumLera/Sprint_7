package steps;

import client.OrderClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;

public class OrderSteps {

    private static final OrderClient orderClient = new OrderClient();

    @Step("Создать заказ")
    public static Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Создать заказ и проверить, что он создан")
    public static Response createOrderAndCheck(Order order) {
        Response response = orderClient.createOrder(order);
        response.then().statusCode(SC_CREATED).body("track", notNullValue());
        return response;
    }

    @Step("Создать заказ и получить id")
    public static int createOrderAndGetId(Order order) {
        Response createResponse = createOrderAndCheck(order);
        int track = createResponse.then().extract().path("track");
        return getOrderByTrack(track).then().extract().path("order.id");
    }

    @Step("Получить список заказов")
    public static Response getOrders() {
        return orderClient.getOrders();
    }

    @Step("Получить заказ по треку")
    public static Response getOrderByTrack(int track) {
        return orderClient.getOrderByTrack(track);
    }

    @Step("Получить заказ по треку и проверить")
    public static Response getOrderByTrackAndCheck(int track) {
        Response response = orderClient.getOrderByTrack(track);
        response.then().statusCode(SC_OK).body("order", notNullValue());
        return response;
    }

    @Step("Принять заказ")
    public static Response acceptOrder(int id, int courierId) {
        return orderClient.acceptOrder(id, courierId);
    }

    @Step("Принять заказ без указания id курьера")
    public static Response acceptOrderWithoutCourierId(int id) {
        return orderClient.acceptOrderWithoutCourierId(id);
    }

    @Step("Принять заказ без указания id заказа")
    public static Response acceptOrderWithoutId(int courierId) {
        return orderClient.acceptOrderWithoutId(courierId);
    }

    @Step("Отменить заказ по треку")
    public static Response cancelOrder(int track) {
        return orderClient.cancelOrder(track);
    }
}