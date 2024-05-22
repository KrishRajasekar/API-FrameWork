package com.StepDefinition;

import org.junit.Assert;
import io.cucumber.java.en.Then;

public class CommanStep {

	@Then("User should verify the status code is {int}")
	public void user_should_verify_the_status_code_is(int expstatuscode) {
		int statuscode = TC1_LoginStep.globalData.getStatuscode();
		Assert.assertEquals("verify status code", expstatuscode, statuscode);
	}
}
