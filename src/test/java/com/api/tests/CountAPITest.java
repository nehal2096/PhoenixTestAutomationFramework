package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class CountAPITest {

	@Test(description="Verifying if the count api is giving proper response", groups= {"regression","api","smoke"})
	public void verifyCountAPIResposne()
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec())
		.body("message", Matchers.equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchemaFD.json"))
		.body("data.size()",Matchers.equalTo(3))
		.body("data",Matchers.notNullValue())
		.body("data.label", Matchers.notNullValue())
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));	

	}

	@Test(description="Verifying if the count api is giving correct status code for invalid token", groups= {"negative","regression","api","smoke"})
	public void verifyCountAPIResposneMissingAuthToken()
	{
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
