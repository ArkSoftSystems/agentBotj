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
		getDriver().manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		CloseBrowser();
	}

	@Test
	public void sendMessageTest() throws InterruptedException {

		getDriver().get("<Insert the web page>");

		CurrentPage = (new HomePOModel().getInstance(HomePOModel.class));
		CurrentPage.As(HomePOModel.class).clickInChatButton();

		String messg1 = "Hi, this is my first message";
		CurrentPage.As(HomePOModel.class).getChatInputText().click();
		CurrentPage.As(HomePOModel.class).sendKeyInChatBot(messg1); // for firefox support (chrome and opera support)
		CurrentPage.As(HomePOModel.class).clickInSendMessage();

		String messg2 = "Hi, this is my second message";
		CurrentPage.As(HomePOModel.class).sendKeyInChatBot(messg2);
		CurrentPage.As(HomePOModel.class).clickInSendMessage();

		String messg3 = "Hi, this is my third message";
		CurrentPage.As(HomePOModel.class).sendKeyInChatBot(messg3);
		CurrentPage.As(HomePOModel.class).clickInSendMessage();

		String messg4 = "Hi, this is my last message";
		CurrentPage.As(HomePOModel.class).sendKeyInChatBot(messg4);
		CurrentPage.As(HomePOModel.class).clickInSendMessage();

		String thanks = "This is a test done by matias carrion to test if shadowDom really works with Selenium (:";
		CurrentPage.As(HomePOModel.class).sendKeyInChatBot(thanks);
		CurrentPage.As(HomePOModel.class).clickInSendMessage();

		Thread.sleep(5000); // remove
	}

}
