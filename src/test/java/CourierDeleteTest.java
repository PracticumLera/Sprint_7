import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierCredentials;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteTest extends BaseCourierTest {

    private int courierId;

    @Before
    public void setUp() {
        courier = CourierData.getRandomCourier();
        CourierSteps.createCourierAndCheck(courier);
        courierId = CourierSteps.loginCourierAndGetId(
                new CourierCredentials(courier.getLogin(), courier.getPassword())
        );
    }

    @Test
    @DisplayName("Удаление курьера: успешный сценарий")
    @Description("Успешный запрос возвращает ok: true")
    public void deleteCourierSuccess() {
        CourierSteps.deleteCourierRaw(courierId)
                .then()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Удаление курьера: без id")
    @Description("Запрос без id возвращает ошибку 400")
    public void deleteCourierWithoutId() {
        CourierSteps.deleteCourierWithoutId()
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для удаления курьера"));
    }

    @Test
    @DisplayName("Удаление курьера: несуществующий id")
    @Description("Запрос с несуществующим id возвращает ошибку 404")
    public void deleteNonExistentCourier() {
        CourierSteps.deleteCourierRaw(999999999)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Курьера с таким id нет."));
    }
}
