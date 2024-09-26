package product.controller;

import java.io.IOException;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.bean.Products;
import product.dao.ProductDao;
import product.service.ProductService;


@RequestMapping("/DeleteProduct/*")
@Transactional
@Controller
public class DeleteProduct {
	
	@Autowired
	ProductService productService;
	
    public DeleteProduct() {
    }
    
    @PostMapping("deleteProductById")
    public String deleteProductById(@RequestParam("productID")int productID) {
        productService.deleteProductById(productID);
        return "redirect:/GetAllProducts/getAllProducts";
    }
    
}
