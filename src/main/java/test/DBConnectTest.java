package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DBConnectTest")
public class DBConnectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		response.setContentType("text/html; charset=UTF-8");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 取得連線
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			connection = dataSource.getConnection();
			
			// SQL 指令
			String sql = "SELECT DB_NAME() AS currentDatabaseName";
			
			// 執行 SQL 指令、取得 ResultSet
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			String dbName = null;
			if (resultSet.next()) {
				dbName = resultSet.getString("currentDatabaseName");
			}
			
			response.getWriter().append("連線成功，連接至的資料庫名稱是：").append(dbName);
			
		} catch (SQLException | NamingException e) {
			response.getWriter().append("連接失敗。");
			e.printStackTrace();
		}  finally {
			try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
