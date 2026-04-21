package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description="Create request payload for CreateJobAPI")
	public void setUp() {
		
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	@Test(description="Verifying if the create inwarranty job api is giving proper response", groups= {"regression","api","smoke"})
	public void createJobAPITest()
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
