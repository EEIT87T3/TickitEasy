package cwdfunding.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cwdfunding.bean.FundOrderBean;

@WebServlet ("/GetAllOrders")
public class FundOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FundOrderController() {
        super();
    }

	FileInputStream fileInputStream = null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			conn = ds.getConnection();
			//設定資料庫資訊			
			boolean status = !conn.isClosed();
			System.out.println("連線狀態:"+status);
			//取得連線物件
			String sql = "SELECT * FROM fundingOrder";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			List<FundOrderBean> orders = new ArrayList<>();
			FundOrderBean order = null;
			while(rs.next()) {
				order = new FundOrderBean();
				order.setOrderID(rs.getInt("orderID"));
				order.setMemberID(rs.getInt("memberID"));
				order.setProjectID(rs.getInt("projectID"));
				order.setPlanID(rs.getString("planID"));
				order.setPrice(rs.getInt("price"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setStatus(rs.getString("status"));
				order.setComment(rs.getString("comment"));
				order.setCommentDate(rs.getString("commentDate"));

				orders.add(order);
			}

			request.setAttribute("orders", orders);
			stmt.close();
			request.getRequestDispatcher("/cwdfunding/GetAllFundOrders.jsp").forward(request, response);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {
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
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
