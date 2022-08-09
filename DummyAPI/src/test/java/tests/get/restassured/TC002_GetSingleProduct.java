package tests.get.restassured;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ReadExcelFile;

public class TC002_GetSingleProduct {
	
	Response res;
	static ReadExcelFile readFile;
	RequestSpecification req ;
	
	
	@Test(dataProvider = "dataForTest")
	public void setupTest(String idNumber) {
		res = RestAssured.given()
				.when()
				.get("https://dummyjson.com/products/"+idNumber)
				.then()
				.log().body()
				.statusCode(200)
				.extract().response();
		
		System.out.println(res.getBody().asPrettyString());
		System.out.println(res.getStatusCode());
		AssertJUnit.assertEquals(res.getBody().asString().contains("id"), true);
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
	}
	
	@DataProvider(name="dataForTest")
	public String[][] getData() {
		readFile = new ReadExcelFile();
		int rowCount = readFile.getRowCount("dataForGetSingleProduct.xlsx", "Sheet1");
		int colCount = readFile.getCellCount("dataForGetSingleProduct.xlsx", "Sheet1");
		String[][] data= new String[rowCount-1][colCount];
		
		for(int x = 1; x < rowCount; x++) {
			for(int y = 0; y < colCount; y++) {
				data[x-1][y] = readFile.getCellData("dataForGetSingleProduct.xlsx", "Sheet1", x, y);
			}
		}
		
		return data;
		
	}


}
