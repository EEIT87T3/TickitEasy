package product.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import product.bean.Products;
import util.HibernateUtil;


public class ProductDao {
	
	private SessionFactory sessionFactory;

	public ProductDao() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
	// 新增商品
	public void addProducts(Products product) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		session.persist(product);
	}

	// 查詢所有商品
	public List<Products> findAll() throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Products ",Products.class).list();
	}

	// 利用 productName 進行模糊查詢商品
    public List<Products> findProductsByName(String productName) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		
		 return session.createQuery("from Products WHERE productName LIKE :name", Products.class)
                 .setParameter("name", "%" + productName + "%")
                 .list();
    }

 // 利用 Category 分類進行模糊查詢商品
    public List<Products> findProductsByCategory(String category) throws SQLException {
    	Session session = sessionFactory.getCurrentSession();
		
		 return session.createQuery("from Products WHERE category LIKE :name", Products.class)
                .setParameter("name", "%" + category + "%")
                .list();
    }

	// productID 查詢單筆商品
	public Products findProductById(int productID) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Products.class, productID);
	}
	
	//刪除商品
	public Products deleteProductById(int productID) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		Products product = session.get(Products.class, productID);
		
		if (product != null) {
	        session.remove(product);
	    }
	    return product;
	}

	// productID 更新商品
	public Products updateProductById(int productID, Products updatedProduct) throws SQLException {
		Session session = sessionFactory.getCurrentSession();
	    Products product = session.get(Products.class, productID);

	        // 如果商品存在，執行更新操作
	        if (product != null) {
	            // 更新原有商品的屬性
	            product.setCategory(updatedProduct.getCategory());
	            product.setProductName(updatedProduct.getProductName());
	            product.setProductPic(updatedProduct.getProductPic());
	            product.setProductDesc(updatedProduct.getProductDesc());
	            product.setPrice(updatedProduct.getPrice());
	            product.setStock(updatedProduct.getStock());
	            product.setStatus(updatedProduct.getStatus());
	            product.setProdTotalReviews(updatedProduct.getProdTotalReviews());
	            product.setProdTotalScore(updatedProduct.getProdTotalScore());

	            // 使用 Hibernate 的 session 更新商品
	            session.merge(product);
	        }
	    return product;  
	}
}