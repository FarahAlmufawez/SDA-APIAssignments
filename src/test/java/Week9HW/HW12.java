package Week9HW;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.PetHW;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class HW12 {

    //Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
//(All actions must be done on same pet)
            //(Use Pojo)
    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

    @Test
    public void HW12(){
        PetHW pet = new PetHW(1, "Fluffy", "available");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(BASE_URL);

        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void testGetPet() {
        Response response = RestAssured.get(BASE_URL + "/1");

        assertEquals(response.getStatusCode(), 200);
        PetHW pet = response.getBody().as(PetHW.class);
        assertEquals(pet.getName(), "Fluffy");
    }

    @Test(priority = 2)
    public void testUpdatePet() {
        PetHW pet = new PetHW(1, "Fluffy", "sold");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put(BASE_URL);

        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testDeletePet() {
        Response response = RestAssured.delete(BASE_URL + "/1");

        assertEquals(response.getStatusCode(), 200);
    }

    }

