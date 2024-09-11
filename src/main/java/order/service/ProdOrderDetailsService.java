package order.service;

import java.util.List;

import order.bean.ProdOrderDetailsBean;
import order.dao.ProdOrderDetailsDaoImpl;


public class ProdOrderDetailsService {

	public static ProdOrderDetailsBean add(ProdOrderDetailsBean prodOrderDetailsBean) {
		int newID = ProdOrderDetailsDaoImpl.prodOrderDetailsAdd(prodOrderDetailsBean);

		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(newID);
	}

	public static ProdOrderDetailsBean delete(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderDetailID = prodOrderDetailsBean.getProdOrderDetailID();
		ProdOrderDetailsBean prodOrderDetailsSelect = ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderDetailID);
		ProdOrderDetailsDaoImpl.prodOrderDetailsDelete(prodOrderDetailID);

		return prodOrderDetailsSelect;
	}

	public static ProdOrderDetailsBean update(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderDetailID = ProdOrderDetailsDaoImpl.prodOrderDetailsUpdate(prodOrderDetailsBean);

		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderDetailID);
	}

	public static ProdOrderDetailsBean select(ProdOrderDetailsBean prodOrderDetailsBean) {
		int prodOrderID = prodOrderDetailsBean.getProdOrderID();

		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelect(prodOrderID);
	}

	public static List<ProdOrderDetailsBean> selectAll(int prodOrderID) {
		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelectAll(prodOrderID);
	}

	public static List<ProdOrderDetailsBean> addAll(List<ProdOrderDetailsBean> prodOrderDetailsBeans) {
		for(ProdOrderDetailsBean podb : prodOrderDetailsBeans) {
			add(podb);
		}

		return ProdOrderDetailsDaoImpl.prodOrderDetailsSelectAllforCart(prodOrderDetailsBeans.get(0).getProdOrderID());
	}
}
