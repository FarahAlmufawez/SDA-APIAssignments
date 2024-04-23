package Week9HW;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;


public class HW11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
    @Test
    public void HW11(){
        // Send a GET request to the specified URL
        Response response = RestAssured.get("https://automationexercise.com/api/productsList");

        // Assert that the status code is 200
        assertEquals(response.getStatusCode(), 200);

        // Print the response using JsonPath
        response.jsonPath().prettyPrint();

        // Assert that the number of "Women" user type is 12
        int womenCount = response.jsonPath().getList("products.findAll { it.category.usertype.usertype == 'Women' }").size();
        assertEquals(womenCount, 12);
    }
}
