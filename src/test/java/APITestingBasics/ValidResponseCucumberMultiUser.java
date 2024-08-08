package APITestingBasics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ValidResponseCucumberMultiUser {

    //basic method 1
    @Test
    public void GetMultiUserAnotherWay()
    {
        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                .when()
                .get("/api/users")
                .then()
                .assertThat()
                .statusCode(200)  // Verify status code
                .statusLine("HTTP/1.1 200 OK")
                .extract()
                .response();

    }

    //basic method 2 with all unexpected status code
    @Test
    public void GetMultiUserAnotherWay2()
    {
        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users")
                .then()
                .assertThat()
                //.statusCode(200)  // Verify status code
                //.statusLine("HTTP/1.1 200 OK")
                //.body("access_token", notNullValue())
                .extract()
                .response();

        int statusCode=response.getStatusCode();
        String statusLine=response.getStatusLine();
        System.out.println(statusCode+" "+statusLine);
        System.out.println(response.asString());  //whole response from server
        Assert.assertEquals(statusCode,200,"Unexpected Status Code received");

        }


    //basic method 3 with specific response data validation
    @Test
    public void GetMultiUserAnotherWay3()
    {

        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users")
                .then()
                .assertThat()
                //.statusCode(200)  // Verify status code
                //.statusLine("HTTP/1.1 200 OK")
                .body("data[1].email",equalTo("janet.weaver@reqres.in"))
                .extract()
                .response();
//        NOTE: PATH FOR EMAIL KEY IN BELOW JSON REQUEST DATA
//          EX.  email >  data.email   & id > data.id
//        {
//            "data": {
//            "id": 12,
//                    "email": "rachel.howell@reqres.in"}}
    }


    //basic method 4 with specific response data validation
    @Test
    public void GetMultiUserAnotherWay4()
    {

        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users")
                .then()
                .assertThat()
                //.statusCode(200)  // Verify status code
                //.statusLine("HTTP/1.1 200 OK")
                //.body("email",equalTo("rachel.howell@reqres.in"))
                .extract()
                .response();

        int statusCode=response.getStatusCode();
        String statusLine=response.getStatusLine();
        String email=response.jsonPath().getString("data.email");
        System.out.println(statusCode+" "+statusLine);
        System.out.println(response.asString());  //whole response from server
        Assert.assertEquals(email,"rachel.howell@reqres.in");

    }


    }


