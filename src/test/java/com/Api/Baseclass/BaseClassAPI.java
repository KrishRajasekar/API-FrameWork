package com.Api.Baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClassAPI {
	RequestSpecification reqSpec;
	Response response;

	public String GetProjectPath() {
		String property = System.getProperty("user.dir");
		return property;

	}

	public String GetPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(GetProjectPath() + "\\Config\\congig.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		return value;

	}

	public void Setheader(String headName, String headValue) {
		reqSpec = RestAssured.given().header(headName, headValue);
	}

	public void Setheaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

	public void AddBasicAuth(String username, String password) {
		reqSpec.auth().preemptive().basic(username, password);
	}

	public Response AddRequestType(String reqtype, String endpoint) {

		switch (reqtype) {
		case "GET":
			response = reqSpec.get(endpoint);
			break;
		case "POST":
			response = reqSpec.post(endpoint);
			break;
		case "PUT":
			response = reqSpec.put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endpoint);
			break;
		default:
			break;
		}
		return response;
	}

	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	public void addBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	public int GetStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String GetAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	public String GetAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}

	public void AddFormData(String key, File pathFile) {
		reqSpec = reqSpec.multiPart(key, pathFile);

	}

}
