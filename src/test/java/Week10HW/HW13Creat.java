package Week10HW;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

    public class HW13Creat {

        private static String baseUrl = "https://petstore.swagger.io/v2";
        private static String username = "testuser";
        private static String password = "testpassword";

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = baseUrl;
        }

        @Test
        public void testCreateUser() {
            JSONObject requestParams = new JSONObject();
            requestParams.put("username", username);
            requestParams.put("password", password);
            requestParams.put("firstName", "John");
            requestParams.put("lastName", "Doe");
            requestParams.put("email", "john.doe@example.com");

            Response response = given()
                    .contentType("application/json")
                    .body(requestParams.toJSONString())
                    .when()
                    .post("/user")
                    .then()
                    .extract().response();

            response.then().statusCode(200);
        }
    }



