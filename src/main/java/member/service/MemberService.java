package member.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import member.bean.MemberBean;
import member.dao.MemberDAO;
import util.PasswordUtil;

@Service
@Transactional
public class MemberService {
    
    @Autowired
    private MemberDAO memberDAO;
    
    // 取得所有會員
    public List<MemberBean> getAllMembers() {
        return memberDAO.getAllMembers();
    }
    
    // 根據會員ID取得會員
    public MemberBean getMemberById(int memberId) {
        return memberDAO.getMemberById(memberId);
    }
    
    // 根據電子郵件查詢會員
    public MemberBean getMemberByEmail(String email) {
        return memberDAO.getMemberByEmail(email);
    }
    
    // 新增會員
    public int addMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        member.setStatus("未驗證");
        member.setRegisterDate(LocalDate.now());
        return memberDAO.addMember(member);
    }
    
    // 更新會員
    public void updateMember(MemberBean member) {
        memberDAO.updateMember(member);
    }
    
    // 登入
    public MemberBean login(String email, String password) {
        MemberBean member = memberDAO.getMemberByEmail(email);
        if (member != null && PasswordUtil.checkPassword(password, member.getPassword())) {
            return member;
        }
        return null;
    }
    
    // 修改狀態
    public void updateMemberStatus(int memberId, String newStatus) {
        memberDAO.updateMemberStatus(memberId, newStatus);
    }
    
    // 移除頭貼
    public void removeProfilePic(int memberId) {
        MemberBean member = getMemberById(memberId);
        if (member != null && member.hasCustomProfilePic()) {
            memberDAO.removeProfilePic(memberId);
        }
    }
    
    // 取得會員統計資料
    public Map<String, Object> getMemberStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("registrationTrend", memberDAO.getRegistrationTrend());
        
        Map<String, Integer> ageDistribution = new HashMap<>();
        for (MemberBean member : getAllMembers()) {
            if (member.getBirthDate() != null) {
                int age = Period.between(member.getBirthDate(), LocalDate.now()).getYears();
                String ageGroup = getAgeGroup(age);
                ageDistribution.put(ageGroup, ageDistribution.getOrDefault(ageGroup, 0) + 1);
            }
        }
        statistics.put("ageDistribution", ageDistribution);
        
        return statistics;
    }
    
    // 獲取年齡組別
    private String getAgeGroup(int age) {
        if (age < 18) return "未滿18歲";
        if (age < 25) return "18-24歲";
        if (age < 35) return "25-34歲";
        if (age < 45) return "35-44歲";
        if (age < 55) return "45-54歲";
        return "55歲以上";
    }
    
    // 修改密碼
    public void changePassword(int memberId, String newPassword) {
        MemberBean member = getMemberById(memberId);
        if (member != null) {
            member.setPassword(PasswordUtil.hashPassword(newPassword));
            updateMember(member);
        }
    }
    
    // 檢查電子郵件是否已經被註冊
    public boolean isEmailExists(String email) {
        return getMemberByEmail(email) != null;
    }
    
    // 註冊新會員
    public int registerMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        member.setStatus("已驗證");
        member.setRegisterDate(LocalDate.now());
        return memberDAO.addMember(member);
    }
}