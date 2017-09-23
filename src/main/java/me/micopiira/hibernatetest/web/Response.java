package me.micopiira.hibernatetest.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Response {
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
