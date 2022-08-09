package tests.post.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

public class TC001_PostANewProduct {
    Response res;
    String bodyString;

    @BeforeTest
    public void setupTest(){
        String title = "Playstation 4";
        String description = "A new way to play";
        long price = 300;
        double discountPercentage = 11.40;
        double rating = 4.98;
        long stock = 10043;
        String brand = "Sony";
        String category = "Gaming";
        String thumbnail = null;
        String image = "https://gmedia.playstation.com/is/image/SIEPDC/ps4-slim-image-block-01-en-24jul20?$native--t$";
        JSONObject body = new JSONObject();
        body.put("title", title);
        body.put("description", description);
        body.put("price", price);
        body.put("discountPercentage", discountPercentage);
        body.put("rating", rating);
        body.put("stock", stock);
        body.put("brand", brand);
        body.put("category", category);
        body.put("thumbnail", thumbnail);
        body.put("images", image);
        bodyString = body.toJSONString();


    }

    @Test
    public void testingAddingNewProduct() {
        res = RestAssured.given()
                .body(bodyString)
                .contentType("application/json")
                .when()
                .post("https://dummyjson.com/products/add")
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
