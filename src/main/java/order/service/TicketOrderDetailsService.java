package order.service;

import java.util.List;

import order.bean.TicketOrderDetailsBean;
import order.dao.TicketOrderDetailsDAO;


public class TicketOrderDetailsService {

	public static TicketOrderDetailsBean add(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int newID = TicketOrderDetailsDAO.ticketOrderDetailsAdd(ticketOrderDetailsBean);//獲取自增長的主鍵

		return TicketOrderDetailsDAO.ticketOrderDetailsSelect(newID);
	}
	
	public static TicketOrderDetailsBean delete(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = ticketOrderDetailsBean.getTicketOrderDetailID();
		TicketOrderDetailsBean ticketOrderDetailsBeanNew = TicketOrderDetailsDAO.ticketOrderDetailsSelect(ticketOrderDetailID);
		TicketOrderDetailsDAO.ticketOrderDetailsDelete(ticketOrderDetailID);
			
		return ticketOrderDetailsBeanNew;
	}
	
	public static TicketOrderDetailsBean update(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = TicketOrderDetailsDAO.ticketOrderDetailsUpdate(ticketOrderDetailsBean);
		
		return TicketOrderDetailsDAO.ticketOrderDetailsSelect(ticketOrderDetailID);
	}
	
	public static TicketOrderDetailsBean select(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = ticketOrderDetailsBean.getTicketOrderDetailID();
		
		return TicketOrderDetailsDAO.ticketOrderDetailsSelect(ticketOrderDetailID);
	}
	
	public static List<TicketOrderDetailsBean> selectAll() {
		return TicketOrderDetailsDAO.ticketOrderDetailsSelectAll();
	}
}
