package order.service;

import java.util.List;

import order.bean.ProdOrderDetailsBean;
import order.dao.ProdOrderDetailsDAO;


public class ProdOrderDetailsService {

	public static ProdOrderDetailsBean add(ProdOrderDetailsBean prodOrderDetailsBean) {
		int newID = ProdOrderDetailsDAO.prodOrderDetailsAdd(prodOrderDetailsBean);

		return ProdOrderDetailsDAO.prodOrderDetailsSelect(newID);
	}

	public static ProdOrderDetailsBean delete(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderDetailID = prodOrderDetailsBean.getProdOrderDetailID();
		ProdOrderDetailsBean prodOrderDetailsSelect = ProdOrderDetailsDAO.prodOrderDetailsSelect(prodOrderDetailID);
		ProdOrderDetailsDAO.prodOrderDetailsDelete(prodOrderDetailID);

		return prodOrderDetailsSelect;
	}

	public static ProdOrderDetailsBean update(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderDetailID = ProdOrderDetailsDAO.prodOrderDetailsUpdate(prodOrderDetailsBean);

		return ProdOrderDetailsDAO.prodOrderDetailsSelect(prodOrderDetailID);
	}

	public static ProdOrderDetailsBean select(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderID = prodOrderDetailsBean.getProdOrderID();

		return ProdOrderDetailsDAO.prodOrderDetailsSelect(prodOrderID);
	}

	public static List<ProdOrderDetailsBean> selectAll(int prodOrderID) {
		return ProdOrderDetailsDAO.prodOrderDetailsSelectAll(prodOrderID);
	}

	public static List<ProdOrderDetailsBean> addAll(List<ProdOrderDetailsBean> prodOrderDetailsBeans) {
		for(ProdOrderDetailsBean podb : prodOrderDetailsBeans) {
			add(podb);
		}

		return ProdOrderDetailsDAO.prodOrderDetailsSelectAllforCart(prodOrderDetailsBeans.get(0).getProdOrderID());
	}
}
