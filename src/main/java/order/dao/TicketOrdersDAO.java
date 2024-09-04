package order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import order.bean.TicketOrdersBean;
import util.ConnectionUtil;

//票券的CRUD
public class TicketOrdersDAO {

	public static int ticketOrderAdd(TicketOrdersBean ticketOrderBean) { //新增
		Connection connection = ConnectionUtil.getConnection();
		String sql = "INSERT INTO ticketOrders(memberID,orderDate,payments,status,totalAmount)"
				+ "VALUES(?,?,?,?,?)";

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, ticketOrderBean.getMemberID());
			prepareStatement.setDate(2, ticketOrderBean.getOrderDate());
			prepareStatement.setString(3, ticketOrderBean.getPayments());
			prepareStatement.setString(4, ticketOrderBean.getStatus());
			prepareStatement.setInt(5, ticketOrderBean.getTotalAmount());
			int add = prepareStatement.executeUpdate();
			if(add > 0) {
				ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
				if(generatedKeys.next()) {
					int newID = generatedKeys.getInt(1);

					return newID;
				}
			}



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}
		throw new RuntimeException("添加資料失敗");
	}

	public static int ticketOrderDelete(int ticketOrderID) { //刪除
		Connection connection = ConnectionUtil.getConnection();
		String sql = "DELETE FROM ticketOrders WHERE ticketOrderID = ?";

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, ticketOrderID);
			int delete = prepareStatement.executeUpdate();

			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}

		throw new RuntimeException("刪除資料失敗");
	}

	public static int ticketOrderUpdate(TicketOrdersBean ticketOrderBean) { //修改
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE ticketOrders SET memberID = ?,orderDate = ?,payments = ?,status = ?,totalAmount = ? "
				+ "WHERE ticketOrderID = ?";

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, ticketOrderBean.getMemberID());
			prepareStatement.setDate(2, ticketOrderBean.getOrderDate());
			prepareStatement.setString(3, ticketOrderBean.getPayments());
			prepareStatement.setString(4, ticketOrderBean.getStatus());
			prepareStatement.setInt(5, ticketOrderBean.getTotalAmount());
			prepareStatement.setInt(6, ticketOrderBean.getTickedOrderID());
			prepareStatement.executeUpdate();


			return ticketOrderBean.getTickedOrderID();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}
		throw new RuntimeException("修改資料失敗");
	}

	public static TicketOrdersBean ticketOrderSelect(int ticketOrderIDparameter) {//查詢單筆
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM ticketOrders WHERE ticketOrderID = ?";

		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		TicketOrdersBean ticketOrdersBean = null;

		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, ticketOrderIDparameter);
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				int ticketOrderID = resultSet.getInt(1);
				int memberID = resultSet.getInt(2);
				Date orderDate = resultSet.getDate(3);
				String payments = resultSet.getString(4);
				String status = resultSet.getString(5);
				int totalAmount = resultSet.getInt(6);
				ticketOrdersBean = new TicketOrdersBean(ticketOrderID,memberID,orderDate,payments,status,totalAmount);
			}
			return ticketOrdersBean;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement,resultSet);
		}

		throw new RuntimeException("取得單筆資料失敗");
	}

	public static List<TicketOrdersBean> ticketOrderSelectAll() { //查詢多筆 未來修改成只顯示該會員的所有訂單顯示
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM ticketOrders";

		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		List<TicketOrdersBean> list = new ArrayList<>();

		try {
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int ticketOrderID = resultSet.getInt(1);
				int memberID = resultSet.getInt(2);
				Date orderDate = resultSet.getDate(3);
				String payments = resultSet.getString(4);
				String status = resultSet.getString(5);
				int totalAmount = resultSet.getInt(6);
				list.add(new TicketOrdersBean(ticketOrderID,memberID,orderDate,payments,status,totalAmount));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement,resultSet);
		}

		throw new RuntimeException("取得多筆資料失敗");
	}
}
