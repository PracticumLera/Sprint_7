import data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseApiTest {

    private final List<String> color;
    private Integer track;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {null}
        };
    }

    @After
    public void tearDown() {
        if (track != null) {
            OrderSteps.cancelOrder(track);
        }
    }

    @Test
    @DisplayName("Создание заказа: параметризация цветов")
    @Description("Проверка создания заказа с разными вариантами цвета, тело ответа содержит track")
    public void createOrderWithDifferentColors() {
        Order order = OrderData.getOrderWithColor(color);
        Response response = OrderSteps.createOrderAndCheck(order);
        track = response.then().extract().path("track");
    }
}