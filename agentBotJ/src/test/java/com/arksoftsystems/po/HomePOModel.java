package com.arksoftsystems.po;

import org.openqa.selenium.WebElement;

import com.arksoftsystems.client.ClientImpl;

public class HomePOModel extends ClientImpl {

	public HomePOModel() throws InterruptedException {
		super();
	}

	private String shadowRootChatBot = "#AgentAppContainer";

	private String chatButtonCss = "#aivochat-launcher > img";

	private String chatInputTextCss = "#agent-add-message > div.aivochat-input > input:nth-child(1)";

	private String sendMessageCss = "#agent-add-message > button.aivochat-send.custom-color";

	public WebElement getChatButton() {
		return shadowFindElement(shadowRootChatBot, chatButtonCss);
	}

	public WebElement getChatInputText() {
		return shadowFindElement(shadowRootChatBot, chatInputTextCss);
	}

	public WebElement sendMessage() {
		return shadowFindElement(shadowRootChatBot, sendMessageCss);
	}

	/**
	 * Wrapper of the click method to have contingency due to time errors
	 */
	public void clickInChatButton() {
		getChatButton().click();
	}

	/**
	 * Wrapper of the click method to have contingency due to time errors
	 */
	public void clickInSendMessage() {
		sendMessage().click();
	}

	/**
	 * @param value for send
	 * 
	 *              Only use the method 'shadowSendKey(String value)' when firefox
	 *              does not have support to interact with the element, otherwise,
	 *              get the element and use the native methods of selenium
	 * 
	 */
	public void sendKeyInChatBot(String value) {
		// The new method for firefox, chrome and opera support
		shadowSendKey(shadowRootChatBot, chatInputTextCss, value);
	}

}
