package member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
// 所有的請求路徑都會以/member開頭
@RequestMapping("/member")
public class MemberLogoutController {

    // 處理GET請求，進行會員登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 檢查session是否存在
        if (session != null) {
            // 移除session中的會員
            session.removeAttribute("member");
            // 使整個session失效
            session.invalidate();
        }
        // 重定向到登錄頁面
        return "member/memberLogin";
    }
}