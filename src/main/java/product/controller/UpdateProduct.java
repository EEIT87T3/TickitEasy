package product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import product.bean.Products;
import product.dao.ProductDao;

@MultipartConfig
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductDao productDao = new ProductDao();

    public UpdateProduct() {
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
        String confirmUpdate = request.getParameter("confirmUpdate");

        if (confirmUpdate == null) {
            // 第一次請求，顯示商品詳細內容
            try {
                Products product = productDao.findProductById(productID);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/product/UpdateProduct.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 確認編輯操作
            try {
                String category = request.getParameter("category");
                String productName = request.getParameter("productName");
                String productDesc = request.getParameter("productDesc");
                int price = Integer.parseInt(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String status = request.getParameter("status");
                int prodTotalReviews = Integer.parseInt(request.getParameter("prodTotalReviews"));
                int prodTotalScore = Integer.parseInt(request.getParameter("prodTotalScore"));

                // 獲取當前商品的舊圖片路徑
                Products currentProduct = productDao.findProductById(productID);
                String productPic = currentProduct.getProductPic(); // 保持原有路徑

                // 處理上傳的圖片
                Part filePart = request.getPart("productPicFile"); // 用於文件上傳的 Part
                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uploadPath = getServletContext().getRealPath("/") + "product/images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    filePart.write(filePath);
                    productPic = "product/images/" + fileName; // 更新圖片路徑為相對路徑
                }

                Products updatedProduct = new Products(productID, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
                productDao.updateProductById(productID, updatedProduct);

                response.sendRedirect(request.getContextPath() + "/product/GetAllProducts.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String productIDStr = request.getParameter("productID");
//        int productID = Integer.parseInt(productIDStr);
//        String confirmUpdate = request.getParameter("confirmUpdate");
//
//        if (confirmUpdate == null) {
//            try {
//                Products product = productDao.findProductById(productID);
//                request.setAttribute("product", product);
//                request.getRequestDispatcher("/product/UpdateProduct.jsp").forward(request, response);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                String category = request.getParameter("category");
//                String productName = request.getParameter("productName");
//                String productDesc = request.getParameter("productDesc");
//                int price = Integer.parseInt(request.getParameter("price"));
//                int stock = Integer.parseInt(request.getParameter("stock"));
//                String status = request.getParameter("status");
//                int prodTotalReviews = Integer.parseInt(request.getParameter("prodTotalReviews"));
//                int prodTotalScore = Integer.parseInt(request.getParameter("prodTotalScore"));
//
//                // 獲取當前商品的舊圖片路徑
//                Products currentProduct = productDao.findProductById(productID);
//                String productPic = currentProduct.getProductPic(); // 保持原有路徑
//
//
//                Products updatedProduct = new Products(productID, category, productName, productPic, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
//                productDao.updateProductById(productID, updatedProduct);
//
//                response.sendRedirect(request.getContextPath() + "/product/GetAllProducts.jsp");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
}