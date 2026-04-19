package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest()
	{
		Customer customer = new Customer("Tej", "Shah", "9897987456", "", "tej@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("42", "test", "Ratan", "MG Road", "ICICI Colony", "4002", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2026-03-23T18:30:00.000Z", "19745714651468", "19745714651468", "19745714651468", "2026-03-23T18:30:00.000Z", 1, 2);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemArray = new Problems[1];
		problemArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(createJobPayload,Roles.FD))
				.when()
				.post("job/create")
				.then()
				.spec(SpecUtil.responseSpec())
				.body("data.id",Matchers.notNullValue());

	}

}
