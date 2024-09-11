package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import post.bean.PostBean;
import post.bean.ThemeBean;
import post.dao.PostDao;
import post.dao.impl.PostDaoImpl;
import post.service.PostService;
import util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GetAllPost") 
public class GetAllPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
	
 	public GetAllPost() {
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 			
			//成功request forward response就不會出現
//			response.getWriter().append("Served at: ").append(request.getContextPath());
 			List<PostBean> posts = postService.findAll();
			request.setAttribute("post", posts);
 		
			request.getRequestDispatcher("/post/PostList.jsp")
			.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
