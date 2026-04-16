package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;


public class LoginAPITest {

	UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		.log().uri()
		.log().headers()
		.log().method()
		.log().body()
		.when()
		.post("login")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000L))
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
		
	}
	
}
