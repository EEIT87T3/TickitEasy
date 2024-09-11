package order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import order.bean.TicketOrderDetailsBean;
import order.dao.base.TickitOrderDetailsDao;
import util.ConnectionUtil;

public class TicketOrderDetailsDaoImpl implements TickitOrderDetailsDao{

	public static int ticketOrderDetailsAdd(TicketOrderDetailsBean ticketOrderDetailsBean) { //新增
		Connection connection = ConnectionUtil.getConnection();
		String sql = "INSERT INTO ticketOrderDetails(ticketOrderID,ticketTypeID,ticketCollectionMethod,price,ticketUUID,ticketStatus,content,reviewTime,score)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, ticketOrderDetailsBean.getTicketOrderID());
			preparedStatement.setInt(2, ticketOrderDetailsBean.getTicketTypeID());
			preparedStatement.setString(3, ticketOrderDetailsBean.getTicketCollectionMethod());
			preparedStatement.setInt(4, ticketOrderDetailsBean.getPrice());
			preparedStatement.setString(5, ticketOrderDetailsBean.getTicketUUID());
			preparedStatement.setInt(6, ticketOrderDetailsBean.getTicketStatus());
			preparedStatement.setString(7, ticketOrderDetailsBean.getContent());
			preparedStatement.setDate(8, ticketOrderDetailsBean.getReviewTime());
			preparedStatement.setInt(9, ticketOrderDetailsBean.getScore());
			int add = preparedStatement.executeUpdate();
			if(add > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if(generatedKeys.next()) {
					int newID = generatedKeys.getInt(1);
					return newID;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement);
		}
		throw new RuntimeException("添加資料失敗");
	}

	public static int ticketOrderDetailsDelete(int ticketOrderDetailID) { //刪除
		Connection connection = ConnectionUtil.getConnection();
		String sql = "DELETE FROM ticketOrderDetails WHERE ticketOrderDetailID = ?";

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, ticketOrderDetailID);
			int delete = prepareStatement.executeUpdate();

			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}

		throw new RuntimeException("刪除資料失敗");
	}

	public static int ticketOrderDetailsUpdate(TicketOrderDetailsBean ticketOrderDetailsBean) { //修改
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE ticketOrderDetails SET ticketOrderID = ?,ticketTypeID = ?,ticketCollectionMethod = ?,price = ?,ticketUUID = ?,ticketStatus = ?,content = ?,reviewTime = ?,score = ? "
				+ "WHERE ticketOrderDetailID = ?";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ticketOrderDetailsBean.getTicketOrderID());
			preparedStatement.setInt(2, ticketOrderDetailsBean.getTicketTypeID());
			preparedStatement.setString(3, ticketOrderDetailsBean.getTicketCollectionMethod());
			preparedStatement.setInt(4, ticketOrderDetailsBean.getPrice());
			preparedStatement.setString(5, ticketOrderDetailsBean.getTicketUUID());
			preparedStatement.setInt(6, ticketOrderDetailsBean.getTicketStatus());
			preparedStatement.setString(7, ticketOrderDetailsBean.getContent());
			preparedStatement.setDate(8, ticketOrderDetailsBean.getReviewTime());
			preparedStatement.setInt(9, ticketOrderDetailsBean.getScore());
			preparedStatement.setInt(10, ticketOrderDetailsBean.getTicketOrderDetailID());
			preparedStatement.execute();

			return ticketOrderDetailsBean.getTicketOrderDetailID();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement);
		}
		throw new RuntimeException("修改資料失敗");
	}

	public static TicketOrderDetailsBean ticketOrderDetailsSelect(int ticketOrderDetailIDparameter) { //查詢單筆
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM ticketOrderDetails WHERE ticketOrderDetailID = ?";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TicketOrderDetailsBean ticketOrderDetailsBean = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ticketOrderDetailIDparameter);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int ticketOrderDetailID = resultSet.getInt(1);
				int ticketOrderID = resultSet.getInt(2);
				int ticketTypeID = resultSet.getInt(3);
				String ticketCollectionMethod = resultSet.getString(4);
				int price = resultSet.getInt(5);
				String ticketUUID = resultSet.getString(6);
				int ticketStatus = resultSet.getInt(7);
				String content = resultSet.getString(8);
				Date reviewTime = resultSet.getDate(9);
				int score = resultSet.getInt(10);
				ticketOrderDetailsBean = new TicketOrderDetailsBean(ticketOrderDetailID, ticketOrderID, ticketTypeID, ticketCollectionMethod, price, ticketUUID, ticketStatus, content, reviewTime, score);
			}
			return ticketOrderDetailsBean;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
		}

		throw new RuntimeException("無法取得資料");
	}

	public static List<TicketOrderDetailsBean> ticketOrderDetailsSelectAll() { //查詢多筆 未來修改成只顯示該會員的所有訂單顯示
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM ticketOrderDetails";

		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		List<TicketOrderDetailsBean> list = new ArrayList<>();

		try {
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int ticketOrderDetailID = resultSet.getInt(1);
				int ticketOrderID = resultSet.getInt(2);
				int ticketTypeID = resultSet.getInt(3);
				String ticketCollectionMethod = resultSet.getString(4);
				int price = resultSet.getInt(5);
				String ticketUUID = resultSet.getString(6);
				int ticketStatus = resultSet.getInt(7);
				String content = resultSet.getString(8);
				Date reviewTime = resultSet.getDate(9);
				int score = resultSet.getInt(10);
				list.add(new TicketOrderDetailsBean(ticketOrderDetailID, ticketOrderID, ticketTypeID, ticketCollectionMethod, price, ticketUUID, ticketStatus, content, reviewTime, score));
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
