package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;
import post.bean.CommentBean;
import post.dao.CommentDao;
import post.dao.impl.CommentDaoImpl;
import util.ConnectionUtil;

@WebServlet("/InsertComment") //url-pattern//difference
public class InsertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Connection connection=null;

 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

 	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
 	        throws ServletException, IOException {
 	    // 從表單中讀取參數
 	    int postID = Integer.parseInt(request.getParameter("postID"));
 	    int memberID = Integer.parseInt(request.getParameter("memberID"));
 	    String content = request.getParameter("content");
// 	    String commentDate = request.getParameter("commentDate");

 	    // 獲取當前時間
//        java.util.Date utilDate = new java.util.Date();
//        // 將 java.util.Date 轉換為 java.sql.Date
//        Date postTime = new Date(utilDate.getTime());
 	   Timestamp postTime = new Timestamp(System.currentTimeMillis()); // 使用 Timestamp


 	    int status = Integer.parseInt(request.getParameter("status"));

 	    // 創建 CommentBean 實例
	 	   CommentBean comment = new CommentBean();
	 	   comment .setPostID(postID);
	 	   comment .setMemberID(memberID);
	 	   comment .setContent(content);
	 	   comment .setCommentDate(postTime);


 	    // 使用 PostDao 插入數據
 	    try (Connection connection = ConnectionUtil.getConnection()) {
 	    	CommentDao commentDao = new CommentDaoImpl(connection);
 	    	commentDao.insert(comment);
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }

 	    // 重定向或轉發到其他頁面
 	   request.getRequestDispatcher("/GetPost").forward(request, response);
 	}
}