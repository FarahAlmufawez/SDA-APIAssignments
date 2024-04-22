package Week9HW;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class HW09 {

    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
*/

    @Test
    public void Hw09() {
        String requestBody = """
                {
                     "id": 1234,
                     "username": "FarahAli",
                     "firstName": "Farah",
                     "lastName": "Ali",
                     "email": "farah@gmail.com",
                     "password": "12345@",
                     "phone": "1234567890",
                     "userStatus": 0
                }""";


//send POST Request to the Url
        io.restassured.response.Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();



        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("code", equalTo(200),
                        "type", equalTo("unknown"),
                        "message", equalTo("1234")
                        );


//        "id", equalTo(1234),
//                "username", equalTo("FarahAli"),
//                "firstName", equalTo("Farah"),
//                "lastName", equalTo("Ali"),
//                "email", equalTo("farah@gmail.com"),
//                "password", equalTo("12345@"),
//                "phone", equalTo("1234567890"),
//                "userStatus", equalTo(0)
    }
}

