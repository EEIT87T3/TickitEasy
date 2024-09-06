package cwdfunding.controller;

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

import cwdfunding.bean.FundPlanBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/GetAllFundPlans")
public class FundPlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public FundPlanController() {
        super();
    }

	FileInputStream fileInputStream = null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/EEIT87-T3");
			conn = ds.getConnection();
			//設定資料庫資訊
			boolean status = !conn.isClosed();
			System.out.println("連線狀態:"+status);
			//取得連線物件
			String sql = "SELECT * FROM fundingPlan";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			List<FundPlanBean> plans = new ArrayList<>();
			FundPlanBean plan = null;
			while(rs.next()) {
				plan = new FundPlanBean();
				plan.setPlanID(rs.getInt("planID"));
				plan.setProjectID(rs.getInt("projectID"));
				plan.setUnitPrice(rs.getInt("unitPrice"));
				plan.setPlanContent(rs.getString("planContent"));
				plan.setTotalAmount(rs.getInt("totalAmount"));
				plan.setBuyAmount(rs.getInt("buyAmount"));
				plans.add(plan);
			}

			request.setAttribute("plans", plans);
			stmt.close();
			request.getRequestDispatcher("/cwdfunding/GetAllFundPlans.jsp").forward(request, response);

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


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
