package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.Product;
import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest()
	{
		Customer customer = new Customer("Tej", "Shah", "9897987456", "", "tej@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("42", "test", "Ratan", "MG Road", "ICICI Colony", "4002", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(100), "19748714651568", "19748714651568", "19748714651568", DateTimeUtil.getTimeWithDaysAgo(100), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<>();
		problemList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(createJobPayload,Roles.FD))
				.when()
				.post("job/create")
				.then()
				.spec(SpecUtil.responseSpec())
				.body("data.id",Matchers.notNullValue());

	}

}
