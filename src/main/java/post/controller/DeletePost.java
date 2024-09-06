package post.controller;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;
import post.dao.PostDao;
import post.dao.impl.PostDaoImpl;
import util.ConnectionUtil;

@WebServlet("/DeletePost") //url-pattern
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection=null;

 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			connection = ConnectionUtil.getConnection();
			int id = Integer.parseInt(request.getParameter("postID"));

			PostDao postDao = new PostDaoImpl(connection);
			postDao.delete(id);


			request.getRequestDispatcher("GetAllPost")//difference
			.forward(request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			ConnectionUtil.closeResource(connection);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
