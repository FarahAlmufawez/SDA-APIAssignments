package Week10HW;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.equalTo;

public class HW16 {

    private String userId;
    private String token;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.useRelaxedHTTPSValidation();
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NjM1NGJkY2ZkZGI1ZjAwMTM3ZDMxYjMiLCJpYXQiOjE3MTQ3Njg4NjB9.HdwkkcxRu57V5lfw-GrQbn36yMF0I2fNwTXpxYGbIUo";
    }

    @Test(priority = 1)

    public void testCreateUser() {
        String requestBody = """
                {
                      "firstName": "John",
                      "lastName": "Doe",
                      "birthdate": "1970-01-01",
                      "email": "jdoe@fake.com",
                      "phone": "8005555555",
                      "street1": "1 Main St.",
                      "street2": "Apartment A",
                      "city": "Anytown",
                      "stateProvince": "KS",
                      "postalCode": "12345",
                      "country": "USA"
                  }""";


        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)   //Bearer {{token}}
                .body(requestBody)
                .when()
                .post("/contacts");

        userId = response.jsonPath().getString("_id");

        response.then().statusCode(201);
    }

    @Test(priority = 2)
    public void testReadUser() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/contacts/")
                .then()
                .statusCode(200)
                .body("_id", equalTo("66354e4906c2090013c24eb1"))
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("birthdate", equalTo("1970-01-01"))
                .body("email", equalTo("jdoe@fake.com"))
                .body("phone", equalTo("8005555555"))
                .body("street1", equalTo("1 Main St."))
                .body("street2", equalTo("Apartment A"))
                .body("city", equalTo("Anytown"))
                .body("stateProvince", equalTo("KS"))
                .body("postalCode", equalTo("12345"))
                .body("country", equalTo("USA"))
                .body("owner", equalTo("66354bdcfddb5f00137d31b3"))
                .body("__v", equalTo(0));

//        {
//            "_id": "6085a221fcfc72405667c3d4",
//                "firstName": "John",
//                "lastName": "Doe",
//                "birthdate": "1970-01-01",
//                "email": "jdoe@fake.com",
//                "phone": "8005555555",
//                "street1": "1 Main St.",
//                "street2": "Apartment A",
//                "city": "Anytown",
//                "stateProvince": "KS",
//                "postalCode": "12345",
//                "country": "USA",
//                "owner": "6085a21efcfc72405667c3d4",
//                "__v": 0
//        }

    }

    @Test(priority = 3)
    public void testUpdateUser() {
        String requestBody = """
                {
                    "firstName": "Amy",
                    "lastName": "Miller",
                    "birthdate": "1992-02-02",
                    "email": "amiller@fake.com",
                    "phone": "8005554242",
                    "street1": "13 School St.",
                    "street2": "Apt. 5",
                    "city": "Washington",
                    "stateProvince": "QC",
                    "postalCode": "A1A1A1",
                    "country": "Canada"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .put("/contacts/")
                .then()
                .statusCode(200)
                .body("_id", equalTo("6085a221fcfc72405667c3d4"))
                .body("firstName", equalTo("Amy"))
                .body("lastName", equalTo("Miller"))
                .body("birthdate", equalTo("1992-02-02"))
                .body("email", equalTo("amiller@fake.com"))
                .body("phone", equalTo("8005554242"))
                .body("street1", equalTo("13 School St."))
                .body("street2", equalTo("Apt. 5"))
                .body("city", equalTo("Washington"))
                .body("stateProvince", equalTo("QC"))
                .body("postalCode", equalTo("A1A1A1"))
                .body("country", equalTo("Canada"))
                .body("owner", equalTo("6085a21efcfc72405667c3d4"))
                .body("__v", equalTo(0));

    }

    @Test(priority = 4)
    public void testDeleteUser() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/contacts/")
                .then()
                .statusCode(200);
    }
}








