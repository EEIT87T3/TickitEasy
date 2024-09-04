package post.controller;


import java.io.IOException;
import java.sql.Connection;

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

@WebServlet("/GetUpdatePost") //url-pattern//difference
public class GetUpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	Connection connection=null;
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String postIDStr = request.getParameter("postID");
 		int postID = 0;
		try {
			connection = ConnectionUtil.getConnection();
			 if (postIDStr != null) {
			        postID = Integer.parseInt(postIDStr);
			    }
			PostDao postDao = new PostDaoImpl(connection);
			PostBean foundPost = postDao.findById(postID); 
			request.setAttribute("post", foundPost);
			
			request.getRequestDispatcher("/post/UpdatePost.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			 e.printStackTrace();
			 
		}finally {
			ConnectionUtil.closeResource(connection);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
