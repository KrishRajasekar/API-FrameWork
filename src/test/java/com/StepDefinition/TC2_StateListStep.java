package com.StepDefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.Pojo.Address.State;
import com.Api.Pojo.Address.StateList_output_pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC2_StateListStep extends BaseClassAPI {
	Response response;

	@Given("User add Headers for the statelist")
	public void user_add_headers_for_the_statelist() {
		Setheader("accept", "application/json");
	}

	@When("User send {string} request for statelist endpoint")
	public void user_send_request_for_statelist_endpoint(String type) {
		response = AddRequestType(type, EndPoints.STATELIST);
		int statusCode = response.getStatusCode();
		TC1_LoginStep.globalData.setStatuscode(statusCode);
	}

	@Then("User should verify the statelist response messgae matches {string} and saved state ID")
	public void user_should_verify_the_statelist_response_messgae_matches_and_saved_state_id(String expStateName) {

		StateList_output_pojo StateListPojo = response.as(StateList_output_pojo.class);
		ArrayList<State> state = StateListPojo.getData();
		for (State eachstatelist : state) {
			String statename = eachstatelist.getName();
			int statelistnum = eachstatelist.getId();
			if (statename.equals(expStateName)) {
				System.out.println(statelistnum);
				String stateid = String.valueOf(statelistnum);
				TC1_LoginStep.globalData.setStateIdtext(stateid);
				System.out.println(stateid);
				Assert.assertEquals("Verify StateName", expStateName, statename);
				TC1_LoginStep.globalData.setStateIdNum(statelistnum);
				break;
			}
		}

	}

}
