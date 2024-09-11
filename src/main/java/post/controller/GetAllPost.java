package post.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;
import post.bean.PostBean;
import post.dao.PostDao;
import post.dao.impl.PostDaoImpl;
import post.model.Post;
import util.ConnectionUtil;
import util.HibernateUtil;

@WebServlet("/GetAllPost") // URL pattern
public class GetAllPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 使用 Hibernate 查詢所有 Post
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			// 使用 PostDaoImpl 來查詢所有的 Post
			PostDao postDao = new PostDaoImpl(session);
			List<Post> posts = postDao.findAll();

			// 將 Post 列表放入 request 中，轉發到 JSP 或其他地方顯示
			request.setAttribute("posts", posts);
			request.getRequestDispatcher("/post/PostList.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving posts.");
		} finally {
			session.close(); // 確保 session 關閉
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // 將 POST 請求轉發給 doGet
	}
}
