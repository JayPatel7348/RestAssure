package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ApiStepDefinitions {

    Response response;
    String endpoint;
    JSONObject requestBody;



    @Given("I have the API endpoint {string}")
    public void i_have_the_api_endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        response = RestAssured.get(endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        System.out.println(response.asString());
        response.then().statusCode(statusCode);
    }

    @And("the response body should contain {string}")
    public void the_response_body_should_contain(String expectedContent) {
        String responseBody = response.getBody().asString();
        assertThat(responseBody, containsString(expectedContent));
    }

    @Given("I have the login API endpoint {string}")
    public void iHaveTheLoginAPIEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @And("I have the request body with email {string} and password {string}")
    public void iHaveTheRequestBodyWithEmailAndPassword(String email, String password) {
        requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

    }

    @When("I send a POST request to the endpoint")
    public void iSendAPOSTRequestToTheEndpoint() {
       RestAssured.baseURI="https://bostonmarketbackend.elaunchinfotech.in/api";
        response = RestAssured.given()
                .header("env","test")
                .header("Content-Type","application/json")
                .body(requestBody.toJSONString())
                .post(endpoint);
    }

    @And("the response body should contain {string} and {string}")
    public void the_response_body_should_contain(String accessToken,String refreshToken) {
        String responseBody = response.getBody().asString();
//        String rToken=response.jsonPath().getString(refreshToken);
//        String aToken=response.jsonPath().getString(accessToken);
        System.out.println(response.asString());
        assertThat(responseBody, containsString(accessToken));

    }


    @And("I have the request body with {},{}")
    public void iHaveTheRequestBodyWithEmailEmailAndPasswordPassword(String email,String password) {
        requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);
    }

}