package steps;

import client.OrderClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

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
        response.then().statusCode(201).body("track", notNullValue());
        return response;
    }

    @Step("Получить список заказов")
    public static Response getOrders() {
        return orderClient.getOrders();
    }

    @Step("Получить заказ по треку")
    public static Response getOrderByTrack(int track) {
        return orderClient.getOrderByTrack(track);
    }

    @Step("Принять заказ")
    public static Response acceptOrder(int id, int courierId) {
        return orderClient.acceptOrder(id, courierId);
    }
}