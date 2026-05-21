package BasicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UserRegistrationTests {

    String BaseURL = "https://www.ndosiautomation.co.za/APIDEV";
    String registeredUserId;
    String adminToken;
    @Test (priority = 1)
    public void userRegistrationTest(){


        String registerUserPath = "/register";

        String userRegistrationPayload = "{\n" +
                "  \"firstName\": \"Dora\",\n" +
                "  \"lastName\": \"Jacks\",\n" +
                "  \"email\": \"test321@gmail.com\",\n" +
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

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status Code: " + responseStatusCode);
        System.out.println(("Response Body: " + response.getBody().asString()));
        Assert.assertEquals(responseStatusCode, 201, "Expected status code 200");

        registeredUserId = response.jsonPath().getString("data.id");
    }
    @Test (priority = 2)

    public void adminLoginTest(){
        //String BaseURL = "https://www.ndosiautomation.co.za/APIDEV";
        String loginPath = "/login";

        String adminLoginOayload = "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath(loginPath)
                .header("Content-Type", "application/json")
                .body(adminLoginOayload)
                .log().all()
                .post()
                .then().extract().response();

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status Code: " + responseStatusCode);
        System.out.println(("Response Body: " + response.getBody().asString()));
        Assert.assertEquals(responseStatusCode, 200, "Expected status code 200");

        adminToken = response.jsonPath().getString("data.token");

    }

    @Test (priority = 3)
    public void approveUser(){
        String approveUserPath = "/admin/users/"+registeredUserId +"/approve";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath(approveUserPath)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .log().all()
                .put()
                .then().extract().response();

        System.out.println(("Response Body: " + response.getBody().asString()));
    }




}
