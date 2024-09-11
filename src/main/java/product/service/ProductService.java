package product.service;

import java.util.List;
import org.hibernate.Session;
import product.bean.Products;
import product.dao.ProductDao;

public class ProductService {
	
	private ProductDao productDao;
	
	public ProductService() {
		this.productDao = new ProductDao();
	}
	
	public ProductService(Session session) {
		this.productDao = new ProductDao(session);
	}
	
	// 新增商品
	public Products addProducts(Products product) {
		return productDao.addProducts(product);
	}

	// 查詢所有商品
	public List<Products> findAll(){
		return productDao.findAll();
	}

	// 利用 productName 進行模糊查詢商品
    public List<Products> findProductsByName(String productName){
		 return productDao.findProductsByName(productName);
    }

	// 利用 Category 分類進行模糊查詢商品
    public List<Products> findProductsByCategory(String category){
		 return productDao.findProductsByCategory(category);
    }

	// productID 查詢單筆商品
	public Products findProductById(int productID){
		return productDao.findProductById(productID);
	}
	
	//刪除商品
	public Products deleteProductById(int productID){
	    return productDao.deleteProductById(productID);
	}

	// productID 更新商品
	public Products updateProductById(int productID, Products updatedProduct){
	    return productDao.updateProductById(productID, updatedProduct);  
	}	
	
	
}
