package tests.delete.requestspec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testbase.TestBase;
import utilities.ReadExcelFile;

public class TC002_DeleteProductWithParams {
    static ReadExcelFile readFile;

    @Test(dataProvider = "dataForTest")
    public void deleteTest(String id){
        RequestSpecification req = TestBase.setup();
        Response res = req.delete("/products/"+id);
        String bodyOutput = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();
        System.out.println(bodyOutput);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertFalse(bodyOutput.contains("not found"));
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

