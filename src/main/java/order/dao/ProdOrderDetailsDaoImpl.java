package order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import order.bean.ProdOrderDetailsBean;
import order.bean.ProdOrdersBean;
import order.dao.base.ProdOrderDetailsDao;
import util.ConnectionUtil;

public class ProdOrderDetailsDaoImpl implements ProdOrderDetailsDao{
	
	private Session session;
	
	
	public ProdOrderDetailsDaoImpl(Session session) {
		this.session = session;
	}
	

	public ProdOrderDetailsBean prodOrderDetailsSelect(int prodOrderIDParameter){ //查詢單筆 prodOrderDetailID搜尋
		ProdOrderDetailsBean prodOrderDetailsBean = session.get(ProdOrderDetailsBean.class, prodOrderIDParameter);
		
		return prodOrderDetailsBean;
	}

	public List<ProdOrderDetailsBean> prodOrderDetailsSelectAll(ProdOrdersBean prodOrderIDOrder) { //查詢多筆
		return session.createQuery("from ProdOrderDetailsBean Where prodOrderID = :prodOrderID").setParameter("prodOrderID", prodOrderIDOrder).list();
	}

}
