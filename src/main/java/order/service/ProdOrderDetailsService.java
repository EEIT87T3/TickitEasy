package order.service;

import java.util.List;

import org.hibernate.Session;

import order.bean.ProdOrderDetailsBean;
import order.bean.ProdOrdersBean;
import order.dao.ProdOrderDetailsDaoImpl;
import order.dao.ProdOrdersDaoImpl;


public class ProdOrderDetailsService {
	
public ProdOrderDetailsDaoImpl prodOrderDetailsDaoImpl;
	
	public ProdOrderDetailsService(Session session) {
		prodOrderDetailsDaoImpl = new ProdOrderDetailsDaoImpl(session);
	}

//	public static ProdOrderDetailsBean add(ProdOrderDetailsBean prodOrderDetailsBean) {
//		int newID = ProdOrderDetailsDaoImpl.prodOrderDetailsAdd(prodOrderDetailsBean);
//
//		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(newID);
//	}

//	public static ProdOrderDetailsBean delete(ProdOrderDetailsBean prodOrderDetailsBean) {
//		int prodOrderDetailID = prodOrderDetailsBean.getProdOrderDetailID();
//		ProdOrderDetailsBean prodOrderDetailsSelect = ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderDetailID);
//		ProdOrderDetailsDaoImpl.prodOrderDetailsDelete(prodOrderDetailID);
//
//		return prodOrderDetailsSelect;
//	}
//
//	public static ProdOrderDetailsBean update(ProdOrderDetailsBean prodOrderDetailsBean) {
//		int prodOrderDetailID = ProdOrderDetailsDaoImpl.prodOrderDetailsUpdate(prodOrderDetailsBean);
//
//		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderDetailID);
//	}
//
	public ProdOrderDetailsBean select(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderID = prodOrderDetailsBean.getProdOrderDetailID();

		return prodOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderID);
	}

	public List<ProdOrderDetailsBean> selectAll(ProdOrdersBean prodOrderID) {
		
		return prodOrderDetailsDaoImpl.prodOrderDetailsSelectAll(prodOrderID);
	}
//
//	public static List<ProdOrderDetailsBean> addAll(List<ProdOrderDetailsBean> prodOrderDetailsBeans) {
//		for(ProdOrderDetailsBean podb : prodOrderDetailsBeans) {
//			add(podb);
//		}
//
//		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelectAllforCart(prodOrderDetailsBeans.get(0).getProdOrderID());
//	}
}
