package order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.PostExchange;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.criteria.CriteriaBuilder.Case;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import order.bean.ProdOrderDetailsBean;
import order.bean.ProdOrdersBean;
import order.bean.TicketOrderDetailsBean;
import order.bean.TicketOrdersBean;
import order.service.ProdOrderDetailsServiceImpl;
import order.service.ProdOrdersServiceImpl;
import order.service.TicketOrderDetailsService;
import order.service.TicketOrdersService;
import order.service.base.ProdOrdersService;
import util.HibernateUtil;

@Controller
@RequestMapping("order")
public class ProdOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdOrdersService prodOrdersService;

	@Autowired
	public ProdOrdersController(ProdOrdersService prodOrdersService) {
		this.prodOrdersService = prodOrdersService;
	}

	// 首頁方法
	@GetMapping
	public String home(Model model) {
		// 獲取所有訂單資料並傳遞給 JSP
		List<ProdOrdersBean> selectAll = prodOrdersService.selectAll();
		model.addAttribute("selectAll", selectAll);
		return "/order/ordersJSP/prodOrdersSelectAll"; // 返回首頁的 JSP 頁面
	}

	@PostMapping("insert")
	public String prodOrdersInsert(
			@RequestParam(value = "prodOrderID", required = false, defaultValue = "0") int prodOrderID,
			@RequestParam(value = "memberID", required = false, defaultValue = "0") int memberID,
			@RequestParam(value = "orderDate", required = false, defaultValue = "1990-01-01") Date orderDate,
			@RequestParam(value = "payments", required = false, defaultValue = "123") String payments,
			@RequestParam(value = "paymentInfo", required = false, defaultValue = "123") String paymentInfo,
			@RequestParam(value = "status", required = false, defaultValue = "未付款") String status,
			@RequestParam(value = "totalAmount", required = false, defaultValue = "0") int totalAmount,
			@RequestParam(value = "shippingStatus", required = false, defaultValue = "未配送") String shippingStatus,
			@RequestParam(value = "shippingID", required = false, defaultValue = "0") int shippingID,
			@RequestParam(value = "recipientName", required = false, defaultValue = "未輸入") String recipientName,
			@RequestParam(value = "address", required = false, defaultValue = "未輸入") String address,
			@RequestParam(value = "phone", required = false, defaultValue = "未輸入") String phone, Model model) {

		ProdOrdersBean prodOrdersBean = new ProdOrdersBean(prodOrderID, memberID, orderDate, payments, paymentInfo,
				status, totalAmount, shippingStatus, shippingID, recipientName, address, phone);
		if(prodOrderID == 0) {
			return "/order/ordersJSP/prodOrdersAdd";
		}
		
		prodOrdersService.add(prodOrdersBean);
		List<ProdOrdersBean> selectAll = prodOrdersService.selectAll();
		model.addAttribute("selectAll", selectAll);
		return "/order/ordersJSP/prodOrdersSelectAll";
	}

	@PostMapping("delete")
	public String prodOrdersDelete(@RequestParam(value = "prodOrderID", required = false, defaultValue = "0") int prodOrderID, Model model) {

		prodOrdersService.delete(prodOrderID);
		List<ProdOrdersBean> selectAll = prodOrdersService.selectAll();
		model.addAttribute("selectAll", selectAll);
		return "/order/ordersJSP/prodOrdersSelectAll";
	}

	@PostMapping("update")
	public String prodOrdersUpdate(@RequestParam(value = "prodOrderID", required = false, defaultValue = "0") int prodOrderID,
			@RequestParam(value = "memberID", required = false, defaultValue = "0") int memberID,
			@RequestParam(value = "orderDate", required = false, defaultValue = "1990-01-01") Date orderDate,
			@RequestParam(value = "payments", required = false, defaultValue = "123") String payments,
			@RequestParam(value = "paymentInfo", required = false, defaultValue = "123") String paymentInfo,
			@RequestParam(value = "status", required = false, defaultValue = "未付款") String status,
			@RequestParam(value = "totalAmount", required = false, defaultValue = "0") int totalAmount,
			@RequestParam(value = "shippingStatus", required = false, defaultValue = "未配送") String shippingStatus,
			@RequestParam(value = "shippingID", required = false, defaultValue = "0") int shippingID,
			@RequestParam(value = "recipientName", required = false, defaultValue = "未輸入") String recipientName,
			@RequestParam(value = "address", required = false, defaultValue = "未輸入") String address,
			@RequestParam(value = "phone", required = false, defaultValue = "未輸入") String phone,
			Model model) {

		if(memberID == 0) {
			ProdOrdersBean selectByprodOrderID = prodOrdersService.selectByprodOrderID(prodOrderID);
			model.addAttribute("selectByprodOrderID",selectByprodOrderID);
			return "/order/ordersJSP/prodOrdersUpdate";
		}
		
		ProdOrdersBean prodOrdersBean = new ProdOrdersBean(prodOrderID, memberID, orderDate, payments, paymentInfo,
				status, totalAmount, shippingStatus, shippingID, recipientName, address, phone);
		prodOrdersService.update(prodOrdersBean);
		List<ProdOrdersBean> selectAll = prodOrdersService.selectAll();
        model.addAttribute("selectAll", selectAll);
		return "/order/ordersJSP/prodOrdersSelectAll";
	}
}
