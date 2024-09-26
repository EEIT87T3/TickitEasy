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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.bean.Products;
import product.dao.ProductDao;
import product.service.ProductService;

@RequestMapping("/GetProduct/*")
@Transactional
@Controller
public class GetProduct {
	
	@Autowired
	ProductService productService;
    public GetProduct() {
    }
    
    @GetMapping("findProductsById")
    public String findProductsById(@RequestParam(value = "productID") int productID, Model m) {
        //List<Products> product = (List<Products>) productService.findProductById(productID);
    	Products product = productService.findProductById(productID); 
        System.out.println(product);
        m.addAttribute("product", product);
        //return "redirect:/GetProduct/GetProduct";
        return "product/GetProduct";
    }
    /*
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String productName = request.getParameter("productName");

		// 使用 findProductsByName 方法進行模糊查詢
		List<Products> products = productService.findProductsByName(productName);
		request.setAttribute("products", products);
		request.getRequestDispatcher("/product/GetProduct.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	*/
}
