package order.service;

import java.util.List;

import org.hibernate.Session;

import order.bean.ProdOrdersBean;
import order.dao.ProdOrdersDaoImpl;

public class ProdOrdersService {
	
	public ProdOrdersDaoImpl prodOrdersDAO;
	
	public ProdOrdersService(Session session) {
		prodOrdersDAO = new ProdOrdersDaoImpl(session);
	}
	
	public ProdOrdersBean add(ProdOrdersBean prodOrderBean) {
		int newID = prodOrdersDAO.prodOrderAdd(prodOrderBean); //獲取自增長的主鍵

		return prodOrdersDAO.prodOrderSelect(newID);
	}

	public ProdOrdersBean delete(ProdOrdersBean prodOrderBean) {
		int prodOrderID = prodOrderBean.getProdOrderID();
		ProdOrdersBean prodOrderBeanNew = prodOrdersDAO.prodOrderSelect(prodOrderID);
		prodOrdersDAO.prodOrderDelete(prodOrderID);

		return prodOrderBeanNew;
	}

	public ProdOrdersBean update(ProdOrdersBean prodOrderBean) {
		prodOrdersDAO.prodOrderUpdate(prodOrderBean);

		return prodOrdersDAO.prodOrderSelect(prodOrderBean.getProdOrderID());
	}

	public ProdOrdersBean select(ProdOrdersBean prodOrderBean) {
		int prodOrderID = prodOrderBean.getProdOrderID();

		return prodOrdersDAO.prodOrderSelect(prodOrderID);
	}

	public List<ProdOrdersBean> selectAll(int memberID) {
		return prodOrdersDAO.prodOrderSelectAll(memberID);
	}

	public int addReturnID(ProdOrdersBean prodOrderBean) {
		int newID = prodOrdersDAO.prodOrderAdd(prodOrderBean); //獲取自增長的主鍵

		return newID;
	}
}
