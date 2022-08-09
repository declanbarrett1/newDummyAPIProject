package tests.get.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

public class TC003_GetProductsBySearch {
    Response res;

    @Test
    public void testingSearchGet(){
      res = RestAssured.given()
              .param("q", "Phone")
              .when()
              .get("https://dummyjson.com/products/search")
              .then()
              .log().body()
              .statusCode(200)
              .extract().response();

        String body = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();

        System.out.println(body);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(body.contains("not found"), false);

    }

}
