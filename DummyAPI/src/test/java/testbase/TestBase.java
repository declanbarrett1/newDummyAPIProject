package testbase;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	
	public static RequestSpecification setup() {
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.baseUri("https://dummyjson.com");
		return httpRequest;
	}
}
