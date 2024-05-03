package Week10HW;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.equalTo;

public class HW15 {

    private String userId;
    private String token;

            @BeforeClass
            public void setup() {
                RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
                RestAssured.useRelaxedHTTPSValidation();
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NjMzNGIxMjUwNjc3NzAwMTMxMDkzZTMiLCJpYXQiOjE3MTQ2Mzc1ODZ9.nGte52narfvQoWa-_dq7hzByIc6VxFmdRs2fymghUH8";
            }

            @Test(priority = 1)
            public void testCreateUser() {
                String requestBody = """
                        {
                         "firstName": "Farah",
                         "lastName": "Ali",
                         "email": "demo@fake.com",
                         "password": "myPassword1"
                         }""";


                 Response response = given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)   //Bearer {{token}}
                        .body(requestBody)
                        .when()
                        .post("/users");

                userId = response.jsonPath().getString("_id");

                response.then().statusCode(201);
            }

            @Test(priority = 2)
            public void testReadUser() {
                given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("/users/me")
                        .then()
                        .statusCode(200)
                        .body("_id", equalTo("66334b1250677700131093e3"))
                        .body("firstName", equalTo("Farah"))
                        .body("lastName", equalTo("Ali"))
                        .body("email", equalTo("demo@fake.com"))
                        .body("__v", equalTo(1));

            }

            @Test(priority = 3)
            public void testUpdateUser() {
                String requestBody = """
                                        {
                                            "firstName": "FarahUpdate",
                                                "lastName": "Ali",
                                                "email": "demo123@fake.com",
                                                "password": "myPassword12"
                                        }""";

                given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .body(requestBody)
                        .when()
                        .patch("/users/me")
                        .then()
                        .statusCode(200)
                        .body("_id", equalTo("66334b1250677700131093e3"))
                        .body("firstName", equalTo("FarahUpdate"))
                        .body("lastName", equalTo("Ali"))
                        .body("email", equalTo("demo123@fake.com"))
                        .body("__v", equalTo(1));
            }

            @Test(priority = 4)
            public void testDeleteUser() {
                given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete("/users/me")
                        .then()
                        .statusCode(200);
            }
        }








