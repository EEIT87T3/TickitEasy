package admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import admin.bean.AdminBean;
import util.HibernateUtil;

public class AdminDAO {
    private SessionFactory sessionFactory;

    public AdminDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public AdminBean getAdminByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM AdminBean WHERE email = :email", AdminBean.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}