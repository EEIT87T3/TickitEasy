package admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.service.MemberService;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class MemberStatisticsServlet
 */
@WebServlet("/admin/memberStatistics")
public class MemberStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberStatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private MemberService memberService;// 用於取得會員統計資料
    private Gson gson;// 用於將物件轉換成JSON

    // 初始化
    public void init() {
        memberService = new MemberService();
        gson = new Gson();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Map<String, Object> statistics = memberService.getMemberStatistics();// 取得會員統計資料
        
        // 將統計資料轉換成JSON
        String statisticsJson = gson.toJson(statistics);
        
        //將統計資料內容轉換成JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        //將統計資料內容回傳
        response.getWriter().write(statisticsJson);
    }
}