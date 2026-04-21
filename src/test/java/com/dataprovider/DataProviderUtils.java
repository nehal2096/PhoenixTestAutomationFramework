package com.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.pojo.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider" , parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider()
	{
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
	}
	

	@DataProvider(name = "CreateJobAPIFakeDataProvider" , parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIFakeDataProvider()
	{
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(10);
		return payloadIterator;
	}
	

}

