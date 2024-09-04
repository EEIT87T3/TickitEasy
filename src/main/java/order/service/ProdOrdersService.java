package order.service;

import java.util.List;

import order.bean.ProdOrdersBean;
import order.dao.ProdOrdersDAO;

public class ProdOrdersService {
	
	public static ProdOrdersBean add(ProdOrdersBean prodOrderBean) {
		int newID = ProdOrdersDAO.prodOrderAdd(prodOrderBean); //獲取自增長的主鍵

		return ProdOrdersDAO.prodOrderSelect(newID);
	}
	
	public static ProdOrdersBean delete(ProdOrdersBean prodOrderBean) {
		int prodOrderID = prodOrderBean.getProdOrderID();
		ProdOrdersBean prodOrderBeanNew = ProdOrdersDAO.prodOrderSelect(prodOrderID);
		ProdOrdersDAO.prodOrderDelete(prodOrderID);
			
		return prodOrderBeanNew;
	}
	
	public static ProdOrdersBean update(ProdOrdersBean prodOrderBean) {
		int prodOrderID = ProdOrdersDAO.prodOrderUpdate(prodOrderBean);
		
		return ProdOrdersDAO.prodOrderSelect(prodOrderID);
	}
	
	public static ProdOrdersBean select(ProdOrdersBean prodOrderBean) {
		int prodOrderID = prodOrderBean.getProdOrderID();
		
		return ProdOrdersDAO.prodOrderSelect(prodOrderID);
	}
	
	public static List<ProdOrdersBean> selectAll(int memberID) {
		return ProdOrdersDAO.prodOrderSelectAll(memberID);
	}
	
	public static int addReturnID(ProdOrdersBean prodOrderBean) {
		int newID = ProdOrdersDAO.prodOrderAdd(prodOrderBean); //獲取自增長的主鍵

		return newID;
	}
}
