package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.bean.AdminBean;
import util.ConnectionUtil;

public class AdminDAO {
	//用email檢查管理員是否存在
	public AdminBean getAdminByEmail(String email) {
		String sql = "SELECT * FROM administrator WHERE email = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, email);
			try(ResultSet rs = pstmt.executeQuery();) {
				//若有資料則回傳管理員資料
				if(rs.next()) {
					return new AdminBean(
							rs.getInt("adminID"),
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("name")
							);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
