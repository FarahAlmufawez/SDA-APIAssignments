package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class HW02 {

    @Test
    void HwTwo() {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

        //        1.User sends a GET Request to the url
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        response.prettyPrint();

        //        2.HTTP Status Code should be 200
        //        3.Response format should be "application/json"
        //        4."email" is "janet.weaver@reqres.in"
        //        5."first_name" is "Janet"
        //        6."last_name" is "Weaver"
        //        7."text" is "To keep ReqRes free, contributions towards server costs are appreciated!"

        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data.email", equalTo("janet.weaver@reqres.in"),
                        "data.first_name", equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}
