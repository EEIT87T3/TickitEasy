package admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import member.bean.MemberBean;
import member.service.MemberService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Servlet implementation class AdminMemberManagementServlet
 */
@WebServlet("/admin/memberManagement")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 5,   // 5 MB
	    maxRequestSize = 1024 * 1024 * 5 * 5 // 25 MB
	)
public class AdminMemberManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        memberService = new MemberService();
	    }
	 	//處理GET請求
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");//獲取要執行的動作
	        if ("edit".equals(action)) {//編輯會員
	            int memberId = Integer.parseInt(request.getParameter("memberId"));//獲取要編輯的會員ID
	            MemberBean member = memberService.getMemberById(memberId);//獲取要編輯的會員
	            request.setAttribute("member", member);
	            request.getRequestDispatcher("/admin/editMember.jsp").forward(request, response);//轉發到編輯頁面
	        } else {
	            List<MemberBean> members = memberService.getAllMembers();//獲取所有會員
	            request.setAttribute("members", members);
	            request.getRequestDispatcher("/admin/memberManagement.jsp").forward(request, response);//轉發到會員管理頁面
	        }
	    }
	    //處理POST請求
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");//獲取要執行的動作
	        if ("update".equals(action)) {//更新會員
	            updateMember(request, response);
	        } else if ("updateStatus".equals(action)) {
	            updateMemberStatus(request, response);
	        } else if ("removeProfilePic".equals(action)) {
	            removeProfilePic(request, response);
	        }
	    }
	    //更新會員
	    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            int memberId = Integer.parseInt(request.getParameter("memberId"));
	            String name = request.getParameter("name");
	            String nickname = request.getParameter("nickname");
	            LocalDate birthDate = null;
	            if (request.getParameter("birthDate") != null && !request.getParameter("birthDate").isEmpty()) {
	                birthDate = LocalDate.parse(request.getParameter("birthDate"));
	            }
	            String phone = request.getParameter("phone");

	            if (name == null || name.trim().isEmpty()) {
	                throw new IllegalArgumentException("姓名不能為空");
	            }

	            MemberBean member = memberService.getMemberById(memberId);
	            if (member == null) {
	                throw new IllegalArgumentException("無效的會員ID");
	            }

	            member.setName(name);
	            member.setNickname(nickname);
	            member.setBirthDate(birthDate);
	            member.setPhone(phone);
	            //上傳檔案
	            Part filePart = request.getPart("profilePic");
	            if (filePart != null && filePart.getSize() > 0) {
	                if (filePart.getSize() > 5 * 1024 * 1024) {
	                    throw new IllegalArgumentException("文件大小不能超過5MB");
	                }

	                String contentType = filePart.getContentType();
	                if (!contentType.startsWith("image/")) {
	                    throw new IllegalArgumentException("只能上傳圖片文件");
	                }
	                
	                String fileName = UUID.randomUUID().toString() + getFileExtension(filePart.getSubmittedFileName());
	                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
	                File uploadDir = new File(uploadPath);
	                if (!uploadDir.exists()) uploadDir.mkdir();
	                
	                String filePath = uploadPath + File.separator + fileName;
	                filePart.write(filePath);
	                
	                member.setProfilePic("uploads" + File.separator + fileName);
	            }

	            memberService.updateMember(member);
	            response.sendRedirect(request.getContextPath() + "/admin/memberManagement");
	        } catch (Exception e) {
	            request.setAttribute("errorMessage", e.getMessage());
	            request.getRequestDispatcher("/admin/editMember.jsp").forward(request, response);
	        }
	    }
	    //更新會員狀態
	    private void updateMemberStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int memberId = Integer.parseInt(request.getParameter("memberId"));
	        String newStatus = request.getParameter("status");
	        memberService.updateMemberStatus(memberId, newStatus);
	        response.setContentType("application/json");
	        response.getWriter().write("{\"success\": true}");
	    }
	    //移除會員頭像
	    private void removeProfilePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int memberId = Integer.parseInt(request.getParameter("memberId"));
	        memberService.removeProfilePic(memberId);
	        response.setContentType("application/json");
	        response.getWriter().write("{\"success\": true}");
	    }
	    //獲取檔案副檔名
	    private String getFileExtension(String fileName) {
	        if (fileName == null) return "";
	        int lastIndexOf = fileName.lastIndexOf(".");
	        if (lastIndexOf == -1) return "";
	        return fileName.substring(lastIndexOf);
	    }
	}