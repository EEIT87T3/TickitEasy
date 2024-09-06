package post.controller;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.jstl.sql.Result;
import post.bean.ThemeBean;
import post.dao.ThemeDao;
import post.dao.impl.ThemeDaoImpl;
import util.ConnectionUtil;

@WebServlet("/InsertTheme") //url-pattern//difference
public class InsertTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection=null;

 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String themeName= request.getParameter("themeName");

			ThemeBean theme = new ThemeBean();
			theme.setThemeName(themeName);


//			request.setAttribute("themes", foundtheme);//difference

		try	(Connection connection = ConnectionUtil.getConnection()) {
				ThemeDao themeDao = new ThemeDaoImpl(connection);
				themeDao.insert(theme);
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {

		}
		request.getRequestDispatcher("/GetAllTheme")//difference
		.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
