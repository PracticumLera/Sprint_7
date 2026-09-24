import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest extends BaseApiTest {

    @Test
    @DisplayName("Список заказов: успешный запрос")
    @Description("Проверка, что в тело ответа возвращается список заказов")
    public void getOrdersList() {
        Response response = OrderSteps.getOrders();
        response.then().statusCode(SC_OK).body("orders", notNullValue());
    }
}