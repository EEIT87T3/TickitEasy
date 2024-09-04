package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {
	
	/*
	 * method 名稱：getConnection
	 * 用途：取得 Connection 物件
	 * @param：（無）
	 * @return：Connection（物件）
	 * 備註：static
	*/
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			connection = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			System.out.println("取得 connection 失敗。");
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * method 名稱：closeResource
	 * 用途：關閉 Connection 物件、Statement 物件與 ResultSet 物件
	 * @param：Connection、[Statement]、[ResultSet]
	 * @return：（無）
	 * 備註： static，overloading
	*/
	public static void closeResource(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeResource(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeResource(connection);
	}
	public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeResource(connection, statement);
	}

}
