package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnectionUtil;


//@WebServlet("/BaseDao") //url-pattern//difference

public class BaseDao  {
	public <T> T baseQueryObject(Class<T> clazz,String sql,Object ... args){
		T t=null;
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		int rows = 0 ;
		try {
			stmt = connection.prepareStatement(sql);
			for(int i = 0; i < args.length;i++) {
				stmt.setObject(i+1,args[i]);
			}

			rs = stmt.executeQuery();
			if (rs.next()) {
				t= (T) rs.getObject(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			ConnectionUtil.closeResource(connection, stmt, rs);
		}
		return t;
	}
}




