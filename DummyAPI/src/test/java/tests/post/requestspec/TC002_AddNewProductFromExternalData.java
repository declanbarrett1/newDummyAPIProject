package tests.post.requestspec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testbase.TestBase;
import utilities.ReadExcelFile;

public class TC002_AddNewProductFromExternalData {
    Response res;
    ReadExcelFile readFile;



    @Test(dataProvider = "dataForTest")
    public void testingAddingNewProduct(String title, String description, String price, String discountPercentage, String rating
    , String stock, String brand, String category, String thumbnail, String image) {

        JSONObject body = new JSONObject();
        body.put("title", title);
        body.put("description", description);
        body.put("price", price);
        body.put("discountPercentage", discountPercentage);
        body.put("rating", rating);
        body.put("stock", stock);
        body.put("brand", brand);
        body.put("category", category);
        body.put("thumbnail", thumbnail);
        body.put("images", image);
        String bodyString = body.toJSONString();
        RequestSpecification req = TestBase.setup();
        req.contentType("application/json");
        req.body(bodyString);
        res = req.post("/products/add");
        String bodyOutput = res.getBody().asPrettyString();
        int statusCode = res.getStatusCode();

        System.out.println(bodyOutput);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyOutput.contains("not found"), false);
    }

    @DataProvider(name="dataForTest")
    public String[][] getData() {
        readFile = new ReadExcelFile();
        int rowCount = readFile.getRowCount("dataForAddNewProducts.xlsx", "Sheet1");
        System.out.println(rowCount);
        int colCount = readFile.getCellCount("dataForAddNewProducts.xlsx", "Sheet1");
        String[][] data= new String[rowCount][colCount];

        for(int x = 1; x <= rowCount; x++) {
            for(int y = 0; y < colCount; y++) {
                data[x-1][y] = readFile.getCellData("dataForAddNewProducts.xlsx", "Sheet1", x, y);
            }
        }

        return data;

    }


}
