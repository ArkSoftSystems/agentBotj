package com.arksoftsystems.po;

import org.openqa.selenium.WebElement;

import com.arksoftsystems.client.ClientImpl;

public class HomePOModel extends ClientImpl {

	public WebElement getChatButton() {
		return shadowFindElement("#AgentAppContainer", "#aivochat-launcher > img");
	}

	public WebElement getChatInputText() {
		return shadowFindElement("#AgentAppContainer", "#agent-add-message > div.aivochat-input > input[type=text]");
	}

	public WebElement sendMessage() {
		return shadowFindElement("#AgentAppContainer", "#agent-add-message > button.aivochat-send.custom-color");
	}

}
