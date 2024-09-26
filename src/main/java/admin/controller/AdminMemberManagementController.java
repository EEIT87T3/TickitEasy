package admin.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import member.bean.MemberBean;
import member.service.MemberService;

@Controller
// 所有的請求路徑都會以/admin/memberManagement開頭
@RequestMapping("/admin/memberManagement")
public class AdminMemberManagementController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private String uploadDirectory; // 注入上傳目錄

    // 處理GET請求，顯示所有會員
    @GetMapping
    public String listMembers(Model model) {
        List<MemberBean> members = memberService.getAllMembers();
        // 將會員列表添加到model
        model.addAttribute("members", members);
        // 返回view
        return "admin/memberManagement";
    }

    // 處理GET請求，顯示編輯會員頁面
    @GetMapping("/edit")
    public String editMember(@RequestParam("memberId") int memberId, Model model) {
        MemberBean member = memberService.getMemberById(memberId);
        // 將會員資料添加到model
        model.addAttribute("member", member);
        // 返回編輯會員的view
        return "admin/editMember";
    }

    // 處理POST請求，更新會員資料
    @PostMapping("/update")
    public String updateMember(@RequestParam("memberId") int memberId,
                               @RequestParam("name") String name,
                               @RequestParam("nickname") String nickname,
                               @RequestParam("birthDate") String birthDate,
                               @RequestParam("phone") String phone,
                               @RequestParam(value = "profilePic", required = false) MultipartFile profilePic,
                               Model model) {
        try {
            MemberBean member = memberService.getMemberById(memberId);
            member.setName(name);
            member.setNickname(nickname);
            member.setBirthDate(LocalDate.parse(birthDate));
            member.setPhone(phone);

            // 如果上傳了新的頭像，則處理頭像上傳
            if (profilePic != null && !profilePic.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + getFileExtension(profilePic.getOriginalFilename());
                File uploadPath = new File(uploadDirectory, fileName);
                profilePic.transferTo(uploadPath);
                
                // 設置相對路徑，以便於在不同環境中使用
                String relativePath = "uploads" + File.separator + fileName;
                member.setProfilePic(relativePath);
            }

            memberService.updateMember(member);
            return "redirect:/admin/memberManagement";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/editMember";
        }
    }
    

    // 處理POST請求，更新會員狀態
    @PostMapping("/updateStatus")
    @ResponseBody // 直接返回數據
    public String updateMemberStatus(@RequestParam("memberId") int memberId,
                                     @RequestParam("status") String newStatus) {
        memberService.updateMemberStatus(memberId, newStatus);
        // 返回JSON格式的成功響應
        return "{\"success\": true}";
    }

    // 處理POST請求，移除會員頭像
    @PostMapping("/removeProfilePic")
    @ResponseBody
    public String removeProfilePic(@RequestParam("memberId") int memberId) {
        memberService.removeProfilePic(memberId);
        // 返回JSON格式的成功響應
        return "{\"success\": true}";
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}