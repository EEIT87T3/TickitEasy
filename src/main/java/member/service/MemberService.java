package member.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import member.bean.MemberBean;
import member.dao.MemberDAO;
import util.PasswordUtil;

public class MemberService {
    private MemberDAO memberDAO;
    private static final Logger LOGGER = Logger.getLogger(MemberService.class.getName());
    
    public MemberService() {
        this.memberDAO = new MemberDAO();
    }
    
    //取得所有會員
    public List<MemberBean> getAllMembers() {
        return memberDAO.getAllMembers();
    }
    
    //根據會員ID取得會員
    public MemberBean getMemberById(int memberId) {
        return memberDAO.getMemberById(memberId);
    }
    
    //根據電子郵件查詢會員
    public MemberBean getMemberByEmail(String email) {
        return memberDAO.getMemberByEmail(email);
    }
    
    //新增會員 在添加之前對密碼進行哈希處理。
    public int addMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        member.setStatus("未驗證");
        member.setRegisterDate(LocalDate.now());
        return memberDAO.addMember(member);
    }
    
    //更新會員
    public void updateMember(MemberBean member) {
        memberDAO.updateMember(member);
    }
    
    //登入
    public MemberBean login(String email, String password) {
        MemberBean member = memberDAO.getMemberByEmail(email);
        if (member != null && PasswordUtil.checkPassword(password, member.getPassword())) {
            return member;
        }
        return null;
    }
    
    //修改狀態
    public void updateMemberStatus(int memberId, String newStatus) {
        memberDAO.updateMemberStatus(memberId, newStatus);
    }
    
    //移除頭貼
    public void removeProfilePic(int memberId) {
        MemberBean member = getMemberById(memberId);
        if (member != null && member.hasCustomProfilePic()) {
            member.setProfilePic(null);
            updateMember(member);
        }
    }
    
    //取得註冊趨勢
    public Map<String, Object> getMemberStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("registrationTrend", memberDAO.getRegistrationTrend());
        //取得年齡分佈
        Map<String, Integer> ageDistribution = new HashMap<>();
        for (MemberBean member : memberDAO.getAllMembers()) {
            if (member.getBirthDate() != null) {
                int age = Period.between(member.getBirthDate(), LocalDate.now()).getYears();
                String ageGroup = getAgeGroup(age);
                ageDistribution.put(ageGroup, ageDistribution.getOrDefault(ageGroup, 0) + 1);
            }
        }
        statistics.put("ageDistribution", ageDistribution);

        return statistics;
    }
    
    private String getAgeGroup(int age) {
        if (age < 18) return "未滿18歲";
        if (age < 25) return "18-24歲";
        if (age < 35) return "25-34歲";
        if (age < 45) return "35-44歲";
        if (age < 55) return "45-54歲";
        return "55歲以上";
    }

    //修改密碼
    public void changePassword(int memberId, String newPassword) {
        MemberBean member = getMemberById(memberId);
        if (member != null) {
            member.setPassword(PasswordUtil.hashPassword(newPassword));
            updateMember(member);
        }
    }
    
    //檢查電子郵件是否已經被註冊
    public boolean isEmailExists(String email) {
        return memberDAO.getMemberByEmail(email) != null;
    }
    
    //註冊
    public int registerMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        member.setStatus("已驗證");
        member.setRegisterDate(LocalDate.now());
        return memberDAO.addMember(member);
    }
    
    //重設密碼（未實現）
    public void resetPassword(String email) {
        MemberBean member = getMemberByEmail(email);
        if (member != null) {
            String newPassword = generateRandomPassword();
            member.setPassword(PasswordUtil.hashPassword(newPassword));
            updateMember(member);
            // TODO: 發送包含新密碼的郵件給用戶
        }
    }
    //隨機生成密碼(未實現)
    private String generateRandomPassword() {
        // TODO: 實現隨機密碼生成邏輯
        return "tempPassword123";
    }
}