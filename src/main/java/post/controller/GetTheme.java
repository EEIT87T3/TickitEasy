package post.controller;


import java.io.IOException;
import java.sql.Connection;


import post.bean.ThemeBean;
import post.dao.ThemeDao;
import post.dao.impl.ThemeDaoImpl;
import util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;

@WebServlet("/GetTheme") //url-pattern//difference
public class GetTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection=null;
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		
		try {
			connection = ConnectionUtil.getConnection();
			int id = Integer.parseInt(request.getParameter("themeID"));

			ThemeDao themeDao = new ThemeDaoImpl(connection);
			ThemeBean foundtheme = themeDao.findById(id); 
			
			request.setAttribute("themes", foundtheme);//difference

			request.getRequestDispatcher("/post/GetTheme.jsp")//difference
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
