package admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import admin.bean.AdminBean;

@Repository
public class AdminDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    // 使用session獲取管理員信息
    public AdminBean getAdminByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM AdminBean WHERE email = :email", AdminBean.class)
                .setParameter("email", email)
                .uniqueResult();
    }

}