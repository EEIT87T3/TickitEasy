package admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.bean.AdminBean;
import admin.dao.AdminDAO;
import util.PasswordUtil;

@Service
@Transactional
public class AdminService {
    
    @Autowired
    private AdminDAO adminDAO;

    // 管理員登入
    public AdminBean login(String email, String password) {
        try {
            AdminBean admin = adminDAO.getAdminByEmail(email);
            if (admin != null && PasswordUtil.checkPassword(password, admin.getPassword())) {
                return admin;
            }
        } catch (Exception e) {
            // 記錄錯誤，讓filter處理
            e.printStackTrace();
        }
        return null;
    }

}