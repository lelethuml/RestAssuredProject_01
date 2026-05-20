package BasicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UserRegistrationTests {
    String BaseURL = "https://www.ndosiautomation.co.za/APIDEV";
    String registerUserPath = "/register";

    String userRegistrationPayload = "{\n" +
            "  \"firstName\": \"Dora\",\n" +
            "  \"lastName\": \"Jacks\",\n" +
            "  \"email\": \"Group2+{{$randomEmail}}\",\n" +
            "  \"password\": \"Password@12\",\n" +
            "  \"confirmPassword\": \"Password@12\",\n" +
            "  \"groupId\": \"5328c91e-fc40-11f0-8e00-5000e6331276\"\n" +
            "}";

    Response response = RestAssured.given()
            .baseUri(BaseURL)
            .basePath(registerUserPath)
            .header("Content-Type", "application/json")
            .body(userRegistrationPayload)
            .log().all()
            .post()
            .then().extract().response();

  int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + responseStatusCode);
         Assert.assertEquals(responseStatusCode, 201, "Expected status code 200");




}
