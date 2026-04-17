package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException
	{
		Header header = new Header("Authorization", AuthTokenProvider.getToken(Roles.FD));
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header(header)
		.when()
		.get("userdetails")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(2000L));
		
		
	}
	
}
