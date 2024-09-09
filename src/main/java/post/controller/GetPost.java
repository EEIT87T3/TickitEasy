//package post.controller;
//
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
////import jakarta.servlet.jsp.jstl.sql.Result;
//import post.bean.CommentBean;
//import post.bean.PostBean;
//import post.dao.CommentDao;
//import post.dao.PostDao;
//import post.dao.impl.CommentDaoImpl;
//import post.dao.impl.PostDaoImpl;
//import util.ConnectionUtil;
//
//@WebServlet("/GetPost") //url-pattern//difference
//public class GetPost extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//	Connection connection=null;
//
// 	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
// 		String postIDStr = request.getParameter("postID");
// 		int postID = 0;
//		try {
//			connection = ConnectionUtil.getConnection();
//			 if (postIDStr != null) {
//			        postID = Integer.parseInt(postIDStr);
//			    }
//			PostDao postDao = new PostDaoImpl(connection);
//			PostBean foundPost = postDao.findById(postID);
//			request.setAttribute("post", foundPost);
//
//			  CommentDao commentDao = new CommentDaoImpl(connection);
//	          List<CommentBean> comments = commentDao.findById(postID);
//	          request.setAttribute("comment", comments);
//
//			request.getRequestDispatcher("/post/post.jsp")
//			.forward(request, response);
//
//		} catch (Exception e) {
//			 e.printStackTrace();
//
//		}finally {
//			ConnectionUtil.closeResource(connection);
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//}
