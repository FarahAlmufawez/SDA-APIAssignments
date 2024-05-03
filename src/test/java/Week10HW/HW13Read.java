package Week10HW;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

    public class HW13Read {

        private static String baseUrl = "https://petstore.swagger.io/v2";
        private static String username = "testuser";

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = baseUrl;
        }

        @Test
        public void testReadUser() {
            Response response = given()
                    .contentType("application/json")
                    .when()
                    .get("/user/" + username)
                    .then()
                    .extract().response();

            response.then().statusCode(200);
        }
    }


