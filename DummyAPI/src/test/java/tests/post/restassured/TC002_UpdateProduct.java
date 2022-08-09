package tests.post.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC002_UpdateProduct {
    Response res;

    @Test(dataProvider = "dataForTest")
    public void TestingUpdate(int id, String title, double price, String category){
        JSONObject body = new JSONObject();
        body.put("title", title );
        body.put("price", price);
        body.put("category", category);
        String stringBody = body.toJSONString();
        res = RestAssured.given()
                .body(stringBody)
                .contentType("application/json")
                .when()
                .put("https://dummyjson.com/products/"+id)
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

    @DataProvider(name="dataForTest")
    public Object[][] getData(){
        Object[][] data = new Object[1][4];
        data[0][0] = 4;
        data[0][1] = "ToothPaste";
        data[0][2] = 12.32;
        data[0][3] = "Home";

        return data;

    }
}
