package cwdfunding.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cwdfunding.bean.FundProjBean;
import cwdfunding.service.*;

import java.io.File;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/FundProjs")
public class FundProjController{
	
	@Autowired
	private FundProjService fundProjService;
	

	/* 查詢全部 */
	@GetMapping
	private String get(Model model){

		List<FundProjBean> projs = fundProjService.selectAll();
		model.addAttribute("projs", projs);
		return "GetAllFundProj";
	}
	
	/* 新增資料 */
	@PostMapping("/insert")
	private String insert(
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("startDate") String startDateStr,
			@RequestParam("endDate") String endDateStr,
			@RequestParam("targetAmount") String targetAmount,
			@RequestParam("currentAmount") String currentAmount,
			@RequestParam("threshold") String threshold,
			@RequestParam("postponeDate") String postponeDateStr,
			@RequestParam("category") String category,
			@RequestParam("image") MultipartFile image,
			HttpServletRequest request
			) throws IllegalStateException, IOException{
		FundProjBean proj = new FundProjBean();
		// 處理圖片（存進本端、將檔名寫進資料庫）
        String filename = image.getOriginalFilename();
        proj.setImage(filename);

        //存進本端
        String uploadPath = request.getServletContext().getRealPath("") + "/WEB-INF/resources/images";
        System.out.println("uploadPath:"+uploadPath);
        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
        }
        File dest = new File(uploadPath + File.separator + filename);
        image.transferTo(dest);
        
        // 將請求中的日期先格式化再轉換成LocalDateTime
        // 定義datetime-local格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // 將字符串轉換為LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, formatter);
        LocalDateTime postponeDateTime = LocalDateTime.parse(postponeDateStr, formatter);
        
        // 設定FundProjBean屬性，日期轉成.sql.TimeStamp
        proj.setTitle(title);
        proj.setDescription(description);
        proj.setStartDate(Timestamp.valueOf(startDateTime));
        proj.setEndDate(Timestamp.valueOf(endDateTime));
        proj.setTargetAmount(targetAmount);
        proj.setCurrentAmount(currentAmount);
        proj.setThreshold(threshold);
        proj.setPostponeDate(Timestamp.valueOf(postponeDateTime));
        proj.setCategory(category);
        // 執行請求
        fundProjService.insertFundProj(proj);
        return "redirect:/FundProjs";
	}
//
//	/* 更新資料 */
	@PostMapping("/update")
	private String update(
			@RequestParam("udt-projectID") Integer projectID,
			@RequestParam("udt-title") String title,
			@RequestParam("udt-description") String description,
			@RequestParam("udt-startDate") String startDateStr,
			@RequestParam("udt-endDate") String endDateStr,
			@RequestParam("udt-targetAmount") String targetAmount,
			@RequestParam("udt-currentAmount") String currentAmount,
			@RequestParam("udt-threshold") String threshold,
			@RequestParam("udt-postponeDate") String postponeDateStr,
			@RequestParam("udt-category") String category,
			@RequestParam("old-image") String oldImage,
			@RequestParam("udt-image") MultipartFile filePart,
			HttpServletRequest request
			) throws IllegalStateException, IOException {

		FundProjBean proj = new FundProjBean();

		String filename="";
		if (!filePart.getOriginalFilename().isEmpty()) {
	        String uploadPath = request.getServletContext().getRealPath("") + "/WEB-INF/resources/images";
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
	        }
	        filename = filePart.getOriginalFilename();
	        File dest = new File(uploadPath + File.separator + filename);
	        filePart.transferTo(dest);
		}else {
			System.out.println(oldImage);
			filename = oldImage;
		}
		
	    // 將請求中的日期先格式化再轉換成LocalDateTime
	    // 定義datetime-local格式
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	
	    // 將字符串轉換為LocalDateTime
	    LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, formatter);
	    LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, formatter);
	    LocalDateTime postponeDateTime = LocalDateTime.parse(postponeDateStr, formatter);
	    
	    // 設定FundProjBean屬性，日期轉成.sql.TimeStamp
	    proj.setProjectID(projectID);
	    proj.setTitle(title);
	    proj.setDescription(description);
	    proj.setImage(filename);
	    proj.setStartDate(Timestamp.valueOf(startDateTime));
	    proj.setEndDate(Timestamp.valueOf(endDateTime));
	    proj.setTargetAmount(targetAmount);
	    proj.setCurrentAmount(currentAmount);
	    proj.setThreshold(threshold);
	    proj.setPostponeDate(Timestamp.valueOf(postponeDateTime));
	    proj.setCategory(category);
	    
	    fundProjService.updateFundProj(proj);
        return "redirect:/FundProjs";
	}
	
//	/* 刪除資料 */
	@PostMapping("/delete")
	private String delete(@RequestParam("del-projectID") Integer projectID) {
		fundProjService.deleteFundProj(projectID);
        return "redirect:/FundProjs";
	}
}