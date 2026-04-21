package com.api.tests.datadriven;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;


public class CreateJobAPIDataDrivenTestUsingFaker {

	private UserCredentials userCredentials;

	@BeforeMethod(description="Create request payload for Login API")
	public void setUp()
	{
		userCredentials= new UserCredentials("iamfd","password");
	}

	@Test(description = "Verifying if the create job API is working for user iamfd", 
			groups= {"regression","smoke","api"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class,
			dataProvider = "CreateJobAPIFakeDataProvider")
	public void loginAPITest(CreateJobPayload createJobPayload) throws IOException
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(createJobPayload,Roles.FD))
				.when()
				.post("job/create")
				.then()
				.spec(SpecUtil.responseSpec())
				.body("data.id",Matchers.notNullValue());

	}

}