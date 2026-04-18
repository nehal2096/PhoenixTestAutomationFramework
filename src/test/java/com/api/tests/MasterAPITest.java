package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import groovyjarjarpicocli.CommandLine.Spec;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
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
	@Test
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
