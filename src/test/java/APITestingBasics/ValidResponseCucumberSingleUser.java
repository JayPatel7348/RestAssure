package APITestingBasics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ValidResponseCucumberSingleUser {

    //basic method 1
    @Test
    public void GetSingleUserAnotherWay()
    {
        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                .when()
                .get("/api/users/19")
                .then()
                .assertThat()
                .statusCode(200)  // Verify status code
                .statusLine("HTTP/1.1 200 OK")
                .extract()
                .response();

    }

    //basic method 2 with all unexpected status code
    @Test
    public void GetSingleUserAnotherWay2()
    {
        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users/12")
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
    public void GetSingleUserAnotherWay3()
    {

        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users/12")
                .then()
                .assertThat()
                //.statusCode(200)  // Verify status code
                //.statusLine("HTTP/1.1 200 OK")
                .body("data.email",equalTo("rachel.howell@reqres.in"))
                .extract()
                .response();
//        NOTE: PATH FOR EMAIL KEY IN BELOW JSON REQUEST DATA
//          EX.  email >  email    -> if only one email in response - no parent name outside curly bracket
//               email >  data.email  ->If single email in under Data bracket
        //       email >  data[0].email ->if multiple email fields start from 0
//        {
//            "data": {
//                    "id": 12,
//                    "email": "rachel.howell@reqres.in"
//                       },
//                       {
//                      "id": 13s,
//                      "email": "janet.weaver@reqres.in",
//                       }
//          }
    }


    //basic method 4 with specific response data validation
    @Test
    public void GetSingleUserAnotherWay4()
    {

        RestAssured.baseURI="https://reqres.in";
        Response response =RestAssured.given() //prepares the request
                .log().all()  // Logs all details of the request
                //.header("Content-type", "application/json")
                //.body(requestBody)
                .when()
                .get("/api/users/12")
                .then()
                .log().all() // Logs all details of the response
                .assertThat()
                //.statusCode(200)  // Verify status code
                //.statusLine("HTTP/1.1 200 OK")
                //.body("email",equalTo("rachel.howell@reqres.in"))
                .extract()
                .response();

        int statusCode=response.getStatusCode();
        String statusLine=response.getStatusLine();
        long time=response.time();
        String email=response.jsonPath().getString("data.email");
        System.out.println(time);
        System.out.println(statusCode+" "+statusLine);
        System.out.println(response.asString());  //whole response from server
        Assert.assertEquals(email,"rachel.howell@reqres.in");


    }


    }


