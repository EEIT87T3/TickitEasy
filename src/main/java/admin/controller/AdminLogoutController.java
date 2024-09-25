package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
// 所有的請求路徑都會以/admin開頭
@RequestMapping("/admin")
public class AdminLogoutController {

    // 處理GET請求，進行管理員登出
    @GetMapping("/adminLogout")
    public String logout(HttpSession session) {
        // 使session失效，相當於登出
        session.invalidate();
        // 重定向到登入頁面
        return "admin/adminLogin";
    }
}