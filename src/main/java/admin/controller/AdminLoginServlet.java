package admin.controller;

import java.io.IOException;

import admin.bean.AdminBean;
import admin.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/admin/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	//初始化
    @Override
	public void init() {
        adminService = new AdminService();
    }
    //登入
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            AdminBean admin = adminService.login(email, password);//用email和密碼登入
            if (admin != null) {
                HttpSession session = request.getSession();//創建一個session
                session.setAttribute("admin", admin);//放入session
                response.getWriter().write("success");//登入成功
            } else {
                response.getWriter().write("無效的電子郵件或密碼");//登入失敗
            }
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
			e.printStackTrace();
        }
    }
}