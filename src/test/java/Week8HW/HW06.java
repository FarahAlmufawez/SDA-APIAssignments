package Week8HW;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;


public class HW06 {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void HwSix() {
        String requestBody = """
                                 {
                                 "name": "morpheus",
                                 "job": "leader",
                                 "id": 496,
                                 "createdAt": "2022-10-04T15:18:56.372Z"
                                 }""";
//send POST Request to the Url
        io.restassured.response.Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://reqres.in/api/users");
        response.prettyPrint();



        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"),
                        "job", equalTo("leader"),
                        "id", equalTo(496),
                        "createdAt",  notNullValue());


    }
}
