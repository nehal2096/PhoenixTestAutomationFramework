package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class CountAPITest {

	@Test
	public void verifyCountAPIResposne()
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header("Authorization",AuthTokenProvider.getToken(Roles.FD))
		.when()
		.get("dashboard/count")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		.body("message", Matchers.equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchemaFD.json"))
		.body("data.size()",Matchers.equalTo(3))
		.body("data",Matchers.notNullValue())
		.body("data.label", Matchers.notNullValue())
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));	
		
	}

	@Test
	public void verifyCountAPIResposneMissingAuthToken()
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
