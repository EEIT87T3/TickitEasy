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


@WebServlet("/UpdatePost") 
public class UpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
       
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		doPost(request, response);
	}

 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
 	        throws ServletException, IOException {
 	    // 從表單中讀取參數
 
 		int postID = Integer.parseInt(request.getParameter("postID"));
 	    int memberID = Integer.parseInt(request.getParameter("memberID"));
 	    int themeID = Integer.parseInt(request.getParameter("themeID"));
 	    String postTitle = request.getParameter("postTitle");
 	    String postContent = request.getParameter("postContent");
 	    int likesCount = 0;
 	    int viewCount = 0;
 	    String postImgUrl = request.getParameter("postImgUrl");
 	    // 獲取當前時間
 	    Timestamp postTime = new Timestamp(System.currentTimeMillis()); // 使用 Timestamp
 		int status = Integer.parseInt(request.getParameter("status"));
 	    
 		// 創建 PostBean 實例
 	    PostBean post = new PostBean();
 	    post.setPostID(postID);
 	    post.setMemberID(memberID);
 	    post.setThemeID(themeID);
 	    post.setPostTitle(postTitle);
 	    post.setPostContent(postContent);
 	    post.setLikesCount(likesCount);
 	    post.setViewCount(viewCount);
 	    post.setPostImgUrl(postImgUrl);
 	    post.setPostTime(postTime);
 	    post.setStatus(status);
 	 
 	    postService.update(postID,post);

 	    // 重定向或轉發到其他頁面
 	   request.getRequestDispatcher("/GetPost").forward(request, response);
 	}
}