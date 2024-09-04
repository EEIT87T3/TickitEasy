package admin.service;

import admin.bean.AdminBean;
import admin.dao.AdminDAO;
import util.PasswordUtil;

public class AdminService {
	private AdminDAO adminDAO;
	
	public AdminService() {
		this.adminDAO = new AdminDAO();
	}
	//用email和密碼登入
	public AdminBean login(String email, String password) {
		//先檢查是否有此管理員
		AdminBean admin = adminDAO.getAdminByEmail(email);
		//若有則檢查密碼
		if(admin != null && PasswordUtil.checkPassword(password, admin.getPassword())) {
			return admin;
		}
		return null;
	}
}
