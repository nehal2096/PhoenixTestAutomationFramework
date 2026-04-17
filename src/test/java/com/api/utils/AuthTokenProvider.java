package com.api.utils;

import com.api.constant.Roles;
import com.api.pojo.UserCredentials;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider()
	{
		
	}
	
	public static String getToken(Roles role) {
		
		UserCredentials userCredentails=null;
		if(role==Roles.FD)
		{
			userCredentails = new UserCredentials("iamfd","password");
		}
		else if(role == Roles.SUP)
		{
			userCredentails = new UserCredentials("iamsup","password");
		}
		else if(role == Roles.QC)
		{
			userCredentails = new UserCredentials("iamqc","password");
		}
		else if(role == Roles.ENG)
		{
			userCredentails = new UserCredentials("iameng","password");
		}
		
			String token =	given()
				.baseUri(ConfigManager.getProperty("BASE_URI"))
				.contentType(ContentType.JSON)
				.body(userCredentails)
				.when()
				.post("login")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.extract().body().jsonPath().getString("data.token");
			return token;
				

	}

}
