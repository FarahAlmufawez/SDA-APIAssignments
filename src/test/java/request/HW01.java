package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class HW01 {
    @Test
    void HwOne() {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */



    //        1.User sends a GET Request to the url
        Response response = RestAssured.get("https://reqres.in/api/users/3");
        response.prettyPrint();

    //        2.HTTP Status Code should be 200
    int statusCode = response.statusCode();
        System.out.println("statusCode = "+statusCode);//200

    //        3.Content Type should be JSON
    String contentType = response.contentType();
        System.out.println("contentType = "+contentType);

    //        4.Status Line should be HTTP/1.1 200 OK
    String statusLine = response.statusLine();
        System.out.println("statusLine = "+statusLine);


}
}
