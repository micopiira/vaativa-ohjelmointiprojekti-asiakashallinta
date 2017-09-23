package me.micopiira.hibernatetest.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardResponse implements Response {
	private final String path;

	public ForwardResponse(String path) {
		this.path = path;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}
