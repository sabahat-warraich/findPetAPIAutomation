package main.java.restapi;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import io.restassured.http.Header;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PetFindByStatus {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2/pet/findByStatus";

    }

    @Test(priority = 1)
    @Description("Hit the pet end point with base path /findByStatus and " +
            "status value should be available, sold, pending")
    @Story("Jira-207")
    public void findByStatus() {

        Header header = new Header("Accept", "application/json");
        Map<String, String> formParam = new HashMap<>();
        formParam.put("status", "available");
        //    formParam.put("status","pending");
        //    formParam.put("status","sold");
        Response response = RestAssured.given()
                .header(header)
                .formParams(formParam)
                .when()
                .get();
        //assert status code
        Assert.assertEquals(response.statusCode(), 200);
        // Body
        System.out.println(response.asPrettyString());

    }
}
