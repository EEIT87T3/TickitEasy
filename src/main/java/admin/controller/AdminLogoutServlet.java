package admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AdminLogoutServlet
 */
@WebServlet("/admin/adminLogout")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //登出
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);//刪除session
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("adminLogin.jsp");
    }
}
