package product.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.bean.Cart;
import product.bean.CartItem;
import product.bean.Products;
import product.dao.ProductDao;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    ProductDao productDao = new ProductDao();
	    Products products = new Products();

	    @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        if ("addItem".equals(action)) {
	            addItem(request, response);
	        } else if ("deleteItem".equals(action)) {
	            deleteItem(request, response);
	        } else if ("clear".equals(action)) {
	            clear(request, response);
	        } else if ("updateCount".equals(action)) {
	            updateCount(request, response);
	        }
	    }

	    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String productIDStr = request.getParameter("id");
	        int id = Integer.parseInt(productIDStr);
	        products = productDao.findProductById(id);
	        CartItem carItem = new CartItem(products.getProductID(), products.getProductName(), 1, products.getPrice(), products.getStock());
	        Cart cart = (Cart) request.getSession().getAttribute("cart");
	        if (cart == null) {
	            cart = new Cart();
	            request.getSession().setAttribute("cart", cart);
	        }
	        cart.addItem(carItem);

	        String referer = request.getHeader("Referer");
	        response.sendRedirect(referer);
	    }

	    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String productIDStr = request.getParameter("id");
	        int id = Integer.parseInt(productIDStr);
	        Cart cart = (Cart) request.getSession().getAttribute("cart");
	        if (cart != null) {
	            cart.deleteItem(id);
	        }
	        response.sendRedirect(request.getHeader("Referer"));
	    }

	    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        Cart cart = (Cart) request.getSession().getAttribute("cart");
	        if (cart != null) {
	            cart.clear();
	        }
	        response.sendRedirect(request.getHeader("Referer"));
	    }

	    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String productIDStr = request.getParameter("id");
	        int id = Integer.parseInt(productIDStr);
	        String cartCount = request.getParameter("count");
	        int count = Integer.parseInt(cartCount);
	        Cart cart = (Cart) request.getSession().getAttribute("cart");
	        if (cart != null) {
	            cart.updateCount(id, count);
	        }
	        response.sendRedirect(request.getHeader("Referer"));
	    }
	}
