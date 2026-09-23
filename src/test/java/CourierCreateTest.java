import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Courier;
import model.CourierCredentials;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends BaseApiTest {

    private int courierId;

    @After
    public void tearDown() {
        if (courierId != 0) {
            CourierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Создание курьера: успешный сценарий")
    @Description("Проверка, что курьера можно создать, код 201, ok: true")
    public void createCourierSuccess() {
        Courier courier = CourierData.getRandomCourier();
        CourierSteps.createCourierAndCheck(courier);
    }

    @Test
    @DisplayName("Создание курьера: дубликат логина")
    @Description("Нельзя создать двух одинаковых курьеров, код 409")
    public void createDuplicateCourier() {
        Courier courier = CourierData.getRandomCourier();
        CourierSteps.createCourierAndCheck(courier);

        CourierCredentials creds = new CourierCredentials(courier.getLogin(), courier.getPassword());
        courierId = CourierSteps.loginCourierAndGetId(creds);

        CourierSteps.createCourier(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера: без логина")
    @Description("Если нет логина, запрос возвращает ошибку 400")
    public void createCourierWithoutLogin() {
        Courier courier = CourierData.getCourierWithoutLogin();
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера: без пароля")
    @Description("Если нет пароля, запрос возвращает ошибку 400")
    public void createCourierWithoutPassword() {
        Courier courier = CourierData.getCourierWithoutPassword();
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
