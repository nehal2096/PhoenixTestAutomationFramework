package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;


public class LoginAPITest {

	private UserCredentials userCredentials;

	@BeforeMethod(description="Create request payload for Login API")
	public void setUp()
	{
		userCredentials= new UserCredentials("iamfd","password");
	}

	@Test(description = "Verifying if the login API is working for user iamfd", groups= {"regression","smoke","api"})
	public void loginAPITest() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpec(userCredentials))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpec())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));

	}

}
