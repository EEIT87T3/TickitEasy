package member.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import member.bean.MemberBean;
import util.HibernateUtil;

public class MemberDAO {
    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());//取得Logger
    private SessionFactory sessionFactory;//宣告SessionFactory

    //建構子
    public MemberDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();//取得SessionFactory
    }
    
    //根據電子郵件查詢會員
    public MemberBean getMemberByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM MemberBean WHERE email = :email", MemberBean.class)
                .setParameter("email", email)
                .uniqueResult();
    }
    
    //新增會員 在添加之前對密碼進行哈希處理。
    public int addMember(MemberBean member) {
        Session session = sessionFactory.getCurrentSession();
        session.save(member);
        return member.getMemberID();
    }
    
    //更新會員
    public void updateMember(MemberBean member) {
        Session session = sessionFactory.getCurrentSession();
        session.update(member);
    }
    
    //移除頭貼
    public void removeProfilePic(int memberId) {
        Session session = sessionFactory.getCurrentSession();
        MemberBean member = session.get(MemberBean.class, memberId);
        if (member != null) {
            member.setProfilePic(null);
            session.update(member);
        }
    }
    
    //根據會員ID取得會員
    public MemberBean getMemberById(int memberID) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(MemberBean.class, memberID);
    }
    
    //取得所有會員
    public List<MemberBean> getAllMembers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM MemberBean", MemberBean.class).list();
    }
    
    //修改狀態
    public void updateMemberStatus(int memberId, String newStatus) {
        Session session = sessionFactory.getCurrentSession();
        MemberBean member = session.get(MemberBean.class, memberId);
        if (member != null) {
            member.setStatus(newStatus);
            session.update(member);
        }
    }
    
    //取得註冊趨勢
    public Map<String, Integer> getRegistrationTrend() {
        Map<String, Integer> trend = new HashMap<>();
        Session session = sessionFactory.getCurrentSession();
        //取得6個月前的註冊日期
        String hql = "SELECT MONTH(m.registerDate) as month, COUNT(*) as count " +	//hql語法
                     "FROM MemberBean m " +	//資料表
                     "WHERE m.registerDate >= :sixMonthsAgo " +	//條件
                     "GROUP BY MONTH(m.registerDate) " +	//分組
                     "ORDER BY MONTH(m.registerDate)";	//排序
		//執行查詢
        List<Object[]> results = session.createQuery(hql, Object[].class)
                .setParameter("sixMonthsAgo", LocalDate.now().minusMonths(6))
                .list();
        //將結果轉換為Map
        for (Object[] result : results) {
            trend.put(result[0].toString(), ((Long)result[1]).intValue());
        }
        return trend;
    }
}