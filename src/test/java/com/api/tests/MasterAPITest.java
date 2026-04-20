package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

public class MasterAPITest {

	@Test(description="Verifying if the Master API is giving proper response", groups= {"regression","api","smoke"})
	public void verifyMasterAPIResponse()
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec())
		.body("data", Matchers.notNullValue())
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("mst_model"))
		.body("data.mst_oem.size()", Matchers.greaterThan(0))
		.body("data.mst_model.size()", Matchers.equalTo(3))
		.and().body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()));

	}
	@Test(description="Verifying if the master api is giving correct status code for invalid request", groups= {"negative","regression","api","smoke"})
	public void verifyMasterAPIResponseByPassingWrongEndpoint()
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.post("masters")
		.then()
		.statusCode(404);
	}
}
