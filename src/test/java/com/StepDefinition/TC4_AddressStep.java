package com.StepDefinition;



import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.Api.Baseclass.BaseClassAPI;
import com.Api.EndPoints.EndPoints;
import com.Api.Pojo.Address.AddAddress_Input_pojo;
import com.Api.Pojo.Address.AddAddress_output_Pojo;
import com.Api.Pojo.Address.DeletAddress_Output_Pojo;
import com.Api.Pojo.Address.DeleteAddress_Input_Pojo;
import com.Api.Pojo.Address.GetUserAdd_Output_Pojo;
import com.Api.Pojo.Address.UpdateAddress_Input_Pojo;
import com.Api.Pojo.Address.UpdateAddress_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClassAPI {

	Response response;
	// AddAddress

	@Given("User add headers and bearer authorization for accesing adddress endpoints")
	public void user_add_headers_and_bearer_authorization_for_accesing_adddress_endpoints() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalData.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		ListHeaders.add(h3);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);
	}

	@When("User add requestbody for AddNewAddress {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_add_requestbody_for_add_new_address(String firstName, String lastName, String mobileNo,
			String appartment, String state, String city, String country, String zipcode, String address,
			String addressType) {

		AddAddress_Input_pojo AddAddressPojo = new AddAddress_Input_pojo(firstName, lastName, mobileNo, appartment,
				TC1_LoginStep.globalData.getStateIdNum(), TC1_LoginStep.globalData.getCityIdNum(),
				Integer.parseInt(country), zipcode, address, addressType);
		addBody(AddAddressPojo);
	}

	@When("User send {string} request for adduseraddress endpoint")
	public void user_send_request_for_adduseraddress_endpoint(String type) {
		response = AddRequestType(type, EndPoints.CREATEADDRESS);
		int statusCode = GetStatusCode(response);
		TC1_LoginStep.globalData.setStatuscode(statusCode);
	}

	@Then("User should verify adduseraddress response message matches {string}")
	public void user_should_verify_adduseraddress_response_message_matches(String expaddAddressSuccessMsg) {

		AddAddress_output_Pojo AddAddressOutPojo = response.as(AddAddress_output_Pojo.class);
		String actmessage = AddAddressOutPojo.getMessage();
		Assert.assertEquals("verify addaddress success msg", expaddAddressSuccessMsg, actmessage);
		int address_id = AddAddressOutPojo.getAddress_id();
		String AddID = String.valueOf(address_id);
		TC1_LoginStep.globalData.setAddId_Text(AddID);

	}

//update address

	@Given("User add headers and bearer authorization for accesing Updateadddress endpoints")
	public void user_add_headers_and_bearer_authorization_for_accesing_updateadddress_endpoints() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalData.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		ListHeaders.add(h3);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);
	}

	@When("User add requestbody for UpdateNewAddress {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_add_requestbody_for_update_new_address(String addressId, String firstName, String lastName,
			String mobileNo, String appartment, String state, String city, String country, String zipcode,
			String address, String addressType) {

		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(
				TC1_LoginStep.globalData.getAddId_Text(), firstName, lastName, mobileNo, appartment,
				TC1_LoginStep.globalData.getStateIdNum(), TC1_LoginStep.globalData.getCityIdNum(),
				Integer.parseInt(country), zipcode, address, addressType);
		addBody(updateAddress_Input_Pojo);
	}

	@When("User send {string} request for AddUserAddress endpoint")
	public void user_send_request_for_add_user_address_endpoint(String type) {

		response = AddRequestType(type, EndPoints.UPDATEADDRESS);
		int getStatusCode = GetStatusCode(response);
		System.out.println(getStatusCode);

	}

	@Then("User should verify updateaddress response message matches {string}")
	public void user_should_verify_updateaddress_response_message_matches(String expUpdateAddressSuccessMsg) {
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String actmessage = updateAddress_Output_Pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("verify updateAddress Success Msg", expUpdateAddressSuccessMsg, actmessage);

	}
	// getuseraddress

	@Given("User add headers and bearer authorization for accesing Getadddress endpoints")
	public void user_add_headers_and_bearer_authorization_for_accesing_getadddress_endpoints() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalData.getLogtoken());
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);

	}

	@When("User send {string} request for GetUserAddress endpoint")
	public void user_send_request_for_get_user_address_endpoint(String type) {
		response = AddRequestType(type, EndPoints.GETUSERADDRESS);
		int getStatusCode = GetStatusCode(response);
		TC1_LoginStep.globalData.getStatuscode();

	}

	@Then("User should verify getuseraddress response message matches {string}")
	public void user_should_verify_getuseraddress_response_message_matches(String expGetUserAddressSuccessMsg) {
		GetUserAdd_Output_Pojo getUserAdd_Output_Pojo = response.as(GetUserAdd_Output_Pojo.class);
		String actmessage = getUserAdd_Output_Pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("verify msg", expGetUserAddressSuccessMsg, actmessage);

	}

	// deleteaddress

	@Given("User add headers and bearer authorization for accesing Deleteadddress endpoints")
	public void user_add_headers_and_bearer_authorization_for_accesing_deleteadddress_endpoints() {
		List<Header> ListHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalData.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		ListHeaders.add(h1);
		ListHeaders.add(h2);
		ListHeaders.add(h3);
		Headers headers = new Headers(ListHeaders);
		Setheaders(headers);
	}

	@When("User add request body for address ID")
	public void user_add_request_body_for_address_id() {
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(
				TC1_LoginStep.globalData.getAddId_Text());
		addBody(deleteAddress_Input_Pojo);
	}

	@When("User send {string} request for DeleteAddress endpoint")
	public void user_send_request_for_delete_address_endpoint(String type) {

		response = AddRequestType(type, EndPoints.DELETEADDRESS);
		int getStatusCode = GetStatusCode(response);
		TC1_LoginStep.globalData.getStatuscode();

	}

	@Then("User should verify DeleteAddress response message matches {string}")
	public void user_should_verify_delete_address_response_message_matches(String expDeleteAddressSuccessMsg) {
		
		DeletAddress_Output_Pojo deletAddress_Output_Pojo = response.as(DeletAddress_Output_Pojo.class);
		String actmessage = deletAddress_Output_Pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("verify DeleteAddressSuccessMsg", expDeleteAddressSuccessMsg,actmessage);
	}

}
