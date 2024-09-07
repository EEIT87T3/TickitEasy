package event.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import event.object.po.TicketTypesPO;
import util.HibernateUtil;

public class TicketTypesDAO {
	private SessionFactory sessionFactory;

	public TicketTypesDAO() {
		super();
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	/*
	 * method 名稱：readAll
	 * 用途：查詢所有票種的實際執行 DAO
	 * @param：（無）
	 * @return：List<TicketTypesPO>
	 * Caller：webapp/event/ReadAllTicketTypes.jsp
	*/
	public List<TicketTypesPO> readAll() {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("FROM TicketTypesPO", TicketTypesPO.class).list();
	}

	/*
	 * method 名稱：selectOneTicketTypeById
	 * 用途：以 ID 查詢單一票種
	 * @param：Integer（ticketTypeID）
	 * @return：TicketTypesPO
	 * Caller：java.event.controller.ReadOneTicketType doGet()
	*/
	public TicketTypesPO selectOneTicketTypeById(Integer ticketTypeID) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("FROM TicketTypesPO WHERE ticketTypeID = :ticketTypeID", TicketTypesPO.class)
				.setParameter("ticketTypeID", ticketTypeID)
				.uniqueResult();
	}

	/*
	 * method 名稱：selectIfOnlyTicketTypeByDTO
	 * 用途：以 TicketTypesPO 物件查詢該票種是否為該場次的唯一票種
	 * @param：TicketTypesPO（要查詢的票種物件）
	 * @return：Boolean
	 * Caller：java.event.controller.ReadOneTicketType doGet()
	*/
	public Boolean selectIfOnlyTicketTypeByPO(TicketTypesPO ticketType) {
		Boolean result = null;
			
		if (ticketType.getSession().getTicketTypes().size() == 1) {
			result = true;
		} else if (ticketType.getSession().getTicketTypes().size() == 0) {
			result = null;
		} else {
			result = false;
		}
		
		return result;
	}

	/*
	 * method 名稱：deleteTicketTypeById
	 * 用途：以 ID 刪除單一票種的資料庫互動
	 * @param：Integer（ticketTypeID）
	 * @return：Boolean
	 * Caller：webapp/event/DeleteTicketType doPost()
	*/
	public Boolean deleteTicketTypeById(Integer ticketTypeID) {
		Boolean result = null;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			TicketTypesPO ticketTypesPO = session.createQuery("FROM TicketTypesPO WHERE ticketTypeID = :ticketTypeID", TicketTypesPO.class)
					.setParameter("ticketTypeID", ticketTypeID)
					.uniqueResult();
			
			session.remove(ticketTypesPO);
			
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * method 名稱：updateTicketTypeById
	 * 用途：以 ID 修改票種得資料庫互動
	 * @param：TicketTypesPO
	 * @return：Boolean（執行結果）
	 * Caller：java.event.service.UpdateTicketTypeService updateTicketType()
	 * 備註：throws SQLException
	*/
	public Boolean updateTicketTypeById(TicketTypesPO updatedTicketTypesPO) throws SQLException {
		Boolean result = null;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			TicketTypesPO ticketTypesPO = session.createQuery("FROM TicketTypesPO WHERE ticketTypeID = :ticketTypeID", TicketTypesPO.class)
					.setParameter("ticketTypeID", updatedTicketTypesPO.getTicketTypeID())
					.uniqueResult();
			updatedTicketTypesPO.setTicketTypeNo(ticketTypesPO.getTicketTypeNo());
			updatedTicketTypesPO.setSession(ticketTypesPO.getSession());
			session.merge(updatedTicketTypesPO);
			
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}
