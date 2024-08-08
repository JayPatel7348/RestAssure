package APITestingBasics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidResponse {

        @Test
        public void GetSingleUser()
        {
            RestAssured.baseURI="https://reqres.in";
            RequestSpecification reqSpec =RestAssured.given(); //prepares the request
            Response response=reqSpec.get("/api/users/12"); //The GET request is sent
            int statusCode=response.getStatusCode();
            String statusLine=response.getStatusLine();
            System.out.println(statusCode+" "+statusLine);
            Assert.assertEquals(statusCode,200,"unexpected status code");
        }

    @Test
    public void GetSingleUserAnotherWay()
    {
        RestAssured.baseURI="https://reqres.in";
        RequestSpecification reqSpec=RestAssured.given();
        Response response=reqSpec.get("/api/users/12");

        int statusCode=response.getStatusCode();
        String statusLine=response.getStatusLine();
        String responseBody=response.asString();
        System.out.println(statusCode+" "+statusLine);
        System.out.println(responseBody);
        Assert.assertEquals(statusCode,200,"unexpected status code");
    }

}
