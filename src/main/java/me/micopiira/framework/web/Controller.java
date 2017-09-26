package me.micopiira.framework.web;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Controller {
	private HttpServletRequest request;
	private static final String MESSAGES_KEY = "messages";

	private List<String> getMessages() {
		return Optional.ofNullable((List<String>) getRequest().getSession(true).getAttribute(MESSAGES_KEY)).orElseGet(ArrayList::new);
	}

	protected void addMessage(String key) {
		final List<String> messages = getMessages();
		messages.add(key);
		getRequest().getSession().setAttribute(MESSAGES_KEY, messages);
	}

	protected String getRequiredParameter(String s) {
		return Optional.ofNullable(getRequest().getParameter(s)).orElseThrow(() -> new RuntimeException("Missing required parameter '" + s + "'"));
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
