package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

public class UserDetailsAPITest {

	@Test(description="Verifying if the user details are shown correctly", groups= {"regression","api","smoke"})
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
