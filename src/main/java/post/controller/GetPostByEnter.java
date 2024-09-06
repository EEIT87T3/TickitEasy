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

@WebServlet("/GetPostByEnter") //url-pattern//difference
public class GetPostByEnter extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Connection connection=null;

 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String enter="";
		try {
			enter = request.getParameter("enter");
			connection = ConnectionUtil.getConnection();
			PostDao postDao = new PostDaoImpl(connection);
			if(enter =="") {
				List<PostBean> posts = postDao.findAll();
				request.setAttribute("post", posts);
				request.setAttribute("selectedThemeName", "所有主題");

				request.getRequestDispatcher("/post/PostList.jsp")
				.forward(request, response);
			}else {
				List<PostBean> posts = postDao.findByEnter(enter);
				request.setAttribute("post", posts);
//				String themeName = postDao.getThemeNameById(themeID);
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
