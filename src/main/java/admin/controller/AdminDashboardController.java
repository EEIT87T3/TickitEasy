package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 所有的請求路徑都會以/admin開頭
@RequestMapping("/admin")
public class AdminDashboardController {

    // 處理GET請求，顯示管理員首頁
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/adminDashboard";
    }
}