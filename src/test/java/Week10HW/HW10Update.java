package Week10HW;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

    public class HW10Update {

        private static String baseUrl = "https://petstore.swagger.io/v2";
        private static String username = "testuser";

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = baseUrl;
        }

        @Test
        public void testUpdateUser() {
            JSONObject requestParams = new JSONObject();
            requestParams.put("firstName", "Jane");
            requestParams.put("lastName", "Doe");
            requestParams.put("email", "jane.doe@example.com");

            Response response = given()
                    .contentType("application/json")
                    .body(requestParams.toJSONString())
                    .when()
                    .put("/user/" + username)
                    .then()
                    .extract().response();

            response.then().statusCode(200);
        }
    }


