package com.arksoftsystems.init;

import com.arksoftsystems.client.Client;
import com.arksoftsystems.config.Config;

public class ClientInit extends Client {

	public static void CloseBrowser() {
		getDriver().close();
		getDriver().quit();
	}

	public static void OpenBrowser() {
		setDriver(Config.createChromeDriver());
	}
}
