package event.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import event.object.po.EventTypesPO;
import event.object.po.EventsPO;
import util.HibernateUtil;

@Repository
public class CreateEventDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * method 名稱：createEvent
	 * 用途：新增活動的實際執行 DAO
	 * @param：EventsPO
	 * @return：（無）
	 * Caller：event.service.CreateEventService createEvent()
	*/
	public void createEvent(EventsPO eventsPO) {
		Session session = sessionFactory.getCurrentSession();

		session.persist(eventsPO);
	}
	
	/*
	 * method 名稱：selectOneEventType
	 * 用途：根據 eventType（英文字串）取得其 PO
	 * @param：String
	 * @return：EventTypesPO
	 * Caller：event.service.CreateEventService createEvent()
	*/
	public EventTypesPO selectOneEventType(String eventType) {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("FROM EventTypesPO WHERE eventType = :eventType", EventTypesPO.class)
				.setParameter("eventType", eventType)
				.uniqueResult();
	}
}
