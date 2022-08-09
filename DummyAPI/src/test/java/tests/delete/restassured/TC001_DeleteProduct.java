package tests.delete.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_DeleteProduct {

    @Test
    public void deleteTest(){
        int id = 6;
        Response res = RestAssured.given()
                .when()
                .delete("https://dummyjson.com/products/" +id)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        String bodyOutput = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();
        System.out.println(bodyOutput);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyOutput.contains("not found"), false);
    }
}
