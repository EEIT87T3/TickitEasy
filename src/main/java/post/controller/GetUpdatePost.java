package post.controller;


import java.io.IOException;
import java.sql.Connection;

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


@WebServlet("/GetUpdatePost") 
public class GetUpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();

	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String postIDStr = request.getParameter("postID");
 		int postID = 0;
		
		if (postIDStr != null) {
			postID = Integer.parseInt(postIDStr);
		}
		
		PostBean foundPost = postService.findById(postID); 
		request.setAttribute("post", foundPost);
			
		request.getRequestDispatcher("/post/UpdatePost.jsp")
		.forward(request, response);
			
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
