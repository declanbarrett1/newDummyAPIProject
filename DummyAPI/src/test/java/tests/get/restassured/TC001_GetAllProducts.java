package tests.get.restassured;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC001_GetAllProducts {
	
	Response res;
	
	@BeforeTest
	public void setupTest() {
		res = RestAssured.given()
				.when()
				.get("https://dummyjson.com/products")
				.then()
				.log().body()
				.statusCode(200)
				.extract().response();
	}

	
	@Test
	public void testingGetAll() {
	
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
	}
}
