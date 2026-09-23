import data.CourierData;
import data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Courier;
import model.CourierCredentials;
import model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderAcceptTest extends BaseApiTest {

    private static final List<String> DEFAULT_COLOR = Arrays.asList("BLACK");

    private int courierId;

    @Before
    public void setUp() {
        Courier courier = CourierData.getRandomCourier();
        CourierSteps.createCourierAndCheck(courier);
        courierId = CourierSteps.loginCourierAndGetId(
                new CourierCredentials(courier.getLogin(), courier.getPassword())
        );
    }

    @After
    public void tearDown() {
        if (courierId > 0) {
            CourierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Принятие заказа: успешный сценарий")
    @Description("Успешный запрос возвращает ok: true")
    public void acceptOrderSuccess() {
        int orderId = OrderSteps.createOrderAndGetId(OrderData.getOrderWithColor(DEFAULT_COLOR));
        OrderSteps.acceptOrder(orderId, courierId)
                .then()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Принятие заказа: без id курьера")
    @Description("Запрос без id курьера возвращает ошибку 400")
    public void acceptOrderWithoutCourierId() {
        int orderId = OrderSteps.createOrderAndGetId(OrderData.getOrderWithColor(DEFAULT_COLOR));
        OrderSteps.acceptOrderWithoutCourierId(orderId)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Принятие заказа: неверный id курьера")
    @Description("Запрос с неверным id курьера возвращает ошибку 404")
    public void acceptOrderWithWrongCourier() {
        int orderId = OrderSteps.createOrderAndGetId(OrderData.getOrderWithColor(DEFAULT_COLOR));
        OrderSteps.acceptOrder(orderId, 999999999)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Курьера с таким id не существует"));
    }

    @Test
    @DisplayName("Принятие заказа: без номера заказа")
    @Description("Запрос без номера заказа возвращает ошибку 400")
    public void acceptOrderWithoutId() {
        OrderSteps.createOrderAndGetId(OrderData.getOrderWithColor(DEFAULT_COLOR));
        OrderSteps.acceptOrderWithoutId(courierId)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Принятие заказа: неверный номер заказа")
    @Description("Запрос с неверным номером заказа возвращает ошибку 404")
    public void acceptOrderWithWrongId() {
        OrderSteps.createOrderAndGetId(OrderData.getOrderWithColor(DEFAULT_COLOR));
        OrderSteps.acceptOrder(999999999, courierId)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Заказа с таким id не существует"));
    }
}