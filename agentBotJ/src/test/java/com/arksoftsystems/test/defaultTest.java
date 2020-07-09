package com.arksoftsystems.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arksoftsystems.init.ClientInit;
import com.arksoftsystems.po.HomePOModel;

public class defaultTest extends ClientInit {

	@Before
	public void setUp() throws Exception {
		OpenBrowser();
		getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		CloseBrowser();
	}

	@Test
	public void sendMessageTest() throws InterruptedException {

		getDriver().get("site");

		CurrentPage = (new HomePOModel().getInstance(HomePOModel.class));
		CurrentPage.As(HomePOModel.class).getChatButton().click();

		String messg1 = "Hi, this is my first message";
		CurrentPage.As(HomePOModel.class).getChatInputText().click();
		CurrentPage.As(HomePOModel.class).getChatInputText().sendKeys(messg1);
		CurrentPage.As(HomePOModel.class).sendMessage().click();

		// remove - only show
		Thread.sleep(5000);

		String messg2 = "Hi, this is my second message";
		CurrentPage.As(HomePOModel.class).getChatInputText().sendKeys(messg2);
		CurrentPage.As(HomePOModel.class).sendMessage().click();

		// remove - only show
		Thread.sleep(5000);

		String messg3 = "Hi, this is my third message";
		CurrentPage.As(HomePOModel.class).getChatInputText().sendKeys(messg3);
		CurrentPage.As(HomePOModel.class).sendMessage().click();

		// remove - only show
		Thread.sleep(5000);

		String messg4 = "Hi, this is my last message";
		CurrentPage.As(HomePOModel.class).getChatInputText().sendKeys(messg4);
		CurrentPage.As(HomePOModel.class).sendMessage().click();

		// remove - only show
		Thread.sleep(5000);

		String thanks = "This is a test done by matias carrion to test if shadowDom really works with Selenium (:";
		CurrentPage.As(HomePOModel.class).getChatInputText().sendKeys(thanks);
		CurrentPage.As(HomePOModel.class).sendMessage().click();

		// remove - only show
		Thread.sleep(10000);

	}

}
