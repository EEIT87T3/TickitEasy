package order.dao.base;

import java.util.List;

import order.bean.ProdOrdersBean;

public interface ProdOrdersDao {
	public int prodOrderAdd(ProdOrdersBean prodOrderBean);
	public void prodOrderDelete(int prodOrderID);
	public void prodOrderUpdate(ProdOrdersBean prodOrderBean);
	public ProdOrdersBean prodOrderSelect(int prodOrderIDparameter);
	public List<ProdOrdersBean> prodOrderSelectAll(int memberIDParameter);
}
