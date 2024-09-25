package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import member.bean.MemberBean;
import member.service.MemberService;

@Controller
// 所有的請求路徑都會以/member開頭
@RequestMapping("/member")
public class MemberLoginController {

    // 自動注入MemberService
    @Autowired
    private MemberService memberService;

	// 新增：處理顯示登入頁面的 GET 請求
	@GetMapping("/login")
	public String showLoginPage() {
		return "member/memberLogin";
	}
	@GetMapping("/dashboard")
	public String showDashboardPage() {
		return "member/memberDashboard";
	}

    // 處理POST請求，進行會員登入
    @PostMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    @ResponseBody // 直接返回數據
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // 登入驗證
        MemberBean member = memberService.login(email, password);
        if (member != null) {
            // 登入成功，將會員存入session
            session.setAttribute("member", member);
            return "success";
        } else {
            // 登錄失敗
            return "無效的電子郵件或密碼";
        }
    }
}