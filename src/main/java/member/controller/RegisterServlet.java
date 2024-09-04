package member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.bean.MemberBean;
import member.service.MemberService;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/member/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    public void init() {
        memberService = new MemberService();
    }
    //處理POST請求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String birthDateStr = request.getParameter("birthDate");
        String phone = request.getParameter("phone");

        MemberBean newMember = new MemberBean();// 新增會員
        newMember.setEmail(email);
        newMember.setPassword(password);
        newMember.setName(name);
        newMember.setNickname(nickname);
        newMember.setBirthDate(LocalDate.parse(birthDateStr));
        newMember.setPhone(phone);

        int result = memberService.registerMember(newMember);// 呼叫MemberService的registerMember方法

        if (result > 0) {
            response.sendRedirect("registerSuccess.jsp");// 註冊成功，重定向到registerSuccess.jsp
        } else {
            response.sendRedirect("registerFailed.jsp");// 註冊失敗，重定向到registerFailed.jsp
        }
    }
}