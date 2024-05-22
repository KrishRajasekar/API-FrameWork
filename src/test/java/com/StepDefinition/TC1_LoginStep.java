package com.StepDefinition;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.junit.Assert;

import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.GlobalData.GlobalData;
import com.Api.Pojo.Login.Login_output_pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClassAPI {

	static GlobalData globalData = new GlobalData();

	Response response;

	@Given("User add header")
	public void user_add_header() {
		Setheader("accept", "application/json");

	}

	@When("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		AddBasicAuth(GetPropertyFileValue("username"), GetPropertyFileValue("password"));
	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String type) {
		response = AddRequestType(type, EndPoints.LOGIN);
		int statuscode = GetStatusCode(response);
		globalData.setStatuscode(statuscode);
	}

	@Then("User should verify the login response body First_name present as {string} and get the logtoken saved")
	public void user_should_verify_the_login_response_body_first_name_present_as_and_get_the_logtoken_saved(
			String expfirstname) {
		Login_output_pojo login_output_pojo = response.as(Login_output_pojo.class);
		String actfirstname = login_output_pojo.getData().getFirst_name();
		Assert.assertEquals("verify firstname of login endpoint", expfirstname, actfirstname);
		String logtoken = login_output_pojo.getData().getLogtoken();
		globalData.setLogtoken(logtoken);

	}

}
