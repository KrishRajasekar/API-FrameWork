package com.StepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.Pojo.Address.CityList_Output_Pojo;
import com.Api.Pojo.Address.City_Input_Pojo;
import com.Api.Pojo.Address.citydata;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_CityListStep extends BaseClassAPI {
	Response response;

	@Given("User add headers for to get citylist")
	public void user_add_headers_for_to_get_citylist() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);
	}

	@When("User add request bodu stateid for get city list")
	public void user_add_request_bodu_stateid_for_get_city_list() {
		City_Input_Pojo city_Input_Pojo = new City_Input_Pojo(TC1_LoginStep.globalData.getStateIdtext());
		addBody(city_Input_Pojo);
	}

	@When("User send {string} request for citylist endpoint")
	public void user_send_request_for_citylist_endpoint(String type) {
	response = AddRequestType(type, EndPoints.CITYLIST);
		int statuscode = GetStatusCode(response);
		TC1_LoginStep.globalData.setStatuscode(statuscode);
	}

	@Then("User berify the citylist response message matches {string} and saved city ID")
	public void user_berify_the_citylist_response_message_matches_and_saved_city_id(String expCityName) {
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<citydata> data = cityList_Output_Pojo.getData();
		for (citydata eachcitylist : data) {
			String name = eachcitylist.getName();
			if (name.equals("Komarapalayam")) {
				int citylistnum = eachcitylist.getId();
				System.out.println(citylistnum);
				Assert.assertEquals("verify cityname", expCityName, name);
				TC1_LoginStep.globalData.setCityIdNum(citylistnum);
				break;
			}
		}

	}
}
