import data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseApiTest {

    private final List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {null}
        };
    }

    @Test
    @DisplayName("Создание заказа: параметризация цветов")
    @Description("Проверка создания заказа с разными вариантами цвета, тело ответа содержит track")
    public void createOrderWithDifferentColors() {
        Order order = OrderData.getOrderWithColor(color);
        OrderSteps.createOrderAndCheck(order);
    }
}