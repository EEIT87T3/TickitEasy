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


@WebServlet("/DeletePost") 
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
	
	public DeletePost() {
		
	}
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		
			int id = Integer.parseInt(request.getParameter("postID"));

			postService.delete(id); 
		
			request.getRequestDispatcher("GetAllPost")
			.forward(request, response);
	
 	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
