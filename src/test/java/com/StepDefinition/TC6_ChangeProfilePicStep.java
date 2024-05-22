package com.StepDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.Pojo.ChangeProfilePic.ChangeProfilePic_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC6_ChangeProfilePicStep extends BaseClassAPI {
	Response response;

	@Given("User add headers and bearer authorization for accesing ChangeProfilePic endpoints")
	public void user_add_headers_and_bearer_authorization_for_accesing_change_profile_pic_endpoints() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalData.getLogtoken());
		Header h3 = new Header("Content-Type", "multipart/form-data");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		ListHeaders.add(h3);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);
	}

	@When("User addformData and initialize the file location")
	public void user_addform_data_and_initialize_the_file_location() {
		AddFormData("profile_picture",
				new File("C:\\Users\\HARII\\eclipse-workspace1\\APITESTING\\Images\\715vwvP5ZEL.png"));
	}

	@When("User send {string} request for change profile picture endpoint")
	public void user_send_request_for_change_profile_picture_endpoint(String type) {
		response = AddRequestType(type, EndPoints.CHANGEPROFILEPIC);
		int getStatusCode = GetStatusCode(response);
		TC1_LoginStep.globalData.getStatuscode();
	}

	@Then("User verify the Change Profile Picture response message matches with {string}")
	public void user_verify_the_change_profile_picture_response_message_matches_with(
			String expChangeProfileSuccessMsg) {

		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String actmessage = changeProfilePic_Output_Pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("Verify ChangeProfilePic Success Msg", expChangeProfileSuccessMsg, actmessage);

	}

}
