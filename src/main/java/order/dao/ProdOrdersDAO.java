package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.bcel.generic.INEG;

import order.bean.ProdOrdersBean;
import util.ConnectionUtil;

//周邊商品的CRUD

public class ProdOrdersDAO {
	
	public static int prodOrderAdd(ProdOrdersBean prodOrderBean) { //新增
		Connection connection = ConnectionUtil.getConnection();
		String sql = "INSERT INTO prodOrders(memberID,orderDate,payments,paymentInfo,status,totalAmount,shippingStatus,shippingID,recipientName,address,phone)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, prodOrderBean.getMemberID());
//			prepareStatement.setDate(2, prodOrderBean.getOrderDate());
			prepareStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			prepareStatement.setString(3, prodOrderBean.getPayments());
			prepareStatement.setString(4, prodOrderBean.getPaymenInfo());
//			prepareStatement.setString(4, String.valueOf( (int)(Math.random() * 1000_0000_0000_0000L)) );
			prepareStatement.setString(5, prodOrderBean.getStatus());
			prepareStatement.setInt(6, prodOrderBean.getTotalAmount());
			prepareStatement.setString(7, prodOrderBean.getShippingStatus());
			prepareStatement.setInt(8, prodOrderBean.getShippingID());
			prepareStatement.setString(9, prodOrderBean.getRecipientName());
			prepareStatement.setString(10, prodOrderBean.getAddress());
			prepareStatement.setString(11, prodOrderBean.getPhone());
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
	
	public static int prodOrderDelete(int prodOrderID) { //刪除
		Connection connection = ConnectionUtil.getConnection();
		String sql = "DELETE FROM prodOrders WHERE prodOrderID = ?";
		
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, prodOrderID);
			int delete = prepareStatement.executeUpdate();
			
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}
		
		throw new RuntimeException("刪除資料失敗");
	}
	
	public static int prodOrderUpdate(ProdOrdersBean prodOrderBean) { //修改 之後修改成返回ProductOrderBean 將改完後頁面顯示在JSP
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE prodOrders SET memberID = ?,orderDate = ?,payments = ?,paymentInfo = ?,status = ?,totalAmount = ?,shippingStatus = ?,shippingID = ?,recipientName = ?,address = ?,phone = ?"
				+ "WHERE prodOrderID = ?";
		
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, prodOrderBean.getMemberID());
			prepareStatement.setDate(2, prodOrderBean.getOrderDate());
			prepareStatement.setString(3, prodOrderBean.getPayments());
			prepareStatement.setString(4, prodOrderBean.getPaymenInfo());
			prepareStatement.setString(5, prodOrderBean.getStatus());
			prepareStatement.setInt(6, prodOrderBean.getTotalAmount());
			prepareStatement.setString(7, prodOrderBean.getShippingStatus());
			prepareStatement.setInt(8, prodOrderBean.getShippingID());
			prepareStatement.setString(9, prodOrderBean.getRecipientName());
			prepareStatement.setString(10, prodOrderBean.getAddress());
			prepareStatement.setString(11, prodOrderBean.getPhone());
			prepareStatement.setInt(12,prodOrderBean.getProdOrderID());
			prepareStatement.executeUpdate();
			
			return prodOrderBean.getProdOrderID();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement);
		}
		throw new RuntimeException("修改資料失敗");
	}
	
	public static ProdOrdersBean prodOrderSelect(int prodOrderIDparameter) { //查詢單筆 
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM prodOrders WHERE prodOrderID = ?"; 
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		ProdOrdersBean prodOrderBean = null;
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, prodOrderIDparameter); 
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				int prodOrderID = resultSet.getInt(1);
				int memberID = resultSet.getInt(2);
				java.sql.Date orderDate = resultSet.getDate(3);
				String payments = resultSet.getString(4);
				String paymentsInfo = resultSet.getString(5);
				String status = resultSet.getString(6);
				int totalAmount = resultSet.getInt(7);
				String shippingStatus = resultSet.getString(8);
				int shippingID = resultSet.getInt(9);
				String recipientName = resultSet.getString(10);
				String address = resultSet.getString(11);
				String phone = resultSet.getString(12);
				prodOrderBean = new ProdOrdersBean(prodOrderID,memberID,orderDate,payments,paymentsInfo,status,totalAmount,shippingStatus,shippingID,recipientName,address,phone);
			}
			return prodOrderBean;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,prepareStatement,resultSet);
		}
		
		throw new RuntimeException("無法取得資料");
	}
	
	public static List<ProdOrdersBean> prodOrderSelectAll(int memberIDParameter) { //查詢多筆 未來修改成只顯示該會員的所有訂單顯示
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM prodOrders WHERE memberID = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ProdOrdersBean> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, memberIDParameter);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int prodOrderID = resultSet.getInt(1);
				int memberID = resultSet.getInt(2);
				java.sql.Date orderDate = resultSet.getDate(3);
				String payments = resultSet.getString(4);
				String paymentsInfo = resultSet.getString(5);
				String status = resultSet.getString(6);
				int totalAmount = resultSet.getInt(7);
				String shippingStatus = resultSet.getString(8);
				int shippingID = resultSet.getInt(9);
				String recipientName = resultSet.getString(10);
				String address = resultSet.getString(11);
				String phone = resultSet.getString(12);
				list.add(new ProdOrdersBean(prodOrderID,memberID,orderDate,payments,paymentsInfo,status,totalAmount,shippingStatus,shippingID,recipientName,address,phone));
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
		}
			throw new RuntimeException("無法取得資料");
		
	}
}
