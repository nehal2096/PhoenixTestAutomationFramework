package com.api.tests.datadriven;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;


public class LoginAPIDataDrivenTest {

	private UserCredentials userCredentials;

	@BeforeMethod(description="Create request payload for Login API")
	public void setUp()
	{
		userCredentials= new UserCredentials("iamfd","password");
	}

	@Test(description = "Verifying if the login API is working for user iamfd", 
			groups= {"regression","smoke","api"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class,
			dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userBean) throws IOException
	{
		given()
		.spec(SpecUtil.requestSpec(userBean))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpec())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));

	}

}