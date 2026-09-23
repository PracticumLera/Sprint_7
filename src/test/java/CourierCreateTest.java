import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends BaseApiTest {

    private Courier courier;

    @Before
    public void setUp() {
        courier = CourierData.getRandomCourier();
    }

    @After
    public void tearDown() {
        Response loginResponse = CourierSteps.loginCourier(
                new CourierCredentials(courier.getLogin(), courier.getPassword())
        );

        if (loginResponse.statusCode() == SC_OK) {
            Integer courierId = loginResponse.then().extract().path("id");
            if (courierId != null && courierId > 0) {
                CourierSteps.deleteCourier(courierId);
            }
        }
    }

    @Test
    @DisplayName("Создание курьера: успешный сценарий")
    @Description("Проверка, что курьера можно создать, код 201, ok: true")
    public void createCourierSuccess() {
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера: дубликат логина")
    @Description("Нельзя создать двух одинаковых курьеров, код 409")
    public void createDuplicateCourier() {
        CourierSteps.createCourierAndCheck(courier);

        CourierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера: без логина")
    @Description("Если нет логина, запрос возвращает ошибку 400")
    public void createCourierWithoutLogin() {
        Courier courierWithoutLogin = CourierData.getCourierWithoutLogin();
        CourierSteps.createCourier(courierWithoutLogin)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера: без пароля")
    @Description("Если нет пароля, запрос возвращает ошибку 400")
    public void createCourierWithoutPassword() {
        Courier courierWithoutPassword = CourierData.getCourierWithoutPassword();
        CourierSteps.createCourier(courierWithoutPassword)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
