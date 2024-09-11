package order.service;

import java.util.List;

import order.bean.TicketOrderDetailsBean;
import order.dao.TicketOrderDetailsDaoImpl;


public class TicketOrderDetailsService {

	public static TicketOrderDetailsBean add(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int newID = TicketOrderDetailsDaoImpl.ticketOrderDetailsAdd(ticketOrderDetailsBean);//獲取自增長的主鍵

		return TicketOrderDetailsDaoImpl.ticketOrderDetailsSelect(newID);
	}

	public static TicketOrderDetailsBean delete(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = ticketOrderDetailsBean.getTicketOrderDetailID();
		TicketOrderDetailsBean ticketOrderDetailsBeanNew = TicketOrderDetailsDaoImpl.ticketOrderDetailsSelect(ticketOrderDetailID);
		TicketOrderDetailsDaoImpl.ticketOrderDetailsDelete(ticketOrderDetailID);

		return ticketOrderDetailsBeanNew;
	}

	public static TicketOrderDetailsBean update(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = TicketOrderDetailsDaoImpl.ticketOrderDetailsUpdate(ticketOrderDetailsBean);

		return TicketOrderDetailsDaoImpl.ticketOrderDetailsSelect(ticketOrderDetailID);
	}

	public static TicketOrderDetailsBean select(TicketOrderDetailsBean ticketOrderDetailsBean) {
		int ticketOrderDetailID = ticketOrderDetailsBean.getTicketOrderDetailID();

		return TicketOrderDetailsDaoImpl.ticketOrderDetailsSelect(ticketOrderDetailID);
	}

	public static List<TicketOrderDetailsBean> selectAll() {
		return TicketOrderDetailsDaoImpl.ticketOrderDetailsSelectAll();
	}
}
