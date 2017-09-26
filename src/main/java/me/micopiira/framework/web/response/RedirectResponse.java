package me.micopiira.framework.web.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectResponse implements Response {
	private final String s;

	public RedirectResponse(String s) {
		this.s = s;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + s);
	}
}
