package product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.bean.Products;
import product.dao.ProductDao;
import util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();
    public DeleteProduct() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIDStr = request.getParameter("productID");
        int productID = Integer.parseInt(productIDStr);

        // 判斷是否來自確認刪除按鈕
        String confirmDelete = request.getParameter("confirmDelete");
        //
        if (confirmDelete == null) {
            // 第一次請求，顯示商品詳細內容
            try {
                Products product = productDao.findProductById(productID);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/product/DeleteProduct.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //確認刪除操作
            try {
                productDao.deleteProductById(productID);
                // 刪除操作完成後重定向到商品列表頁面
                response.sendRedirect(request.getContextPath() + "/product/GetAllProducts.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
