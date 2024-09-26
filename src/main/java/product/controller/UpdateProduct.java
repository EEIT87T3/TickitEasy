package product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import product.bean.Products;
import product.dao.ProductDao;
import product.service.ProductService;

@RequestMapping("/UpdateProduct/*")
@Transactional
@Controller
public class UpdateProduct {
	
	@Autowired
    ProductService productService;
	
	@Autowired
	    private ServletContext servletContext;

    public UpdateProduct() {
    }
    
    @GetMapping("getProductForUpdate")
    public String getProductForUpdate(@RequestParam("productID") int productID, Model model) {
        Products product = productService.findProductById(productID);
        model.addAttribute("product", product);
        return "product/UpdateProduct";
    }
    
    @PostMapping("updateProductById")
    public String updateProductById(
            @RequestParam("productID") int productID,
            @RequestParam("category") String category,
            @RequestParam("productName") String productName,
            @RequestParam("productDesc") String productDesc,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock,
            @RequestParam("status") String status,
            @RequestParam("prodTotalReviews") int prodTotalReviews,
            @RequestParam("prodTotalScore") int prodTotalScore,
            @RequestParam("oldproductPic") String oldproductPic,
            @RequestParam("newproductPic") MultipartFile filePart,
            HttpServletRequest request, Model model) throws IllegalStateException, IOException {

        Products updatedProduct = new Products();

        String filename = "";
        if (!filePart.getOriginalFilename().isEmpty()) {
            String uploadPath = servletContext.getRealPath("/") + "product/images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            filename = filePart.getOriginalFilename();
            File dest = new File(uploadPath + File.separator + filename);
            filePart.transferTo(dest);
        } else {
            System.out.println(oldproductPic);
            filename = oldproductPic;
        }

        // 設定Products屬性
        updatedProduct.setProductID(productID);
        updatedProduct.setCategory(category);
        updatedProduct.setProductName(productName);
        updatedProduct.setProductPic(filename);
        updatedProduct.setProductDesc(productDesc);
        updatedProduct.setPrice(price);
        updatedProduct.setStock(stock);
        updatedProduct.setStatus(status);
        updatedProduct.setProdTotalReviews(prodTotalReviews);
        updatedProduct.setProdTotalScore(prodTotalScore);
        
        updatedProduct = productService.updateProductById(productID, updatedProduct);
        
        if (updatedProduct != null) {
            // 更新成功，重定向到所有商品列表
            return "redirect:/GetAllProducts/getAllProducts";
        } else {
            // 更新失敗，返回更新頁面並顯示錯誤信息
            model.addAttribute("error", "更新失敗，請重試。");
            return "product/UpdateProduct";
        }
//        productService.updateProductById(productID, updatedProduct);
//        model.addAttribute("product", updatedProduct);
//        return "product/UpdateProduct";
    }
}