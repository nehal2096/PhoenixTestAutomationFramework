package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	static String env;
	static String path = "config/config.properties" ;
	private ConfigManager()
	{

	}

	private static Properties prop = new Properties();
	static
	{
		env = System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		
		switch(env) {	
		case "dev" -> path="config/config.dev.properties";
	
		case "qa" -> path="config/config.qa.properties";
		
		case "uat" ->
			path="config/config.uat.properties";
			
		default -> path="config/config.qa.properties";	
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		//	File configFile = new File(System.getProperty("user.dir")+File.separator + "src" + File.separator + "test" + File.separator
		//		+ "resources" + File.separator + "config" + File.separator + "config.properties");
		//FileReader fr;

		if(input==null)
		{
			throw new RuntimeException();
		}
		try {
			//fr = new FileReader(configFile);
			//prop.load(fr);
			prop.load(input);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getProperty(String key) 
	{
		return prop.getProperty(key);
	}
}
