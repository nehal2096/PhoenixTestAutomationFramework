package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private static Random random = new Random();
	private static int mst_service_location_id = 0;
	private static int mst_platform_id=2;
	private static int mst_warrenty_status_id=1;
	private static int mst_oem_id=1;
	private static int product_id=1;
	private static int model_id=1;
	

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		List<Problems> problemsList = generateFakeProblemsList();
		
		CreateJobPayload createJobPayload = new CreateJobPayload(mst_service_location_id,mst_platform_id,mst_warrenty_status_id,mst_oem_id,customer,customerAddress,customerProduct,problemsList);
		return createJobPayload;
	}
	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for(int i=1; i<=count; i++)
		{
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		List<Problems> problemsList = generateFakeProblemsList();
		
		CreateJobPayload createJobPayload = new CreateJobPayload(mst_service_location_id,mst_platform_id,mst_warrenty_status_id,mst_oem_id,customer,customerAddress,customerProduct,problemsList);
		payloadList.add(createJobPayload);
		}
		return payloadList.iterator();
	}
	
	

	private static List<Problems> generateFakeProblemsList() {

		int problemId = random.nextInt(27) + 1;
		String fakeRemark = faker.lorem().sentence(5);

		Problems problems = new Problems(problemId, fakeRemark);

		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {

		String dop = DateTimeUtil.getTimeWithDaysAgo(200);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, product_id, model_id);

		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddress() {

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pincode, COUNTRY, state);

		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("70########");
		String altMobileNumber = faker.numerify("70########");
		String emailAddress = faker.internet().emailAddress();
		String altEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, emailAddress, altEmailAddress);

		return customer;
	}

}
