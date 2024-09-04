package admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      //回到管理首頁 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        request.getRequestDispatcher("/admin/adminDashboard.jsp").forward(request, response);
	    }
	}