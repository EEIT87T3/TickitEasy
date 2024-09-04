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
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();
    public AddProduct() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 從請求中獲取新增的商品信息
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String status = request.getParameter("status");
        int prodTotalReviews = Integer.parseInt(request.getParameter("prodTotalReviews"));
        int prodTotalScore = Integer.parseInt(request.getParameter("prodTotalScore"));

     // 獲取上傳的圖片文件
        Part filePart = request.getPart("productPic");
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // 獲取上傳文件的文件名

        String uploadPath = getServletContext().getRealPath("") + "product/images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

     // 確保儲存到資料庫的路徑是相對於 web 應用根目錄的路徑
        String relativePath = "product/images/" + fileName;
        Products newProduct = new Products(category, productName, relativePath, productDesc, price, stock, status, prodTotalReviews, prodTotalScore);
        try {
            productDao.addProducts(newProduct);
            // 新增完成後跳轉到顯示該商品詳情的頁面
            request.setAttribute("product", newProduct);
            request.getRequestDispatcher("/product/AddProduct.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
