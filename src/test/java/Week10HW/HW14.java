package Week10HW;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

    public class HW14 {

        private static String baseUrl = "https://dummy.restapiexample.com/api/v1/employees";

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = baseUrl;
        }

        @Test
        public void testEmployeeAPI() {
            given()
                    .when()
                    .get()
                    .then()
                    .statusCode(200)
                    .body("data.id", hasSize(24))
                    .body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"))
                    .body("data.collect { it.employee_age.toInteger() }.max()", equalTo(66))
                    .body("data.min { it.employee_age.toInteger() }.employee_name", equalTo("Tatyana Fitzpatrick"))
                    .body("data.collect { it.employee_salary.toInteger() }.sum()", equalTo(6644770));
        }
    }


