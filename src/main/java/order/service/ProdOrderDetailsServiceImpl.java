package order.service;

import java.util.List;

import org.hibernate.Session;

import order.bean.ProdOrderDetailsBean;
import order.bean.ProdOrdersBean;
import order.dao.ProdOrderDetailsDaoImpl;
import order.dao.ProdOrdersDaoImpl;
import order.service.base.ProdOrderDetailsService;


public class ProdOrderDetailsServiceImpl implements ProdOrderDetailsService {
	
public ProdOrderDetailsDaoImpl prodOrderDetailsDaoImpl;
	
	public ProdOrderDetailsServiceImpl(Session session) {
		prodOrderDetailsDaoImpl = new ProdOrderDetailsDaoImpl(session);
	}

	public ProdOrderDetailsBean select(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderID = prodOrderDetailsBean.getProdOrderDetailID();

		return prodOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderID);
	}

	public List<ProdOrderDetailsBean> selectAll(ProdOrdersBean prodOrderID) {
		
		return prodOrderDetailsDaoImpl.prodOrderDetailsSelectAll(prodOrderID);
	}

}
