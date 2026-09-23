import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Courier;
import model.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest extends BaseApiTest {

    private Courier courier;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierData.getRandomCourier();
        CourierSteps.createCourierAndCheck(courier);
    }

    @After
    public void tearDown() {
        if (courierId != 0) {
            CourierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Логин курьера: успешный сценарий")
    @Description("Курьер может авторизоваться, возвращается id")
    public void loginCourierSuccess() {
        CourierCredentials creds = new CourierCredentials(courier.getLogin(), courier.getPassword());
        courierId = CourierSteps.loginCourierAndGetId(creds);
    }

    @Test
    @DisplayName("Логин курьера: без пароля")
    @Description("Если нет пароля, запрос возвращает ошибку 400")
    public void loginWithoutPassword() {
        CourierCredentials creds = new CourierCredentials(courier.getLogin(), null);
        CourierSteps.loginCourier(creds)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера: без логина")
    @Description("Если нет логина, запрос возвращает ошибку 400")
    public void loginWithoutLogin() {
        CourierCredentials creds = new CourierCredentials(null, courier.getPassword());
        CourierSteps.loginCourier(creds)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера: неправильный логин")
    @Description("Система вернёт ошибку 404 при неправильном логине")
    public void loginWithWrongLogin() {
        CourierCredentials creds = new CourierCredentials("wrong_" + courier.getLogin(), courier.getPassword());
        CourierSteps.loginCourier(creds)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Логин курьера: неправильный пароль")
    @Description("Система вернёт ошибку 404 при неправильном пароле")
    public void loginWithWrongPassword() {
        CourierCredentials creds = new CourierCredentials(courier.getLogin(), "wrong_" + courier.getPassword());
        CourierSteps.loginCourier(creds)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Логин курьера: несуществующий пользователь")
    @Description("Система вернёт ошибку 404 при несуществующем пользователе")
    public void loginNonExistentUser() {
        CourierCredentials creds = new CourierCredentials("nonexistent_" + System.currentTimeMillis(), "pass");
        CourierSteps.loginCourier(creds)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
