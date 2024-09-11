package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import order.bean.ProdOrdersBean;
import order.dao.base.ProdOrdersDao;
import util.ConnectionUtil;
import util.HibernateUtil;

//周邊商品的CRUD
public class ProdOrdersDaoImpl implements ProdOrdersDao{
	
	private Session session;
	
	
	public ProdOrdersDaoImpl(Session session) {
		this.session = session;
	}
	
	
	public int prodOrderAdd(ProdOrdersBean prodOrderBean) { //新增

		try {
			ProdOrdersBean prodOrdersBean = session.get(prodOrderBean.getClass(), prodOrderBean.getProdOrderID());
			int prodOrderID = (int) session.save(prodOrderBean);
			
			return prodOrderID;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		throw new RuntimeException("添加資料失敗");
	}

	public void prodOrderDelete(int prodOrderID) { //刪除
		ProdOrdersBean prodOrdersBean = session.get(ProdOrdersBean.class, prodOrderID);
		
		session.remove(prodOrdersBean);
	}

	public void prodOrderUpdate(ProdOrdersBean prodOrderBean) { //修改 之後修改成返回ProductOrderBean 將改完後頁面顯示在JSP
		session.update(prodOrderBean);
	}

	public ProdOrdersBean prodOrderSelect(int prodOrderIDparameter) { //查詢單筆
		
		ProdOrdersBean prodOrdersBean = session.get(ProdOrdersBean.class, prodOrderIDparameter);
		
		return prodOrdersBean;
	}

	public List<ProdOrdersBean> prodOrderSelectAll(int memberIDParameter) { //查詢多筆 未來修改成只顯示該會員的所有訂單顯示
		return session.createQuery("from ProdOrdersBean Where memberID = :memberID")
				.setParameter("memberID", memberIDParameter).list();
		
	}
}
