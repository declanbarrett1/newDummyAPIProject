package tests.delete.requestspec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

public class TC001_DeleteProduct {

    Response res;

    @BeforeTest
    public void setupTest(){
        int id = 6;
        RequestSpecification req = TestBase.setup();
        res = req.delete("/products/"+id);

    }

    @Test
    public void deleteTest(){
        String bodyOutput = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();
        System.out.println(bodyOutput);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyOutput.contains("not found"), false);
    }
}
