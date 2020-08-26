package com.arksoftsystems.handlers;

public class eventHandler {

	public static String criteria = "querySelector";

	public static String criteriaAll = "querySelectorAll";

	public static String shadow = "shadowRoot";

	public static String root = "document";

	public static String buildQueryFinder(String shadowRoot, String cssSelector) {
		String query = "return ".concat(root).concat(".").concat(criteria).concat("(\"").concat(shadowRoot)
				.concat("\")").concat(".").concat(shadow).concat(".").concat(criteria).concat("(\"").concat(cssSelector)
				.concat("\")");
		return query;
	}

	public static String buildQueryFinders(String shadowRoot, String cssSelector) {
		String query = "return ".concat(root).concat(".").concat(criteria).concat("(\"").concat(shadowRoot)
				.concat("\")").concat(".").concat(shadow).concat(".").concat(criteriaAll).concat("(\"")
				.concat(cssSelector).concat("\")");
		return query;
	}

	public static String buildQuerySender(String shadowRoot, String cssSelector, String value) {
		String query = root.concat(".").concat(criteria).concat("(\"").concat(shadowRoot).concat("\")").concat(".")
				.concat(shadow).concat(".").concat(criteria).concat("(\"").concat(cssSelector).concat("\")")
				.concat(".value='" + value + "'");
		return query;
	}

}
