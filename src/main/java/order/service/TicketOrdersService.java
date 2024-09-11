package order.service;

import java.util.List;

import order.bean.TicketOrdersBean;
import order.dao.TicketOrdersDaoImpl;


public class TicketOrdersService {

	public static TicketOrdersBean add(TicketOrdersBean ticketOrdersBean) {
		int newID = TicketOrdersDaoImpl.ticketOrderAdd(ticketOrdersBean);//獲取自增長的主鍵

		return TicketOrdersDaoImpl.ticketOrderSelect(newID);
	}

	public static TicketOrdersBean delete(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = ticketOrdersBean.getTickedOrderID();
		TicketOrdersBean ticketOrdersBeanNew = TicketOrdersDaoImpl.ticketOrderSelect(tickedOrderID);
		TicketOrdersDaoImpl.ticketOrderDelete(tickedOrderID);

		return ticketOrdersBeanNew;
	}

	public static TicketOrdersBean update(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = TicketOrdersDaoImpl.ticketOrderUpdate(ticketOrdersBean);

		return TicketOrdersDaoImpl.ticketOrderSelect(tickedOrderID);
	}

	public static TicketOrdersBean select(TicketOrdersBean ticketOrdersBean) {
		int tickedOrderID = ticketOrdersBean.getTickedOrderID();

		return TicketOrdersDaoImpl.ticketOrderSelect(tickedOrderID);
	}

	public static List<TicketOrdersBean> selectAll() {
		return TicketOrdersDaoImpl.ticketOrderSelectAll();
	}
}
