package member.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import member.bean.MemberBean;

@Repository
public class MemberDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    // 根據電子郵件查詢會員
    public MemberBean getMemberByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<MemberBean> query = session.createQuery("FROM MemberBean WHERE email = :email", MemberBean.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }
    
    // 新增會員
    public int addMember(MemberBean member) {
        Session session = sessionFactory.getCurrentSession();
        session.save(member);
        return member.getMemberID();
    }
    
    // 更新會員
    public void updateMember(MemberBean member) {
        Session session = sessionFactory.getCurrentSession();
        session.update(member);
    }
    
    // 移除頭貼
    public void removeProfilePic(int memberId) {
        Session session = sessionFactory.getCurrentSession();
        MemberBean member = session.get(MemberBean.class, memberId);
        if (member != null) {
            member.setProfilePic(null);
            session.update(member);
        }
    }
    
    // 根據會員ID取得會員
    public MemberBean getMemberById(int memberID) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(MemberBean.class, memberID);
    }
    
    // 取得所有會員
    public List<MemberBean> getAllMembers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM MemberBean", MemberBean.class).list();
    }
    
    // 修改狀態
    public void updateMemberStatus(int memberId, String newStatus) {
        Session session = sessionFactory.getCurrentSession();
        MemberBean member = session.get(MemberBean.class, memberId);
        if (member != null) {
            member.setStatus(newStatus);
            session.update(member);
        }
    }
    
    // 取得註冊趨勢
    public Map<String, Integer> getRegistrationTrend() {
        Map<String, Integer> trend = new HashMap<>();
        Session session = sessionFactory.getCurrentSession();
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
        return trend;
    }
}