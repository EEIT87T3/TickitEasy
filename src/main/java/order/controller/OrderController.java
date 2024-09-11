package order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import order.bean.ProdOrderDetailsBean;
import order.bean.ProdOrdersBean;
import order.bean.TicketOrderDetailsBean;
import order.bean.TicketOrdersBean;
import order.service.ProdOrderDetailsService;
import order.service.ProdOrdersService;
import order.service.TicketOrderDetailsService;
import order.service.TicketOrdersService;
import util.HibernateUtil;

//接收所有客戶端傳來的資料，並分發下去給service
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		if (request.getParameter("order") != null) {
			ProdOrdersBean prodOrderBeanNew = null;
			ProdOrdersService prodOrdersService = new ProdOrdersService(session);
			switch (request.getParameter("order")) {
			case "products":
				ProdOrdersBean prodOrderBean = prodOrderBean(request);
				// 調用ProdOrdersService.add，回傳的int給予list
				int newID = prodOrdersService.addReturnID(prodOrderBean); // 獲取prodOrder insert後 pk自增長鍵值
				// 將新的prodOrderID賦予list每一個ProdOrderDetailsBean
				List<ProdOrderDetailsBean> list = (List<ProdOrderDetailsBean>) request.getSession()
						.getAttribute("listNew");
				// 新List，已經prodOrderID給予每一個對象
				List<ProdOrderDetailsBean> listNew = encapsulation(list, newID);
				List<ProdOrderDetailsBean> listNewAll = ProdOrderDetailsService.addAll(listNew); //返回加成功的List
				request.setAttribute("listNewAll", listNewAll);
				request.getRequestDispatcher("order/ordersJSP/CartAddSuccess.jsp").forward(request,
						response);

				break;
			}

		}



		if (request.getParameter("form") != null) {
			switch (request.getParameter("form")) {
			case "prodOrders":
				ProdOrdersBean prodOrderBean = prodOrderBean(request);
				ProdOrdersBean prodOrderBeanNew = prodOrders(request.getParameter("button"), prodOrderBean, request);
				request.setAttribute("button", request.getParameter("button"));
				request.setAttribute("prodOrderBeanNew", prodOrderBeanNew);
				if (request.getParameter("button").equals("selectAll")) {
					request.getRequestDispatcher("order/ordersJSP/prodOrdersSelectAll.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("order/ordersJSP/prodOrders.jsp").forward(request, response);
				}
				break;
			case "prodOrderDetails":
				ProdOrderDetailsBean prodOrderDetailsBean = prodOrderDetailsBean(request);
				ProdOrderDetailsBean prodOrderDetailsBeanNew = prodOrderDetails(request.getParameter("button"),
						prodOrderDetailsBean, request);
				request.setAttribute("button", request.getParameter("button"));
				request.setAttribute("prodOrderDetailsBeanNew", prodOrderDetailsBeanNew);
				if (request.getParameter("button").equals("selectAll")) {
					request.getRequestDispatcher("order/ordersJSP/prodOrderDetailsSelectAll.jsp").forward(request,
							response);
				} else {
					request.getRequestDispatcher("order/ordersJSP/prodOrderDetails.jsp").forward(request, response);
				}
				break;
			case "ticketOrders":
				TicketOrdersBean ticketOrdersBean = ticketOrdersBean(request);
				TicketOrdersBean ticketOrdersBeanNew = ticketOrders(request.getParameter("button"), ticketOrdersBean,
						request);
				request.setAttribute("button", request.getParameter("button"));
				request.setAttribute("ticketOrdersBeanNew", ticketOrdersBeanNew);
				if (request.getParameter("button").equals("selectAll")) {
					request.getRequestDispatcher("order/ordersJSP/ticketOrdersSelectAll.jsp").forward(request,
							response);
				} else {
					request.getRequestDispatcher("order/ordersJSP/ticketOrders.jsp").forward(request, response);
				}

				break;
			case "ticketOrderDetails":
				TicketOrderDetailsBean ticketOrderDetailsBean = ticketOrderDetailsBean(request);
				TicketOrderDetailsBean ticketOrderDetailsBeanNew = ticketOrderDetails(request.getParameter("button"),
						ticketOrderDetailsBean, request);
				request.setAttribute("button", request.getParameter("button"));
				request.setAttribute("ticketOrderDetailsBeanNew", ticketOrderDetailsBeanNew);
				if (request.getParameter("button").equals("selectAll")) {
					request.getRequestDispatcher("order/ordersJSP/ticketOrderDetailsSelectAll.jsp").forward(request,
							response);
				} else {
					request.getRequestDispatcher("order/ordersJSP/ticketOrderDetails.jsp").forward(request, response);
				}
				break;

			}

		}
	}

	public ProdOrdersBean prodOrders(String method, ProdOrdersBean prodOrderBean, HttpServletRequest request) { // request為了selectAll而塞入
		ProdOrdersBean prodOrderBeanNew = null;
		session = sessionFactory.getCurrentSession();
		ProdOrdersService prodOrdersService = new ProdOrdersService(session);
		switch (method) {
		case "add":
			prodOrderBeanNew = prodOrdersService.add(prodOrderBean);
			break;
		case "delete":
			prodOrderBeanNew = prodOrdersService.delete(prodOrderBean);
			break;
		case "update":
			prodOrderBeanNew = prodOrdersService.update(prodOrderBean);
			break;
		case "select":
			prodOrderBeanNew = prodOrdersService.select(prodOrderBean);
			break;
		case "selectAll":
			List<ProdOrdersBean> list = prodOrdersService.selectAll(prodOrderBean.getMemberID());
			request.setAttribute("list", list);
			break;
		}
		return prodOrderBeanNew;
	}

	public ProdOrdersBean prodOrderBean(HttpServletRequest request) { // 將使用者資料封裝成ProdOrderBean

		String prodOrderIDStr = request.getParameter("prodOrderID");
		int prodOrderID = Integer
				.parseInt((prodOrderIDStr != null && !prodOrderIDStr.trim().isEmpty() ? prodOrderIDStr : "0"));
		String memberIDStr = request.getParameter("memberID");
		int memberID = Integer.parseInt((memberIDStr != null && !memberIDStr.trim().isEmpty() ? memberIDStr : "0"));
		String orderDateStr = request.getParameter("orderDate");
		Date orderDate = Date
				.valueOf((orderDateStr != null && !orderDateStr.trim().isEmpty() ? orderDateStr : "1900-01-01"));
		String payments = request.getParameter("payments");
		String paymentInfo = request.getParameter("paymentInfo");
		String status = request.getParameter("status");
		String totalAmoungStr = request.getParameter("totalAmount");
		int totalAmount = Integer
				.parseInt((totalAmoungStr != null && !totalAmoungStr.trim().isEmpty() ? totalAmoungStr : "0"));
		String shippingStatus = request.getParameter("shippingStatus");
		String shippingIDStr = request.getParameter("shippingID");
		int shippingID = Integer
				.parseInt((shippingIDStr != null && !shippingIDStr.trim().isEmpty() ? shippingIDStr : "0"));
		String recipientName = request.getParameter("recipientName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		payments = (payments != null && !payments.trim().isEmpty()) ? payments : "未輸入";
		paymentInfo = (paymentInfo != null && !paymentInfo.trim().isEmpty()) ? paymentInfo : "未輸入";
		status = (status != null && !status.trim().isEmpty()) ? status : "未輸入";
		shippingStatus = (shippingStatus != null && !shippingStatus.trim().isEmpty()) ? shippingStatus : "未輸入";
		recipientName = (recipientName != null && !recipientName.trim().isEmpty()) ? recipientName : null;
		address = (address != null && !address.trim().isEmpty()) ? address : null;
		phone = (phone != null && !phone.trim().isEmpty()) ? phone : null;

		ProdOrdersBean prodOrderBean = new ProdOrdersBean(prodOrderID, memberID, orderDate, payments, paymentInfo,
				status, totalAmount, shippingStatus, shippingID, recipientName, address, phone);

		return prodOrderBean;
	}

	public ProdOrderDetailsBean prodOrderDetails(String method, ProdOrderDetailsBean prodOrderDetailsBean,
			HttpServletRequest request) { //
		ProdOrderDetailsBean prodOrderDetailsBeanNew = null;
		switch (method) {
		case "add":
			prodOrderDetailsBeanNew = ProdOrderDetailsService.add(prodOrderDetailsBean);
			break;
		case "delete":
			prodOrderDetailsBeanNew = ProdOrderDetailsService.delete(prodOrderDetailsBean);
			break;
		case "update":
			prodOrderDetailsBeanNew = ProdOrderDetailsService.update(prodOrderDetailsBean);
			break;
		case "select":
			prodOrderDetailsBeanNew = ProdOrderDetailsService.select(prodOrderDetailsBean);
			break;
		case "selectAll":
			List<ProdOrderDetailsBean> list = ProdOrderDetailsService.selectAll(prodOrderDetailsBean.getProdOrderID());
			request.setAttribute("list", list);
			break;
		}
		return prodOrderDetailsBeanNew;
	}

	public ProdOrderDetailsBean prodOrderDetailsBean(HttpServletRequest request) { // 將使用者資料封裝成ProdOrderDetailsBean
		String prodOrderDetailIDStr = request.getParameter("prodOrderDetailID");
		int prodOrderDetailID = Integer.parseInt(
				prodOrderDetailIDStr != null && !prodOrderDetailIDStr.trim().isEmpty() ? prodOrderDetailIDStr : "0");
		String prodOrderIDStr = request.getParameter("prodOrderID");
		int prodOrderID = Integer
				.parseInt(prodOrderIDStr != null && !prodOrderIDStr.trim().isEmpty() ? prodOrderIDStr : "0");
		String productIDStr = request.getParameter("productID");
		int productID = Integer.parseInt(productIDStr != null && !productIDStr.trim().isEmpty() ? productIDStr : "0");
		String priceStr = request.getParameter("price");
		int price = Integer.parseInt(priceStr != null && !priceStr.trim().isEmpty() ? priceStr : "0");
		String quantityStr = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr != null && !quantityStr.trim().isEmpty() ? quantityStr : "0");
		String contentStr = request.getParameter("content");
		String content = contentStr != null && !contentStr.trim().isEmpty() ? contentStr : "未輸入";
		String reviewTimeStr = request.getParameter("reviewTime");
		Date reviewTime = Date
				.valueOf(reviewTimeStr != null && !reviewTimeStr.trim().isEmpty() ? reviewTimeStr : "1900-01-01");
		String scoreStr = request.getParameter("score");
		int score = Integer.parseInt(scoreStr != null && !scoreStr.trim().isEmpty() ? scoreStr : "0");

		ProdOrderDetailsBean prodOrderDetailsBean = new ProdOrderDetailsBean(prodOrderDetailID, prodOrderID, productID,
				price, quantity, content, reviewTime, score);

		return prodOrderDetailsBean;
	}

	public TicketOrdersBean ticketOrders(String method, TicketOrdersBean ticketOrdersBean, HttpServletRequest request) { //
		TicketOrdersBean ticketOrdersBeanNew = null;
		switch (method) {
		case "add":
			ticketOrdersBeanNew = TicketOrdersService.add(ticketOrdersBean);
			break;
		case "delete":
			ticketOrdersBeanNew = TicketOrdersService.delete(ticketOrdersBean);
			break;
		case "update":
			ticketOrdersBeanNew = TicketOrdersService.update(ticketOrdersBean);
			break;
		case "select":
			ticketOrdersBeanNew = TicketOrdersService.select(ticketOrdersBean);
			break;
		case "selectAll":
			List<TicketOrdersBean> list = TicketOrdersService.selectAll();
			request.setAttribute("list", list);
			break;
		}
		return ticketOrdersBeanNew;
	}

	public TicketOrdersBean ticketOrdersBean(HttpServletRequest request) { // 將使用者資料封裝成TicketOrdersBean
		String ticketOrderIDStr = request.getParameter("ticketOrderID");
		int ticketOrderID = Integer
				.parseInt(ticketOrderIDStr != null && !ticketOrderIDStr.trim().isEmpty() ? ticketOrderIDStr : "0");
		String memberIDStr = request.getParameter("memberID");
		int memberID = Integer.parseInt(memberIDStr != null && !memberIDStr.trim().isEmpty() ? memberIDStr : "0");
		String orderDateStr = request.getParameter("orderDate");
		Date orderDate = Date
				.valueOf(orderDateStr != null && !orderDateStr.trim().isEmpty() ? orderDateStr : "1900-01-01");
		String paymentsStr = request.getParameter("payments");
		String payments = paymentsStr != null && !paymentsStr.trim().isEmpty() ? paymentsStr : "未輸入";
		String statusStr = request.getParameter("status");
		String status = statusStr != null && !statusStr.trim().isEmpty() ? statusStr : "未輸入";
		String totalAmountStr = request.getParameter("totalAmount");
		int totalAmount = Integer
				.parseInt(totalAmountStr != null && !totalAmountStr.trim().isEmpty() ? totalAmountStr : "0");

		TicketOrdersBean ticketOrdersBean = new TicketOrdersBean(ticketOrderID, memberID, orderDate, payments, status,
				totalAmount);
		return ticketOrdersBean;
	}

	public TicketOrderDetailsBean ticketOrderDetails(String method, TicketOrderDetailsBean ticketOrderDetailsBean,
			HttpServletRequest request) { //
		TicketOrderDetailsBean ticketOrderDetailsBeanNew = null;
		switch (method) {
		case "add":
			ticketOrderDetailsBeanNew = TicketOrderDetailsService.add(ticketOrderDetailsBean);
			break;
		case "delete":
			ticketOrderDetailsBeanNew = TicketOrderDetailsService.delete(ticketOrderDetailsBean);
			break;
		case "update":
			ticketOrderDetailsBeanNew = TicketOrderDetailsService.update(ticketOrderDetailsBean);
			break;
		case "select":
			ticketOrderDetailsBeanNew = TicketOrderDetailsService.select(ticketOrderDetailsBean);
			break;
		case "selectAll":
			List<TicketOrderDetailsBean> list = TicketOrderDetailsService.selectAll();
			request.setAttribute("list", list);
			break;
		}
		return ticketOrderDetailsBeanNew;
	}

	public TicketOrderDetailsBean ticketOrderDetailsBean(HttpServletRequest request) { // 將使用者資料封裝成TicketOrdersBean
		String ticketOrderDetailIDStr = request.getParameter("ticketOrderDetailID");
		int ticketOrderDetailID = Integer.parseInt(
				ticketOrderDetailIDStr != null && !ticketOrderDetailIDStr.trim().isEmpty() ? ticketOrderDetailIDStr
						: "0");
		String ticketOrderIDStr = request.getParameter("ticketOrderID");
		int ticketOrderID = Integer
				.parseInt(ticketOrderIDStr != null && !ticketOrderIDStr.trim().isEmpty() ? ticketOrderIDStr : "0");
		String ticketTypeIDStr = request.getParameter("ticketTypeID");
		int ticketTypeID = Integer
				.parseInt(ticketTypeIDStr != null && !ticketTypeIDStr.trim().isEmpty() ? ticketTypeIDStr : "0");
		String ticketCollectionMethodStr = request.getParameter("ticketCollectionMethod");
		String ticketCollectionMethod = ticketCollectionMethodStr != null && !ticketCollectionMethodStr.trim().isEmpty()
				? ticketCollectionMethodStr
				: "未輸入";
		String priceStr = request.getParameter("price");
		int price = Integer.parseInt(priceStr != null && !priceStr.trim().isEmpty() ? priceStr : "0");
		String ticketUUIDStr = request.getParameter("ticketUUID");
		String ticketUUID = ticketUUIDStr != null && !ticketUUIDStr.trim().isEmpty() ? ticketUUIDStr : "未輸入";
		String ticketStatusStr = request.getParameter("ticketStatus");
		int ticketStatus = Integer
				.parseInt(ticketStatusStr != null && !ticketStatusStr.trim().isEmpty() ? ticketStatusStr : "0");
		String contentStr = request.getParameter("content");
		String content = contentStr != null && !contentStr.trim().isEmpty() ? contentStr : "未輸入";
		String reviewTimeStr = request.getParameter("reviewTime");
		Date reviewTime = Date
				.valueOf(reviewTimeStr != null && !reviewTimeStr.trim().isEmpty() ? reviewTimeStr : "1900-01-01");
		String scoreStr = request.getParameter("score");
		int score = Integer.parseInt(scoreStr != null && !scoreStr.trim().isEmpty() ? scoreStr : "0");
		TicketOrderDetailsBean ticketOrderDetailsBean = new TicketOrderDetailsBean(ticketOrderDetailID, ticketOrderID,
				ticketTypeID, ticketCollectionMethod, price, ticketUUID, ticketStatus, content, reviewTime, score);

		return ticketOrderDetailsBean;
	}

	// 購物車專用
	public List<ProdOrderDetailsBean> encapsulation(List<ProdOrderDetailsBean> list, int newID) {
		List<ProdOrderDetailsBean> listNew = new ArrayList<>();
		for (ProdOrderDetailsBean podbOld : list) {
			ProdOrderDetailsBean podbNew = new ProdOrderDetailsBean(newID, podbOld.getProductID(), podbOld.getPrice(),
					podbOld.getQuantity());
			listNew.add(podbNew);
		}

		return listNew;
	}
}
