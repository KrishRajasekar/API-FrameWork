package com.Api.Baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class A {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String property = System.getProperty("user.dir");
		System.out.println(property);

		Properties properties = new Properties();
		properties.load(
				new FileInputStream("C:\\Users\\HARII\\eclipse-workspace1\\APIAutomation\\Config\\congig.properties"));
		Object object = properties.get("url");
		String value = (String) object;
		System.out.println(value);
	}
}
