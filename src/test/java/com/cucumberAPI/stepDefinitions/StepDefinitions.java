package com.cucumberAPI.stepDefinitions;

import com.cucumberAPI.resource.ApiResources;
import com.cucumberAPI.resource.TestData;
import com.cucumberAPI.utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestData data=new TestData();
    static String place_id;

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        resSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));


    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resources, String method) {

        ApiResources resourceAPI=ApiResources.valueOf(resources);
        System.out.println(resourceAPI.getResources());
        if (method.equalsIgnoreCase("POST"))
            response =res.when().post(resourceAPI.getResources());
        else if (method.equalsIgnoreCase("GET"))
            response =res.when().get(resourceAPI.getResources());



    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {

         assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        assertEquals(getJsonPath(response, keyValue),expectedValue);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resources) throws IOException {
        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resources,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);

    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }


}
