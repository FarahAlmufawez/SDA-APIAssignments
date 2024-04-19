package Week8HW;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class HW03 {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */
    @Test
    public void HWThree() {
        Response response = RestAssured.get("https://reqres.in/api/users/23");

        // Verify status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 404, "Status code doesn't match");

        // Verify status line
        String statusLine = response.getStatusLine();
        assertEquals(statusLine, "HTTP/1.1 404 Not Found", "Status line doesn't match");

        // Verify server
        String server = response.getHeader("Server");
        assertEquals(server, "cloudflare", "Server doesn't match");

        // Verify response body is empty
        String body = response.getBody().asString();
        assertEquals(body, "{}", "Response body is not empty");
    }
}
