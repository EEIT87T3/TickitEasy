package product.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.bean.Products;
import product.dao.ProductDao;
import product.service.ProductService;

@RequestMapping("/GetAllProducts")
@Transactional
@Controller
public class GetAllProducts {
	
	@Autowired
	ProductService productService;
	
    public GetAllProducts() {
    }
    
    //@PostMapping("getAllProducts")
    @GetMapping("/getAllProducts")
    public String getAllProducts(Model m) {
    	List<Products> product = productService.findAll();
    	System.out.println(product);
    	m.addAttribute("product", product);
    	return "/product/GetAllProducts";
    	
    }
    
}
