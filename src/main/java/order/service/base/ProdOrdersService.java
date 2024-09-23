package order.service.base;

import java.sql.Date;
import java.util.List;

import order.bean.ProdOrdersBean;

public interface ProdOrdersService {
	public ProdOrdersBean add(ProdOrdersBean prodOrderBean); //INSERT 新增 依照ProdOrdersBean
	public ProdOrdersBean delete(int prodOrderID); //DELETE 刪除  依照prodOrderID
	public ProdOrdersBean update(ProdOrdersBean prodOrderBean); //UPDATE 修改 依照ProdOrdersBean
	public ProdOrdersBean selectByprodOrderID(int prodOrderID); //SELECT 單筆搜尋 1.依照prodOrderID
	public ProdOrdersBean selectByShippingId(int shippingID);//SELECT 單筆搜尋 2.依照shippingID
	
	public List<ProdOrdersBean> selectAll(); //SELECTALL 多筆搜尋 1.空參 (搜尋該會員全部訂單 SELECTALL)
	public List<ProdOrdersBean> selectAllByMemberId(int memberIDParameter); //SELECTALL 多筆搜尋 2.依照memberID
	public List<ProdOrdersBean> selectAllByOrderDate(Date orderDate); //SELECTALL 多筆搜尋 3.依照orderDate
	public List<ProdOrdersBean> selectAllByStatus(String status);//SELECTALL 多筆搜尋 4.依照status
	public List<ProdOrdersBean> selectAllByShippingStatus(String shippingStatus);//SELECTALL 多筆搜尋 5.依照shippingStatus
	
}
