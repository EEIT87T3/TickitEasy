package event.test;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import event.object.po.TicketTypesPO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.HibernateUtil;

@WebServlet ("/event/EventTest")
public class EventTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		try {
//			EventsPO eventsPO = new EventsPO();
//			eventsPO.setEventName("Hibernate測試活動5");
//			
//			EventTypesPO eventType = session.createQuery("FROM EventTypesPO WHERE eventType = :eventType", EventTypesPO.class)
//					.setParameter("eventType", "other")
//					.uniqueResult();
//			eventsPO.setEventType(eventType);
//			
//			System.out.println("eventName: " + eventsPO.getEventName());
//			System.out.println("eventType: " + eventsPO.getEventType());
//			
//			session.persist(eventsPO);
			
			
			TicketTypesPO ticketTypesPO = session.createQuery("FROM TicketTypesPO WHERE ticketTypeID = :ticketTypeID", TicketTypesPO.class)
					.setParameter("ticketTypeID", 29)
					.uniqueResult();
			
			System.out.println("該場次的票種數量：" + ticketTypesPO.getSession().getTicketTypes().size());
			
			session.getTransaction().commit();
			System.out.println("成功執行 EventTest");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("發生 Exception");
			e.printStackTrace();
		}


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
