import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.After;
import steps.CourierSteps;

import static org.apache.http.HttpStatus.SC_OK;

public class BaseCourierTest extends BaseApiTest {

    protected Courier courier;

    @After
    public void tearDownCourier() {
        if (courier == null) return;

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
}
