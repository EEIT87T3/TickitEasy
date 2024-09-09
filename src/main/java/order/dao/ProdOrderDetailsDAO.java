package order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import order.bean.ProdOrderDetailsBean;
import util.ConnectionUtil;

public class ProdOrderDetailsDAO {

	public static int prodOrderDetailsAdd(ProdOrderDetailsBean prodOrderDetailsBean) { //新增
		Connection connection = ConnectionUtil.getConnection();
		String sql = "INSERT INTO prodOrderDetails(prodOrderID ,productID ,price ,quantity ,content,reviewTime ,score )"
				+ "VALUES(?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, prodOrderDetailsBean.getProdOrderID());
			preparedStatement.setInt(2, prodOrderDetailsBean.getProductID());
			preparedStatement.setInt(3, prodOrderDetailsBean.getPrice());
			preparedStatement.setInt(4, prodOrderDetailsBean.getQuantity());
			preparedStatement.setString(5, prodOrderDetailsBean.getContent());
			preparedStatement.setDate(6, prodOrderDetailsBean.getReviewTime());
			preparedStatement.setInt(7, prodOrderDetailsBean.getScore());
			int add = preparedStatement.executeUpdate();
			if(add > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if(generatedKeys.next()) {
					int newID = generatedKeys.getInt(1);
					return newID;
				}
			}
			//hi

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement);
		}
			throw new RuntimeException("無法取得資料");
	}

	public static int prodOrderDetailsDelete(int prodOrderDetailID) { //刪除
		Connection connection = ConnectionUtil.getConnection();
		String sql = "DELETE FROM prodOrderDetails WHERE prodOrderDetailID = ?";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodOrderDetailID);
			int delete = preparedStatement.executeUpdate();

			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement);
		}
			throw new RuntimeException("無法取得資料");
	}

	public static int prodOrderDetailsUpdate(ProdOrderDetailsBean prodOrderDetailsBean) { //修改
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE prodOrderDetails SET prodOrderID = ?,productID = ?,price = ?,quantity = ?,content = ?,reviewTime = ?,score = ?"
				+ "WHERE prodOrderDetailID = ?";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodOrderDetailsBean.getProdOrderID());
			preparedStatement.setInt(2, prodOrderDetailsBean.getProductID());
			preparedStatement.setInt(3, prodOrderDetailsBean.getPrice());
			preparedStatement.setInt(4, prodOrderDetailsBean.getQuantity());
			preparedStatement.setString(5, prodOrderDetailsBean.getContent());
			preparedStatement.setDate(6, prodOrderDetailsBean.getReviewTime());
			preparedStatement.setInt(7, prodOrderDetailsBean.getScore());
			preparedStatement.setInt(8, prodOrderDetailsBean.getProdOrderDetailID());
			preparedStatement.executeUpdate();

			return prodOrderDetailsBean.getProdOrderDetailID();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement);
		}
		throw new RuntimeException("修改資料失敗");
	}

	public static ProdOrderDetailsBean prodOrderDetailsSelect(int prodOrderIDParameter){ //查詢單筆 prodOrderDetailID搜尋
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM prodOrderDetails WHERE prodOrderDetailID = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodOrderIDParameter);
			resultSet = preparedStatement.executeQuery();
			ProdOrderDetailsBean prodOrderDetailsBean = null;
			if(resultSet.next()) {
				int prodOrderDetailsID = resultSet.getInt(1);
				int prodOrderID = resultSet.getInt(2);
				int productID = resultSet.getInt(3);
				int price = resultSet.getInt(4);
				int quantity = resultSet.getInt(5);
				String content = resultSet.getString(6);
				Date reviewTime = resultSet.getDate(7);
				int score = resultSet.getInt(8);
				prodOrderDetailsBean =  new ProdOrderDetailsBean(prodOrderDetailsID,prodOrderID,productID,price,quantity,content,reviewTime,score);
			}

			return prodOrderDetailsBean;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
		}
			throw new RuntimeException("無法取得資料");

	}

	public static List<ProdOrderDetailsBean> prodOrderDetailsSelectAll(int prodOrderIDOrder) { //查詢多筆
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM prodOrderDetails WHERE prodOrderID = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ProdOrderDetailsBean> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodOrderIDOrder);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				int prodOrderDetailsID = resultSet.getInt(1);
				int prodOrderID = resultSet.getInt(2);
				int productID = resultSet.getInt(3);
				int price = resultSet.getInt(4);
				int quantity = resultSet.getInt(5);
				String content = resultSet.getString(6);
				Date reviewTime = resultSet.getDate(7);
				int score = resultSet.getInt(8);
				list.add(new ProdOrderDetailsBean(prodOrderDetailsID,prodOrderID,productID,price,quantity,content,reviewTime,score));
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
		}
			throw new RuntimeException("無法取得資料");

	}

	public static List<ProdOrderDetailsBean> prodOrderDetailsSelectAllforCart(int prodOrderIDCart) { //查詢多筆
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM prodOrderDetails WHERE prodOrderID = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ProdOrderDetailsBean> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodOrderIDCart);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				int prodOrderDetailsID = resultSet.getInt(1);
				int prodOrderID = resultSet.getInt(2);
				int productID = resultSet.getInt(3);
				int price = resultSet.getInt(4);
				int quantity = resultSet.getInt(5);
				String content = resultSet.getString(6);
				Date reviewTime = resultSet.getDate(7);
				int score = resultSet.getInt(8);
				list.add(new ProdOrderDetailsBean(prodOrderDetailsID,prodOrderID,productID,price,quantity,content,reviewTime,score));
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
