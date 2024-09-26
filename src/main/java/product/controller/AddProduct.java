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

@RequestMapping("/AddProduct/*")
@Transactional
@Controller
public class AddProduct {
	
	@Autowired
	ProductService productService;

    @Autowired
    private ServletContext servletContext;

	
    public AddProduct() {
    }
    
    //測試
    @GetMapping("/showForm")
    public String showAddProductForm() {
        return "product/AddProduct";  // 返回 JSP 頁面
    }
    
    @PostMapping("addProduct")
    public String addProducts(
            @RequestParam("category") String category,
            @RequestParam("productName") String productName,
            @RequestParam("productDesc") String productDesc,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock,
            @RequestParam("status") String status,
            @RequestParam("prodTotalReviews") int prodTotalReviews,
            @RequestParam("prodTotalScore") int prodTotalScore,
            @RequestParam("productPic") MultipartFile productPic,
			HttpServletRequest request,Model model
    ) throws IOException {
    	
    	Products addProduct = new Products();
    	
    	String filename = productPic.getOriginalFilename();
    	addProduct.setProductPic(filename);
    	
    	//存進本端
        String uploadPath = request.getServletContext().getRealPath("") + "product/images";
        System.out.println("uploadPath:"+uploadPath);
        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
        }
        File dest = new File(uploadPath + File.separator + filename);
        productPic.transferTo(dest);
    	
        
        // 設定Products屬性
        //addProduct.setProductID(productID);
        addProduct.setCategory(category);
        addProduct.setProductName(productName);
        addProduct.setProductPic(filename);
        addProduct.setProductDesc(productDesc);
        addProduct.setPrice(price);
        addProduct.setStock(stock);
        addProduct.setStatus(status);
        addProduct.setProdTotalReviews(prodTotalReviews);
        addProduct.setProdTotalScore(prodTotalScore);
        
        //addProduct = productService.updateProductById(productID, updatedProduct);
        addProduct = productService.addProducts(addProduct);

        if (addProduct != null) {
            // 更新成功，重定向到所有商品列表
            return "redirect:/GetAllProducts/getAllProducts";
        } else {
            // 更新失敗，返回更新頁面並顯示錯誤信息
            model.addAttribute("error", "更新失敗，請重試。");
            return "product/AddProduct";
        }
        // 使用 ModelAndView 返回商品資訊與視圖
//        return "product/AddProduct";
    }
    
}
