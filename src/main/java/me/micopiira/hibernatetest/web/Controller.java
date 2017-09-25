package me.micopiira.hibernatetest.web;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

abstract class Controller {
	private HttpServletRequest request;
	private static final String MESSAGES_KEY = "messages";

	List<String> getMessages() {
		return Optional.ofNullable((List<String>) getRequest().getSession(true).getAttribute(MESSAGES_KEY)).orElseGet(ArrayList::new);
	}

	void addMessage(String key) {
		List<String> messages = getMessages();
		messages.add(key);
		getRequest().getSession().setAttribute(MESSAGES_KEY, messages);
	}

	String getRequiredParameter(String s) {
		return Optional.ofNullable(getRequest().getParameter(s)).orElseThrow(() -> new RuntimeException("Missing required paramter '" + s + "'"));
	}

	HttpServletRequest getRequest() {
		return request;
	}

	void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
