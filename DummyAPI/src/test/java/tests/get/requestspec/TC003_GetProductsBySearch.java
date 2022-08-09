package tests.get.requestspec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

public class TC003_GetProductsBySearch {
    Response res;

    @BeforeTest
    public void setupTest(){
       RequestSpecification req =  TestBase.setup();
       req.param("q", "Laptop");
       res = req.get("/products/search");
    }

    @Test
    public void testingSearchGet(){
        String body = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();

        System.out.println(body);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(body.contains("not found"), false);

    }

}
