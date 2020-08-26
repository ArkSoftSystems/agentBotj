package com.arksoftsystems.client;

import java.util.List;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arksoftsystems.handlers.eventHandler;

/**
 * @author matiasc
 * 
 *         Experimental class for the implementation of the handling of
 *         shadowDom elements in different web platforms
 * 
 *         A time handler is not implemented, interrupts are implemented to
 *         experiment with the implementation
 * 
 */
public class ClientImpl extends Client {

	public static FluentWait<WebDriver> Wait;

	public static JavascriptExecutor JsExec;

	private int pullingTime = 2000;

	/**
	 * @throws InterruptedException
	 */
	public ClientImpl() throws InterruptedException {
		Wait = new WebDriverWait(getDriver(), 30000);
		JsExec = (JavascriptExecutor) getDriver();
	}

	/**
	 * @param shadowRoot  - The shadowRoot path
	 * @param cssSelector - The element path
	 * @param value       - The value for send
	 * 
	 *                    Only use when firefox does not have support to interact
	 *                    with the element, otherwise, get the element and use the
	 *                    native methods of selenium
	 * 
	 *                    Firefox errors with native text sending: JavaScript error:
	 *                    https://<url>/qa/latest/core.5.ee3de4d7efaf6df2340c.js,
	 *                    line 1: Error: Network Error console.warn: LoginRecipes:
	 *                    "getRecipes: falling back to a synchronous message for:"
	 *                    "<url>"
	 * 
	 *                    Support: This method is experimental and is supported in
	 *                    firefox, chrome and opera
	 * 
	 * 
	 */
	public void shadowSendKey(String shadowRoot, String cssSelector, String value) {
		String query = eventHandler.buildQuerySender(shadowRoot, cssSelector, value);
		Wait.until(driver -> {
			try {
				executeJavaScriptExecutor(query);
			} catch (Exception e) {
				String errMess = "invalid shadowRoot event"; // generic error -delete this
				throw new WebDriverException(errMess);
			}
			return true;
		});
	}

	/**
	 * @param shadowRoot  - The shadowRoot path
	 * @param cssSelector - The element path
	 * @return the WebElement
	 * @throws Exception
	 */
	public WebElement shadowFindElement(String shadowRoot, String cssSelector) {
		String query = eventHandler.buildQueryFinder(shadowRoot, cssSelector);
		WebElement element = Wait.until(driver -> {
			WebElement e = null;
			return waitForJavaScriptExecutor(e, query);
		});
		if (element == null) {
			throw new JavascriptException("shadowDom element error");
		}

		return element;
	}

	/**
	 * @param shadowRoot  - The shadowRoot path
	 * @param cssSelector - The element path
	 * @return the web element list
	 * 
	 *         Experimental method
	 */
	@SuppressWarnings("unchecked")
	public List<WebElement> shadowFindElements(String shadowRoot, String cssSelector) {
		String query = eventHandler.buildQueryFinders(shadowRoot, cssSelector);
		return Wait.until(driver -> {
			return (List<WebElement>) waitForJavaScriptExecutor(null, query);
		});

	}

	/**
	 * @param <TPage>
	 * @param page
	 * @return
	 */
	public <TPage extends ClientImpl> TPage getInstance(Class<TPage> page) {
		Object obj = PageFactory.initElements(getDriver(), page);
		return page.cast(obj);
	}

	/**
	 * @param <TPage>
	 * @param instancia
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <TPage extends ClientImpl> TPage As(Class<TPage> instancia) {
		try {
			return (TPage) this;
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return null;
	}

	/**
	 * @param e
	 * @param query
	 * @return
	 */
	public WebElement waitForJavaScriptExecutor(WebElement e, String query) {
		boolean flag = true;
		try {
			Thread.sleep(pullingTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (flag) {
			try {
				e = (WebElement) JsExec.executeScript(query);
				flag = false;
			} catch (JavascriptException ex) {
				flag = true;
			}
		}
		return e;
	}

	/**
	 * @param query
	 */
	public void executeJavaScriptExecutor(String query) {
		boolean flag = true;

		try {
			Thread.sleep(pullingTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (flag) {
			try {
				JsExec.executeScript(query);
				flag = false;
			} catch (JavascriptException ex) {
				flag = true;
			}
		}
	}

	/**
	 * @return the result of ready state of the page
	 */
	public boolean waitForPageLoad() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) JsExec.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return JsExec.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return Wait.until(jQueryLoad) && Wait.until(jsLoad);
	}

}
