package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;

public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			if (((Employee) httpSession.getAttribute("employee")).isManager())
				resp.sendRedirect("mdash.html");
			else resp.sendRedirect("edash.html");
		}
		else resp.sendRedirect("login.html");
	}
}
