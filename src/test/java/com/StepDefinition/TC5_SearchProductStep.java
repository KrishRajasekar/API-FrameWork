package com.StepDefinition;

import java.util.ArrayList;



import java.util.List;

import org.junit.Assert;

import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.Pojo.ProductSearch.SearchProduct_Input_Pojo;
import com.Api.Pojo.ProductSearch.SearchProduct_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_SearchProductStep extends BaseClassAPI {
	Response response;

	@Given("User add headers for searching the product")
	public void user_add_headers_for_searching_the_product() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);

	}

	@When("User add request body to search {string}")
	public void user_add_request_body_to_search(String text) {

		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo(text);
		addBody(searchProduct_Input_Pojo);

	}

	@When("User send {string} request for search product endpoint")
	public void user_send_request_for_search_product_endpoint(String type) {
		response = AddRequestType(type, EndPoints.SEARCHPRODUCT);
		int statusCode = GetStatusCode(response);
		
	}

	@Then("User should verify Searchproduct response message matches {string}")
	public void user_should_verify_searchproduct_response_message_matches(String expSeachProductSuccessMsg) {
		
		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String actmessage = searchProduct_Output_Pojo.getMessage();
		System.out.println("SearchProduct :"+actmessage);
		Assert.assertEquals("verify searchProduct Success Msg", expSeachProductSuccessMsg,actmessage);
		
	}

}
