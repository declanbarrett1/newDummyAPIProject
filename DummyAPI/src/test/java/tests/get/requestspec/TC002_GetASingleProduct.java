package tests.get.requestspec;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testbase.TestBase;
import utilities.ReadExcelFile;

public class TC002_GetASingleProduct {


	Response res;
	static ReadExcelFile readFile;
	RequestSpecification req ;
	
	@BeforeTest
	public void setupTest() {
		
		req = TestBase.setup();
		
		
	}
	
	@Test(dataProvider="dataForTest")
	public void test(String idNumber) {
		res = req.get("/products/"+idNumber);
		String body = res.getBody().asPrettyString();
		int statusCode = res.getStatusCode();
		System.out.println(statusCode);
		System.out.println(body);
		AssertJUnit.assertEquals(statusCode, 200);
	}
	
	@DataProvider(name="dataForTest")
	public static String[][] getData() {
		readFile = new ReadExcelFile();
		int rowCount = readFile.getRowCount("dataForGetSingleProduct.xlsx", "Sheet1");
		System.out.println(rowCount);
		int colCount = readFile.getCellCount("dataForGetSingleProduct.xlsx", "Sheet1");
		String[][] data = new String[rowCount-1][colCount];
		
		for(int x = 1; x < rowCount; x++) {
			for(int y = 0; y < colCount; y++) {
				data[x-1][y] = readFile.getCellData("dataForGetSingleProduct.xlsx", "Sheet1", x, y);
			}
		}
		
		return data;
	}
	
	
	

}
