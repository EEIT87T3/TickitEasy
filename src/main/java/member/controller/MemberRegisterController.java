package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import member.bean.MemberBean;
import member.service.MemberService;

@Controller
// 所有的請求路徑都會以/member開頭
@RequestMapping("/member")
public class MemberRegisterController {

    // 自動注入MemberService
    @Autowired
    private MemberService memberService;

    // 處理POST請求，進行會員註冊
    @PostMapping("/register")
    public String register(@RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("nickname") String nickname,
                           @RequestParam("birthDate") String birthDateStr,
                           @RequestParam("phone") String phone) {
        
        // 創建新的MemberBean對象
        MemberBean newMember = new MemberBean();
        newMember.setEmail(email);
        newMember.setPassword(password);
        newMember.setName(name);
        newMember.setNickname(nickname);
        newMember.setBirthDate(LocalDate.parse(birthDateStr));
        newMember.setPhone(phone);

        // 調用服務進行註冊
        int result = memberService.registerMember(newMember);

        if (result > 0) {
            // 註冊成功，重定向到成功頁面
            return "member/registerSuccess";
        } else {
            // 註冊失敗，重定向到失敗頁面
            return "member/registerFailed";
        }
    }
}