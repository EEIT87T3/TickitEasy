package order.service;

import java.util.List;

import order.bean.TicketOrdersBean;
import order.dao.TicketOrdersDAO;


public class TicketOrdersService {
	
	public static TicketOrdersBean add(TicketOrdersBean ticketOrdersBean) {
		int newID = TicketOrdersDAO.ticketOrderAdd(ticketOrdersBean);//獲取自增長的主鍵

		return TicketOrdersDAO.ticketOrderSelect(newID);
	}
	
	public static TicketOrdersBean delete(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = ticketOrdersBean.getTickedOrderID();
		TicketOrdersBean ticketOrdersBeanNew = TicketOrdersDAO.ticketOrderSelect(tickedOrderID);
		TicketOrdersDAO.ticketOrderDelete(tickedOrderID);
			
		return ticketOrdersBeanNew;
	}
	
	public static TicketOrdersBean update(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = TicketOrdersDAO.ticketOrderUpdate(ticketOrdersBean);
		
		return TicketOrdersDAO.ticketOrderSelect(tickedOrderID);
	}
	
	public static TicketOrdersBean select(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = ticketOrdersBean.getTickedOrderID();
		
		return TicketOrdersDAO.ticketOrderSelect(tickedOrderID);
	}
	
	public static List<TicketOrdersBean> selectAll() {
		return TicketOrdersDAO.ticketOrderSelectAll();
	}
}
