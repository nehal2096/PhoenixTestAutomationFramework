package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec());

	}

}
