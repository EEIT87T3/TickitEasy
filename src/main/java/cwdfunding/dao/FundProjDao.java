package cwdfunding.dao;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cwdfunding.bean.FundProjBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FundProjDao {

	
	FileInputStream fileInputStream = null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public List<FundProjBean> getAllFundProjs() {
		List<FundProjBean> projs = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			conn = ds.getConnection();
			// 設定資料庫資訊
			boolean status = !conn.isClosed();
			System.out.println("連線狀態:" + status);
			// 取得連線物件
			String sql = "SELECT * FROM fundingProj";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			FundProjBean proj = null;
			while (rs.next()) {
				proj = new FundProjBean();
				proj.setProjectID(rs.getInt("projectID"));
				proj.setTitle(rs.getString("title"));
				proj.setDescription(rs.getString("description"));
				proj.setImage(rs.getString("image"));
				proj.setStartDate(rs.getString("startDate"));
				proj.setEndDate(rs.getString("endDate"));
				proj.setTargetAmount(String.valueOf(rs.getInt("targetAmount")));
				proj.setCurrentAmount(String.valueOf(rs.getInt("currentAmount")));
				proj.setThreshold(String.valueOf(rs.getDouble("threshold")));
				proj.setPostponeDate(rs.getString("postponeDate"));
				proj.setCategory(rs.getString("category"));
				projs.add(proj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projs;
	}

	public void insertFundProj(FundProjBean proj) throws IOException, ServletException {
		String title = proj.getTitle();
		String description = proj.getDescription();
		String image = proj.getImage();
		String startDate = proj.getStartDate();
		String endDate = proj.getEndDate();
		String targetAmount = proj.getTargetAmount();
		String currentAmount = proj.getCurrentAmount();
		String threshold = proj.getThreshold();
		String postponeDate = proj.getPostponeDate();
		String category = proj.getCategory();
		
		System.out.println("title:"+title);
		System.out.println("description:"+description);

		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			conn = ds.getConnection();

			// 設定資料庫資訊
			boolean status = !conn.isClosed();
			System.out.println("連線狀態:" + status);
			// 取得連線物件
			String sql = "INSERT INTO fundingProj VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertStmt = conn.prepareStatement(sql);
			insertStmt.setString(1, title);
			insertStmt.setString(2, description);
			insertStmt.setString(3, image);
			insertStmt.setString(4, startDate);
			insertStmt.setString(5, endDate);
			insertStmt.setString(6, targetAmount);
			insertStmt.setString(7, currentAmount);
			insertStmt.setString(8, threshold);
			insertStmt.setString(9, postponeDate);
			insertStmt.setString(10, category);

			insertStmt.execute();
			insertStmt.close();
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateFundProj(FundProjBean proj) throws IOException, ServletException {
		int projectID = proj.getProjectID();
		String title = proj.getTitle();
		String description = proj.getDescription();
		String image = proj.getImage();
		String startDate = proj.getStartDate();
		String endDate = proj.getEndDate();
		String targetAmount = proj.getTargetAmount();
		String currentAmount = proj.getCurrentAmount();
		String threshold = proj.getThreshold();
		String postponeDate = proj.getPostponeDate();
		String category = proj.getCategory();
	    	    

	    try {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
	        conn = ds.getConnection();

	        String sql = "UPDATE fundingProj SET title=?, description=?, image=?, startDate=?, endDate=?, targetAmount=?, currentAmount=?, threshold=?, postponeDate=?, category=? WHERE projectID=?";
	        PreparedStatement udtStmt = conn.prepareStatement(sql);

	        udtStmt.setString(1, title);
	        udtStmt.setString(2, description);
	        udtStmt.setString(3, image);
	        udtStmt.setTimestamp(4, Timestamp.valueOf(startDate));
	        udtStmt.setTimestamp(5, Timestamp.valueOf(endDate));
	        udtStmt.setInt(6, Integer.parseInt(targetAmount));
	        udtStmt.setInt(7, Integer.parseInt(currentAmount));
	        udtStmt.setDouble(8, Double.valueOf(threshold));
	        udtStmt.setTimestamp(9, Timestamp.valueOf(postponeDate));
	        udtStmt.setString(10, category);
	        udtStmt.setInt(11, projectID);

	        udtStmt.execute();
	        udtStmt.close();


	    }  catch (SQLException e) {
	        e.printStackTrace();
	    } catch (NamingException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


	public void deleteFundProj(int projectID) throws IOException, ServletException {

		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			conn = ds.getConnection();

			System.out.println("project ID:"+projectID);
			// 設定資料庫資訊
			boolean status = !conn.isClosed();
			System.out.println("連線狀態:" + status);
			// 取得連線物件
			String sql = "DELETE FROM fundingProj WHERE projectID=?";
			PreparedStatement delStmt = conn.prepareStatement(sql);
			delStmt.setInt(1, projectID);
			
			delStmt.execute();
			delStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}
