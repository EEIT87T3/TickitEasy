package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import post.bean.PostBean;
import post.dao.PostDao;
import post.dao.impl.PostDaoImpl;
import post.service.PostService;
import util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/InsertPost") 
public class InsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		doPost(request, response);
	}

 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
 	        throws ServletException, IOException {
 	  
 		// 從表單中讀取參數
 		int themeID = Integer.parseInt(request.getParameter("themeID"));
 	    int memberID = Integer.parseInt(request.getParameter("memberID"));
 	    String postTitle = request.getParameter("postTitle");
 	    String postContent = request.getParameter("postContent");
 	    String postImgUrl = request.getParameter("postImgUrl");
 	    // 獲取當前時間
 	    Timestamp postTime = new Timestamp(System.currentTimeMillis()); // 使用 Timestamp
 	    int status = Integer.parseInt(request.getParameter("status"));
		
 	    System.out.println("servlet");
 	    
 	    // 創建 PostBean 實例
 	    PostBean post = new PostBean();
 	    post.setThemeID(themeID);
 	    post.setMemberID(memberID);
 	    post.setPostTitle(postTitle);
 	    post.setPostContent(postContent);
 	    post.setPostImgUrl(postImgUrl);
 	    post.setPostTime(postTime);
 	    post.setStatus(status);

 	    // postService 呼叫 postDao 方法 傳post過去
 	    //回傳改過的post 可以用變數接起來(這裡是insert 也可以不用
 	    //shit + alt + l
 	    PostBean insert = postService.insert(post);
 	    System.out.println(insert.getPostTitle());


 	    // 重定向或轉發到其他頁面
 	   request.getRequestDispatcher("/GetAllPost")
 	   .forward(request, response);
 	}
}