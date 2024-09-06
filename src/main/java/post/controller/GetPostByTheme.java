package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;
import post.bean.PostBean;
import post.dao.PostDao;
import post.dao.impl.PostDaoImpl;
import util.ConnectionUtil;

@WebServlet("/GetPostByTheme") //url-pattern//difference
public class GetPostByTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Connection connection=null;

 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		int themeID=0;
		try {
			themeID = Integer.parseInt(request.getParameter("themeID"));
			connection = ConnectionUtil.getConnection();
			PostDao postDao = new PostDaoImpl(connection);
			if(themeID ==0) {
				List<PostBean> posts = postDao.findAll();
				request.setAttribute("post", posts);
				request.setAttribute("selectedThemeName", "所有主題");

				request.getRequestDispatcher("/post/PostList.jsp")
				.forward(request, response);
			}else {
				List<PostBean> posts = postDao.findByTheme(themeID);
				request.setAttribute("post", posts);
//				String themeName =  posts .getString("themeName");
//				request.setAttribute("selectedThemeName", themeName);

				request.getRequestDispatcher("/post/PostList.jsp")
				.forward(request, response);
			}

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
