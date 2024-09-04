package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.bean.MemberBean;
import member.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 private MemberService memberService;

	    @Override
		public void init() {
	        memberService = new MemberService();
	    }

	    @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        MemberBean member = memberService.login(email, password);
	        if (member != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("member", member);
	            response.getWriter().write("success");
	        } else {
	            response.getWriter().write("無效的電子郵件或密碼");
	        }
	    }
	}