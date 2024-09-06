package product.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import product.bean.Products;
import util.ConnectionUtil;


public class ProductDao {

	// 新增商品
	public void addProducts(Products product) throws SQLException {
	    String sql = "INSERT INTO Products (category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionUtil.getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        preparedStatement.setString(1, product.getCategory());
	        preparedStatement.setString(2, product.getProductName());
	        preparedStatement.setString(3, product.getProductPic());
	        preparedStatement.setString(4, product.getProductDesc());
	        preparedStatement.setInt(5, product.getPrice());
	        preparedStatement.setInt(6, product.getStock());
	        preparedStatement.setString(7, product.getStatus());
	        preparedStatement.setInt(8, product.getProdTotalReviews());
	        preparedStatement.setInt(9, product.getProdTotalScore());
	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    product.setProductID(generatedKeys.getInt(1));  // 更新 productID
	                }
	            }
	        }
	    }
	}

	// 查詢所有商品
	public List<Products> findAll() throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from products";
		ArrayList<Products> productList = new ArrayList<>();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			 preparedStatement = connection.prepareStatement(sql);
			 resultSet = preparedStatement.executeQuery();
			 while (resultSet.next()) {
				Integer productId=resultSet.getInt("productID");
				String category = resultSet.getString("category");
				String productName = resultSet.getString("productName");
				String productPic = resultSet.getString("productPic");
				String productDesc = resultSet.getString("productDesc");
				int price = resultSet.getInt("price");
				int stock = resultSet.getInt("stock");
				String status = resultSet.getString("status");
				int prodTotalReviews = resultSet.getInt("prodTotalReviews");
				int prodTotalScore = resultSet.getInt("prodTotalScore");
				Products products = new Products(productId, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
				productList.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}
		return productList;
	}

	// 利用 productName 進行模糊查詢商品
    public List<Products> findProductsByName(String productName) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM products WHERE productName LIKE ?";
        List<Products> productsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + productName + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String category = resultSet.getString("category");
                String prodName = resultSet.getString("productName");
                String productPic = resultSet.getString("productPic");
                String productDesc = resultSet.getString("productDesc");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                String status = resultSet.getString("status");
                int prodTotalReviews = resultSet.getInt("prodTotalReviews");
                int prodTotalScore = resultSet.getInt("prodTotalScore");
                Products product = new Products(productID, category, prodName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
        }
        return productsList;
    }

 // 利用 Category 分類進行模糊查詢商品
    public List<Products> findProductsByCategory(String category) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM products WHERE category LIKE ?";
        List<Products> productsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + category + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String prodcategory = resultSet.getString("category");
                String prodName = resultSet.getString("productName");
                String productPic = resultSet.getString("productPic");
                String productDesc = resultSet.getString("productDesc");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                String status = resultSet.getString("status");
                int prodTotalReviews = resultSet.getInt("prodTotalReviews");
                int prodTotalScore = resultSet.getInt("prodTotalScore");
                Products product = new Products(productID, category, prodName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
        }
        return productsList;
    }

	// productID 查詢單筆商品
	public Products findProductById(int productID) throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
	    String sql = "SELECT * FROM products WHERE productID = ?";
	    Products product = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, productID);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            String category = resultSet.getString("category");
	            String productName = resultSet.getString("productName");
	            String productPic = resultSet.getString("productPic");
	            String productDesc = resultSet.getString("productDesc");
	            int price = resultSet.getInt("price");
	            int stock = resultSet.getInt("stock");
	            String status = resultSet.getString("status");
	            int prodTotalReviews = resultSet.getInt("prodTotalReviews");
				int prodTotalScore = resultSet.getInt("prodTotalScore");
				product = new Products(productID, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}
	    return product;
	}
	//刪除商品
	public Products deleteProductById(int productID) throws SQLException {
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    PreparedStatement deleteStmt = null;
	    ResultSet resultSet = null;
	    Products product = null;

	    try {
	        connection = ConnectionUtil.getConnection();
	        // 先查詢商品
	        String selectSql = "SELECT * FROM products WHERE productID = ?";
	        selectStmt = connection.prepareStatement(selectSql);
	        selectStmt.setInt(1, productID);
	        resultSet = selectStmt.executeQuery();
	        if (resultSet.next()) {
	            String category = resultSet.getString("category");
	            String productName = resultSet.getString("productName");
	            String productPic = resultSet.getString("productPic");
	            String productDesc = resultSet.getString("productDesc");
	            int price = resultSet.getInt("price");
	            int stock = resultSet.getInt("stock");
	            String status = resultSet.getString("status");
	            int prodTotalReviews = resultSet.getInt("prodTotalReviews");
	            int prodTotalScore = resultSet.getInt("prodTotalScore");
	            product = new Products(productID, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
	        }
	        // 删除商品
	        if (product != null) { // 确保商品存在再執行删除
	            String deleteSql = "DELETE FROM products WHERE productID = ?";
	            deleteStmt = connection.prepareStatement(deleteSql);
	            deleteStmt.setInt(1, productID);
	            deleteStmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionUtil.closeResource(connection, selectStmt, resultSet);
	        ConnectionUtil.closeResource(connection, deleteStmt);
	    }
	    return product;
	}

	// productID 更新商品
	public Products updateProductById(int productID, Products updatedProduct) throws SQLException {
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    PreparedStatement updateStmt = null;
	    ResultSet resultSet = null;
	    Products product = null;

	    try {
	        connection = ConnectionUtil.getConnection();

	        // 先查詢商品
	        String selectSql = "SELECT * FROM products WHERE productID = ?";
	        selectStmt = connection.prepareStatement(selectSql);
	        selectStmt.setInt(1, productID);
	        resultSet = selectStmt.executeQuery();

	        if (resultSet.next()) {
	            // 取得原始商品資料
	            String category = resultSet.getString("category");
	            String productName = resultSet.getString("productName");
	            String productPic = resultSet.getString("productPic");
	            String productDesc = resultSet.getString("productDesc");
	            int price = resultSet.getInt("price");
	            int stock = resultSet.getInt("stock");
	            String status = resultSet.getString("status");
	            int prodTotalReviews = resultSet.getInt("prodTotalReviews");
	            int prodTotalScore = resultSet.getInt("prodTotalScore");

	            // 用查詢到的資料初始化 product 對象
	            product = new Products(productID, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
	        }

	        // 更新商品
	        if (product != null) { // 確保商品存在再進行更新
	            String updateSql = "UPDATE products SET category = ?, productName = ?, productPic = ?, productDesc = ?, price = ?, stock = ?, status = ?, prodTotalReviews = ?, prodTotalScore = ? WHERE productID = ?";
	            updateStmt = connection.prepareStatement(updateSql);
	            updateStmt.setString(1, updatedProduct.getCategory());
	            updateStmt.setString(2, updatedProduct.getProductName());
	            updateStmt.setString(3, updatedProduct.getProductPic());
	            updateStmt.setString(4, updatedProduct.getProductDesc());
	            updateStmt.setInt(5, updatedProduct.getPrice());
	            updateStmt.setInt(6, updatedProduct.getStock());
	            updateStmt.setString(7, updatedProduct.getStatus());
	            updateStmt.setInt(8, updatedProduct.getProdTotalReviews());
	            updateStmt.setInt(9, updatedProduct.getProdTotalScore());
	            updateStmt.setInt(10, productID);
	            int rowsAffected = updateStmt.executeUpdate();

	            if (rowsAffected > 0) {
	                // 更新成功後返回更新後的商品資料
	                product = updatedProduct;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        ConnectionUtil.closeResource(connection, selectStmt, resultSet);
	        ConnectionUtil.closeResource(connection, updateStmt);
	    }
	    return product;  // 返回更新後的商品資料
	}
}