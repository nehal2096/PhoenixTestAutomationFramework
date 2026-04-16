package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest()
	{
		Header header = new Header("Authorization","");
		given()
		.baseUri("http://64.227.160.186:9000/v1")
		.header(header)
		.when()
		.get("userdetails")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(200L));
		
		
	}
	
}
