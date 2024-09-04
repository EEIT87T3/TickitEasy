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
import util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;

@WebServlet("/UpdatePost") //url-pattern//difference
public class UpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	Connection connection=null;
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		
	}

 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
 	        throws ServletException, IOException {
 	    // 從表單中讀取參數
 
 		int postID = Integer.parseInt(request.getParameter("postID"));
 	    int memberID = Integer.parseInt(request.getParameter("memberID"));
 	    int themeID = Integer.parseInt(request.getParameter("themeID"));
 	    String postTitle = request.getParameter("postTitle");
 	    String postContent = request.getParameter("postContent");
 	    String postImgUrl = request.getParameter("postImgUrl");
 	   
 	    // 獲取當前時間
//        java.util.Date utilDate = new java.util.Date();
//        // 將 java.util.Date 轉換為 java.sql.Date
//        Date postTime = new Date(utilDate.getTime());
 	   Timestamp postTime = new Timestamp(System.currentTimeMillis()); // 使用 Timestamp


 	   

 	    // 創建 PostBean 實例
 	    PostBean post = new PostBean();
 	 
 	    post.setPostID(postID);
 	    post.setMemberID(memberID);
 	    post.setThemeID(themeID);
 	    post.setPostTitle(postTitle);
 	    post.setPostContent(postContent);
 	    post.setPostImgUrl(postImgUrl);
 	    post.setPostTime(postTime);
 	 

 	    // 使用 PostDao 插入數據
 	    try (Connection connection = ConnectionUtil.getConnection()) {
 	        PostDao postDao = new PostDaoImpl(connection);
 	        postDao.update(post);
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }

 	    // 重定向或轉發到其他頁面
 	   request.getRequestDispatcher("/GetPost").forward(request, response);
 	}
}