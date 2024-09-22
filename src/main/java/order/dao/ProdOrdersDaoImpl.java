package order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import order.bean.ProdOrdersBean;
import order.dao.base.ProdOrdersDao;
import util.ConnectionUtil;
import util.HibernateUtil;

@Repository
public class ProdOrdersDaoImpl implements ProdOrdersDao{
	
	 private final SessionFactory sessionFactory;

    @Autowired
    public ProdOrdersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	public int prodOrderAdd(ProdOrdersBean prodOrderBean) { //INSERT 新增
		Session session = getSession();
		try {
			ProdOrdersBean prodOrdersBean = session.get(prodOrderBean.getClass(), prodOrderBean.getProdOrderID());
			int prodOrderID = (int) session.save(prodOrderBean);
			
			return prodOrderID;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		throw new RuntimeException("添加資料失敗");
	}

	public void prodOrderDelete(int prodOrderID) { //DELETE 刪除
		Session session = getSession();
		ProdOrdersBean prodOrdersBean = session.get(ProdOrdersBean.class, prodOrderID);
		
		session.remove(prodOrdersBean);
	}

	public void prodOrderUpdate(ProdOrdersBean prodOrderBean) { //UPDATE 修改
		Session session = getSession();
		session.update(prodOrderBean);
	}

	public ProdOrdersBean prodOrderSelect(int prodOrderIDparameter) { //SELECT 單筆搜尋 1.依照prodOrderID
		Session session = getSession();
		ProdOrdersBean prodOrdersBean = session.get(ProdOrdersBean.class, prodOrderIDparameter);
		
		return prodOrdersBean;
	}
	
	
	@Override
	public ProdOrdersBean prodOrderSelectByShippingId(int shippingID) { //SELECT 單筆搜尋 2.依照shippingID
		return null;
	}

	@Override
	public List<ProdOrdersBean> prodOrderSelectAll() { //SELECTALL 多筆搜尋 1.空參 (搜尋該會員全部訂單 SELECTALL)
		Session session = getSession();
		return session.createQuery("from ProdOrdersBean").list();
	}
	
	public List<ProdOrdersBean> prodOrderSelectAllByMemberId(int memberIDParameter) { //SELECTALL 多筆搜尋 2.依照memberID
		Session session = getSession();
		return session.createQuery("from ProdOrdersBean Where memberID = :memberID")
				.setParameter("memberID", memberIDParameter).list();
		
	}

	

	@Override
	public List<ProdOrdersBean> prodOrderSelectAllByOrderDate(Date orderDate) { //SELECTALL 多筆搜尋 3.依照orderDate
		return null;
	}

	@Override
	public List<ProdOrdersBean> prodOrderSelectAllByStatus(String status) { //SELECTALL 多筆搜尋 4.依照status
		return null;
	}

	@Override
	public List<ProdOrdersBean> prodOrderSelectAllByShippingStatus(String shippingStatus) { //SELECTALL 多筆搜尋 5.依照shippingStatus
		return null;
	}
}
