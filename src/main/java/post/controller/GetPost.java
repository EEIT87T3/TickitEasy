package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import post.bean.PostBean;
import post.bean.CommentBean;
import post.dao.PostDao;
import post.dao.CommentDao;
import post.dao.impl.PostDaoImpl;
import post.service.PostService;
import post.service.CommentService;
import post.dao.impl.CommentDaoImpl;
import util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;

@WebServlet("/GetPost") //url-pattern//difference
public class GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
	CommentService commentService = new CommentService();
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		
 		String postIDStr = request.getParameter("postID");
 		int postID = 0;
		if (postIDStr != null) {
			postID = Integer.parseInt(postIDStr);
		}
		
		//取得post
		PostBean foundPost = postService.findById(postID);
		request.setAttribute("post", foundPost);
		
		//取得comment 
//		CommentDao commentDao = new CommentDaoImpl(connection);
	    List<CommentBean> comments = commentService.findById(postID);
	    request.setAttribute("comment", comments);
			
	    request.getRequestDispatcher("/post/post.jsp")
		.forward(request, response);
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
