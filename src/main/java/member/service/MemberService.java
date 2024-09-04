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
    // 取得所有會員
    public List<MemberBean> getAllMembers() {
        return memberDAO.getAllMembers();
    }
    //根據會員ID取得會員
    public MemberBean getMemberById(int memberId) {
        return memberDAO.getMemberById(memberId);
    }
    // 根據電子郵件查詢會員
    public MemberBean getMemberByEmail(String email) {
        return memberDAO.getMemberByEmail(email);
    }
    //新增會員 在添加之前對密碼進行哈希處理。
    public int addMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));//實現加密的邏輯
        member.setStatus("未驗證"); // 設置初始狀態
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
    //移除頭貼
    public void removeProfilePic(int memberId) {
        MemberBean member = getMemberById(memberId);
        if (member != null && member.hasCustomProfilePic()) {
            member.setProfilePic(null); // 將頭貼設置為 null，這樣會使用預設頭貼
            updateMember(member);
        }
    }
    // 取得會員統計資料
    public Map<String, Object> getMemberStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // 取得註冊趨勢DAO
        statistics.put("registrationTrend", memberDAO.getRegistrationTrend());


        // 取得年齡分佈
        Map<String, Integer> ageDistribution = new HashMap<>();
        for (MemberBean member : memberDAO.getAllMembers()) {
            if (member.getBirthDate() != null) {// 避免沒有生日的會員
                int age = Period.between(member.getBirthDate(), LocalDate.now()).getYears();// 計算年齡
                String ageGroup = getAgeGroup(age);// 計算年齡組
                ageDistribution.put(ageGroup, ageDistribution.getOrDefault(ageGroup, 0) + 1);// 計算年齡分佈
            }
        }
        statistics.put("ageDistribution", ageDistribution);// 將年齡分佈存入統計資料

        return statistics;// 返回統計資料
    }
    // 計算年齡分佈
    private String getAgeGroup(int age) {
        if (age < 18) {
			return "未滿18歲";
		}
        if (age < 25) {
			return "18-24歲";
		}
        if (age < 35) {
			return "25-34歲";
		}
        if (age < 45) {
			return "35-44歲";
		}
        if (age < 55) {
			return "45-54歲";
		}
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
    // 驗證電子郵件是否存在
    public boolean isEmailExists(String email) {
        return memberDAO.getMemberByEmail(email) != null;
    }
    public int registerMember(MemberBean member) {
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        member.setStatus("已驗證"); // 由於沒有郵箱驗證，我們直接設置為已驗證
        return memberDAO.addMember(member);
    }
    // 重設密碼
    public void resetPassword(String email) {
        // 這裡應該實現發送重置密碼郵件的邏輯
        MemberBean member = getMemberByEmail(email);
        if (member != null) {
            String newPassword = generateRandomPassword(); // 生成隨機密碼
            member.setPassword(PasswordUtil.hashPassword(newPassword));
            updateMember(member);
            // TODO: 發送包含新密碼的郵件給用戶
        }
    }

    private String generateRandomPassword() {
        // TODO: 實現隨機密碼生成邏輯
        return "tempPassword123";
    }
}