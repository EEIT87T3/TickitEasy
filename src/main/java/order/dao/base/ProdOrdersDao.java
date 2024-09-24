package order.dao.base;

import java.sql.Date;
import java.util.List;

import order.bean.ProdOrdersBean;

/**
 * 搜尋
	SELECT 單筆搜尋
		1.依照prodOrderID
		2.依照shippingID
	INSERT 新增
		1.依照ProdOrdersBean
	DELETE 刪除
		1.依照prodOrderID
	UPDATE 修改
		1.依照ProdOrdersBean
	SELECTALL 多筆搜尋
		1.空參
		2.依照memberID
		3.依照orderDate
		4.依照status
		5.依照shippingStatus
 */
public interface ProdOrdersDao {
	public int prodOrderAdd(ProdOrdersBean prodOrderBean); //INSERT 新增 依照ProdOrdersBean
	public void prodOrderDelete(int prodOrderID); //DELETE 刪除  依照prodOrderID
	public void prodOrderUpdate(ProdOrdersBean prodOrderBean); //UPDATE 修改 依照ProdOrdersBean
	public ProdOrdersBean prodOrderSelect(int prodOrderIDparameter);//SELECT 單筆搜尋 1.依照prodOrderID
	public ProdOrdersBean prodOrderSelectByShippingId(int shippingID);//SELECT 單筆搜尋 2.依照shippingID
	
	public List<ProdOrdersBean> prodOrderSelectAll(); //SELECTALL 多筆搜尋 1.空參 (搜尋該會員全部訂單 SELECTALL)
	public List<ProdOrdersBean> prodOrderSelectAllByMemberId(int memberIDParameter); //SELECTALL 多筆搜尋 2.依照memberID
	public List<ProdOrdersBean> prodOrderSelectAllByOrderDate(Date orderDate); //SELECTALL 多筆搜尋 3.依照orderDate
	public List<ProdOrdersBean> prodOrderSelectAllByStatus(String status);//SELECTALL 多筆搜尋 4.依照status
	public List<ProdOrdersBean> prodOrderSelectAllByShippingStatus(String shippingStatus);//SELECTALL 多筆搜尋 5.依照shippingStatus

}
