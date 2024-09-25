package admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.bean.AdminBean;
import admin.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
// 所有的請求路徑都會以/admin開頭
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;
    
 // 新增：處理顯示登入頁面的 GET 請求
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/adminLogin"; 
    }

    // 處理POST請求，進行管理員登入
    @PostMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    @ResponseBody // 直接返回數據
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        try {
            // 嘗試登入
            AdminBean admin = adminService.login(email, password);
            if (admin != null) {
                // 登入成功，將管理員存入session
                session.setAttribute("admin", admin);
                return "success";
            } else {
                // 登入失敗
                return "無效的電子郵件或密碼";
            }
        } catch (Exception e) {
            // 發生異常
            e.printStackTrace();
            return e.getMessage();
        }
    }
}