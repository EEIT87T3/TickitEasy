package event.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import event.object.po.EventTypesPO;
import event.object.po.EventsPO;
import util.HibernateUtil;

public class CreateEventDAO {
	private SessionFactory sessionFactory;

	public CreateEventDAO() {
		super();
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	/*
	 * method 名稱：createEvent
	 * 用途：新增活動的實際執行 DAO
	 * @param：EventsPO
	 * @return：（無）
	 * Caller：event.service.CreateEventService createEvent()
	*/
	public void createEvent(EventsPO eventsPO) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		try {
			session.persist(eventsPO);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		    throw e;
		}
	}
	
	/*
	 * method 名稱：selectOneEventType
	 * 用途：根據 eventType（英文字串）取得其 PO
	 * @param：String
	 * @return：EventTypesPO
	 * Caller：event.service.CreateEventService createEvent()
	*/
	public EventTypesPO selectOneEventType(String eventType) {
		EventTypesPO resultEventType = null;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		try {
			resultEventType = session.createQuery("FROM EventTypesPO WHERE eventType = :eventType", EventTypesPO.class)
					.setParameter("eventType", eventType)
					.uniqueResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return resultEventType;
	}
}
