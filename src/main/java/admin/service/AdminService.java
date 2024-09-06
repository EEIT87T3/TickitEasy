package admin.service;

import admin.bean.AdminBean;
import admin.dao.AdminDAO;
import util.PasswordUtil;

public class AdminService {
	private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    public AdminBean login(String email, String password) {
        AdminBean admin = adminDAO.getAdminByEmail(email);
        if (admin != null && PasswordUtil.checkPassword(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }
}
