package member.dao;


import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import member.bean.MemberBean;

import util.HibernateUtil;

public class MemberDAO {
	private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());
    private SessionFactory sessionFactory;

    public MemberDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    // 取得所有會員
    public MemberBean getMemberByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MemberBean WHERE email = :email", MemberBean.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
    // 新增會員
    public int addMember(MemberBean member) {
        try (Session session = sessionFactory.openSession()) {
        	Transaction transaction = session.beginTransaction();
            try {
                if (member.getRegisterDate() == null) {
                    member.setRegisterDate(LocalDate.now());
                }
                session.save(member);
                transaction.commit();
                return member.getMemberID();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                return -1;
            }
        }
    }
    // 更新會員
    public void updateMember(MemberBean member) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(member);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
    // 移除頭貼
    public void removeProfilePic(int memberId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                MemberBean member = session.get(MemberBean.class, memberId);
                if (member != null) {
                    member.setProfilePic(null);
                    session.update(member);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
    // 根據會員ID取得會員
    public MemberBean getMemberById(int memberID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MemberBean.class, memberID);
        }
    }
    // 取得所有會員
    public List<MemberBean> getAllMembers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MemberBean", MemberBean.class).list();
        }
    }
    // 修改狀態
    public void updateMemberStatus(int memberId, String newStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                MemberBean member = session.get(MemberBean.class, memberId);
                if (member != null) {
                    member.setStatus(newStatus);
                    session.update(member);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
    //取得註冊統計
    public Map<String, Integer> getRegistrationTrend() {
        Map<String, Integer> trend = new HashMap<>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT MONTH(m.registerDate) as month, COUNT(*) as count " +
                         "FROM MemberBean m " +
                         "WHERE m.registerDate >= :sixMonthsAgo " +
                         "GROUP BY MONTH(m.registerDate) " +
                         "ORDER BY MONTH(m.registerDate)";
            
            List<Object[]> results = session.createQuery(hql, Object[].class)
                    .setParameter("sixMonthsAgo", LocalDate.now().minusMonths(6))
                    .list();
            
            for (Object[] result : results) {
                trend.put(result[0].toString(), ((Long)result[1]).intValue());
            }
        }
        return trend;
    }


}

