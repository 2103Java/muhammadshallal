//package com.revature.request;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.revature.model.Employee;
//
//
//@WebServlet("/ERS/DispatcherServlet")
//public class DispatcherServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//	
//	public DispatcherServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession httpSession = req.getSession(false);
//		
//		if (httpSession != null) {
//			System.out.println("httpsession != null");
//			if (((Employee) httpSession.getAttribute("employee")).getIsManager())
//				resp.sendRedirect("mdash.html");
//			else resp.sendRedirect("edash.html");
//		} else {
//			System.out.println("httpsession == null");
//			resp.sendRedirect("index.html");
//		} 
//	}
//}
