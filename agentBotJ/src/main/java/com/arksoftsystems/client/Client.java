package com.arksoftsystems.client;

import org.openqa.selenium.WebDriver;

public class Client {

	private static WebDriver driver;
	public static ClientImpl CurrentPage;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Client.driver = driver;
	}

}
