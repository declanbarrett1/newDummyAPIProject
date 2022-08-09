package tests.post.requestspec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testbase.TestBase;

public class TC003_UpdateAProduct {

    Response res;

    @Test(dataProvider = "dataForTest")
    public void TestingUpdate(int id, String title, double price, String category){
        JSONObject body = new JSONObject();
        body.put("title", title );
        body.put("price", price);
        body.put("category", category);
        String stringBody = body.toJSONString();
        RequestSpecification req = TestBase.setup();
        req.body(stringBody);
        req.contentType("application/json");
        res = req.put("/products/"+id);

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
