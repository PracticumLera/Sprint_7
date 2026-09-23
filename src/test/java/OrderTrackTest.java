import data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;

import java.util.Arrays;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderTrackTest extends BaseApiTest {

    private int track;

    @Before
    public void setUp() {
        Order order = OrderData.getOrderWithColor(Arrays.asList("BLACK"));
        track = OrderSteps.createOrderAndCheck(order).then().extract().path("track");
    }

    @After
    public void tearDown() {
        if (track > 0) {
            Response cancelResponse = OrderSteps.cancelOrder(track);
            if (cancelResponse.statusCode() != SC_OK) {
                System.out.println("Не удалось отменить заказ, track: " + track
                        + ", статус: " + cancelResponse.statusCode());
            }
        }
    }

    @Test
    @DisplayName("Получение заказа по треку: успешный сценарий")
    @Description("Успешный запрос возвращает объект с заказом")
    public void getOrderByTrackSuccess() {
        OrderSteps.getOrderByTrack(track)
                .then()
                .statusCode(SC_OK)
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Получение заказа по треку: без номера")
    @Description("Запрос без номера возвращает ошибку 400")
    public void getOrderByTrackWithoutNumber() {
        OrderSteps.getOrderByTrack(0)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Получение заказа по треку: несуществующий номер")
    @Description("Запрос с несуществующим номером возвращает ошибку 404")
    public void getOrderByNonExistentTrack() {
        OrderSteps.getOrderByTrack(999999999)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Заказ не найден"));
    }
}
