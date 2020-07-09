package com.arksoftsystems.client;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientImpl extends Client {

	public static WebDriverWait Wait;
	public static JavascriptExecutor JsExec;
	public static String criteria = "querySelector";
	public static String criteriaAll = "querySelectorAll";
	public static String shadow = "shadowRoot";
	public static String root = "document";

	public ClientImpl() {
		Wait = new WebDriverWait(getDriver(), 30);
		JsExec = (JavascriptExecutor) getDriver();
	}

	public WebElement shadowFindElement(String shadowRoot, String cssSelector) {
		String query = "return ".concat(root).concat(".").concat(criteria).concat("(\"").concat(shadowRoot)
				.concat("\")").concat(".").concat(shadow).concat(".").concat(criteria).concat("(\"").concat(cssSelector)
				.concat("\")");

		return Wait.until(driver -> {
			//System.out.println("From Uri: " + driver.getCurrentUrl() + "for: " + query + " [Shadow Dom Element]");
			System.out.println("[Pulling ShadowDom Element: " + cssSelector);
			return (WebElement) JsExec.executeScript(query);
		});
	}

	@SuppressWarnings("unchecked")
	public List<WebElement> shadowFindElements(String shadowRoot, String cssSelector) {
		String query = "return ".concat(root).concat(".").concat(criteria).concat("(\"").concat(shadowRoot)
				.concat("\")").concat(".").concat(shadow).concat(".").concat(criteriaAll).concat("(\"")
				.concat(cssSelector).concat("\")");

		return Wait.until(driver -> {
			//System.out.println("From Uri: " + driver.getCurrentUrl() + "for: " + query + " [Shadow Dom Element]");
			return (List<WebElement>) JsExec.executeScript(query);
		});

	}

	public <TPage extends ClientImpl> TPage getInstance(Class<TPage> page) {
		Object obj = PageFactory.initElements(getDriver(), page);
		return page.cast(obj);
	}

	@SuppressWarnings("unchecked")
	public <TPage extends ClientImpl> TPage As(Class<TPage> instancia) {
		try {
			return (TPage) this;
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return null;
	}
}
