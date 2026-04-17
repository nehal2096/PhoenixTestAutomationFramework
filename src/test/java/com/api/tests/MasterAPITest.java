package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void verifyMasterAPIResponse()
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.headers("Authorization", AuthTokenProvider.getToken(Roles.FD))
		.when()
		.post("master")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		.body("data", Matchers.notNullValue())
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("mst_model"))
		.body("data.mst_oem.size()", Matchers.greaterThan(0))
		.body("data.mst_model.size()", Matchers.equalTo(3))
		.and().body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()));
		
	}
	@Test
	public void verifyMasterAPIResponseByPassingWrongEndpoint()
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.headers("Authorization", AuthTokenProvider.getToken(Roles.FD))
		.when()
		.post("masters")
		.then()
		.statusCode(404);
	}
}
