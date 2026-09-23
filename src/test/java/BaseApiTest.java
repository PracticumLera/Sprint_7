import io.restassured.RestAssured;
import org.junit.Before;

public class BaseApiTest {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void setUpBase() {
        RestAssured.baseURI = BASE_URL;
    }
}
