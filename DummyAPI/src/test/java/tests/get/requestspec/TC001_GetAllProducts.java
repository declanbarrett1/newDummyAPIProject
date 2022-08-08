package tests.get.requestspec;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testbase.TestBase;

public class TC001_GetAllProducts {
	
	Response res;
	
	@BeforeTest
	public void setupTest() {
		RequestSpecification req = TestBase.setup();
		res = req.get("/products");
	}

	
	@Test
	public void testingGetAll() {
		System.out.println(res.getBody().asPrettyString());
		System.out.println(res.getStatusCode());
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
	}
}
