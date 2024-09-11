package post.controller;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

//import org.apache.jasper.tagplugins.jstl.core.If;
import org.hibernate.SessionFactory;

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


@WebServlet("/GetPostByTheme") 
public class GetPostByTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
       
	Connection connection=null;
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		int themeID=0;
		
			themeID = Integer.parseInt(request.getParameter("themeID")); 
		
			
			if(themeID ==0) {
				List<PostBean> posts = postService.findAll(); 
				request.setAttribute("post", posts);
				request.setAttribute("selectedThemeName", "所有主題");
				
				request.getRequestDispatcher("/post/PostList.jsp")
				.forward(request, response);
			}else {
				List<PostBean> posts = postService.findByTheme(themeID); 
				request.setAttribute("post", posts);
//				String themeName =  posts .getString("themeName");
//				request.setAttribute("selectedThemeName", themeName);
				
				request.getRequestDispatcher("/post/PostList.jsp")
				.forward(request, response);
			}
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
