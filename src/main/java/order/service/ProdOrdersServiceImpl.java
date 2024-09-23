package order.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import order.bean.ProdOrdersBean;
import order.dao.ProdOrdersDaoImpl;
import order.dao.base.ProdOrdersDao;
import order.service.base.*;

@Service
@Transactional
public class ProdOrdersServiceImpl implements ProdOrdersService{
	
	
	public ProdOrdersDao prodOrdersDAO;
	
	@Autowired
	public ProdOrdersServiceImpl(ProdOrdersDao prodOrdersDAO) {
		this.prodOrdersDAO = prodOrdersDAO;
	}
	
	public ProdOrdersBean add(ProdOrdersBean prodOrderBean) {
		int newID = prodOrdersDAO.prodOrderAdd(prodOrderBean); //獲取自增長的主鍵

		return prodOrdersDAO.prodOrderSelect(newID);
	}

	public ProdOrdersBean delete(int prodOrderID) {
		ProdOrdersBean prodOrderBeanNew = prodOrdersDAO.prodOrderSelect(prodOrderID);
		prodOrdersDAO.prodOrderDelete(prodOrderID);

		return prodOrderBeanNew;
	}

	public ProdOrdersBean update(ProdOrdersBean prodOrderBean) {
		prodOrdersDAO.prodOrderUpdate(prodOrderBean);

		return prodOrdersDAO.prodOrderSelect(prodOrderBean.getProdOrderID());
	}

	public ProdOrdersBean selectByprodOrderID(int prodOrderID) {
		return prodOrdersDAO.prodOrderSelect(prodOrderID);
	}

	public List<ProdOrdersBean> selectAllByMemberId(int memberID) {
		return prodOrdersDAO.prodOrderSelectAllByMemberId(memberID);
	}

	@Override
	public List<ProdOrdersBean> selectAll() {
		List<ProdOrdersBean> prodOrderSelectAll = prodOrdersDAO.prodOrderSelectAll();
		return prodOrderSelectAll;
	}

	@Override
	public ProdOrdersBean selectByShippingId(int shippingID) {
		return null;
	}

	@Override
	public List<ProdOrdersBean> selectAllByOrderDate(Date orderDate) {
		return null;
	}

	@Override
	public List<ProdOrdersBean> selectAllByStatus(String status) {
		return null;
	}

	@Override
	public List<ProdOrdersBean> selectAllByShippingStatus(String shippingStatus) {
		return null;
	}

}
