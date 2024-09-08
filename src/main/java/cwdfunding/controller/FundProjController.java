package cwdfunding.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cwdfunding.bean.FundProjBean;
import cwdfunding.service.*;

import java.io.File;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.HibernateUtil;

@WebServlet("/FundProjs")
@MultipartConfig
public class FundProjController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FundProjController() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//在test05.html中以<a>超連結方式連進來，此請求只能使用doGet()處理
		get(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//在test.jsp中以<form action="" method="post">方法傳送請求，只能用doPost()處理
		String action = request.getParameter("action");
		switch (action) {
		case "get":
			get(request, response);
			break;
		case "insert":
			insert(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "update":
			update(request, response);
			break;
		}
	}
	
	/* 查詢全部 */
	private void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		System.out.println("hereee!!");
		FundProjService fundProjService = new FundProjService(session);
		List<FundProjBean> projs = fundProjService.selectAll();
		request.setAttribute("projs", projs);
		request.getRequestDispatcher("/cwdfunding/GetAllFundProj.jsp").forward(request, response);
	}
	
	/* 新增資料 */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		FundProjService fundProjService = new FundProjService(session);
		FundProjBean proj = new FundProjBean();
		
		// 接收請求
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String targetAmount = request.getParameter("targetAmount");
		String currentAmount = request.getParameter("currentAmount");
		String threshold = request.getParameter("threshold");
		String postponeDateStr = request.getParameter("postponeDate");
		String category = request.getParameter("category");
		
		// 處理圖片（存進本端、將檔名寫進資料庫）
		Part filePart= request.getPart("image");
        String uploadPath = getServletContext().getRealPath("") + "cwdfunding/images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
        }

        String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
        String filePath = uploadPath + File.separator + filename;
        filePart.write(filePath);
        
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
        proj.setImage(filename);
        proj.setStartDate(Timestamp.valueOf(startDateTime));
        proj.setEndDate(Timestamp.valueOf(endDateTime));
        proj.setTargetAmount(targetAmount);
        proj.setCurrentAmount(currentAmount);
        proj.setThreshold(threshold);
        proj.setPostponeDate(Timestamp.valueOf(postponeDateTime));
        proj.setCategory(category);
        
        // 執行請求
        fundProjService.insertFundProj(proj);
		response.sendRedirect(request.getContextPath() + "/FundProjs");
	}

	/* 更新資料 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		FundProjService fundProjService = new FundProjService(session);
		FundProjBean proj = new FundProjBean();
	
		int projectID = Integer.valueOf(request.getParameter("udt-projectID"));
		String title = request.getParameter("udt-title");
		String description = request.getParameter("udt-description");
		String startDateStr = request.getParameter("udt-startDate");
		String endDateStr = request.getParameter("udt-endDate");
		String targetAmount = request.getParameter("udt-targetAmount");
		String currentAmount = request.getParameter("udt-currentAmount");
		String threshold = request.getParameter("udt-threshold");
		String postponeDateStr = request.getParameter("udt-postponeDate");
		String category = request.getParameter("udt-category");
		String oldImage = request.getParameter("old-image");
		Part filePart= request.getPart("udt-image");
		
		String filename="";
		if (!filePart.getSubmittedFileName().isEmpty()) {
	        String uploadPath = getServletContext().getRealPath("") + "cwdfunding/images";
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
	        }
	        filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	        String filePath = uploadPath + File.separator + filename;
	        filePart.write(filePath);;
	        System.out.println(filePath);
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
		
		response.sendRedirect(request.getContextPath() + "/FundProjs");	
	}
	
	/* 刪除資料 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		FundProjService fundProjService = new FundProjService(session);
		
		int projectID = Integer.valueOf(request.getParameter("del-projectID"));
		fundProjService.deleteFundProj(projectID);
		
		response.sendRedirect(request.getContextPath() + "/FundProjs");	
	}
	
//	舊方法
//	private void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		FundProjService fundProjService = new FundProjService();
//		List<FundProjBean> projs = fundProjService.getAllFundProjs();
//			request.setAttribute("projs", projs);
//			request.getRequestDispatcher("/cwdfunding/GetAllFundProj.jsp").forward(request, response);
//	}
//	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		FundProjService fundProjService = new FundProjService();
//		
//		String title = request.getParameter("title");
//		String description = request.getParameter("description");
//		String startDate = request.getParameter("startDate");
//		String endDate = request.getParameter("endDate");
//		String targetAmount = request.getParameter("targetAmount");
//		String currentAmount = request.getParameter("currentAmount");
//		String threshold = request.getParameter("threshold");
//		String postponeDate = request.getParameter("postponeDate");
//		String category = request.getParameter("category");
//		
//		Part filePart= request.getPart("image");
//        String uploadPath = getServletContext().getRealPath("") + "cwdfunding/images";
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
//        }
//
//        String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
//        String filePath = uploadPath + File.separator + filename;
//        filePart.write(filePath);
//		
//		fundProjService.insertFundProj(title, description, filename, startDate, endDate, targetAmount, currentAmount, threshold, postponeDate, category);
//		
//		response.sendRedirect(request.getContextPath() + "/FundProjs");
//	}
//	
//	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		FundProjService fundProjService = new FundProjService();
//		
//		int projectID = Integer.valueOf(request.getParameter("udt-projectID"));
//		String title = request.getParameter("udt-title");
//		String description = request.getParameter("udt-description");
//		String startDate = request.getParameter("udt-startDate");
//		String endDate = request.getParameter("udt-endDate");
//		String targetAmount = request.getParameter("udt-targetAmount");
//		String currentAmount = request.getParameter("udt-currentAmount");
//		String threshold = request.getParameter("udt-threshold");
//		String postponeDate = request.getParameter("udt-postponeDate");
//		String category = request.getParameter("udt-category");
//		String oldImage = request.getParameter("old-image");
//		Part filePart= request.getPart("udt-image");
//		
//		String filename="";
//		if (!filePart.getSubmittedFileName().isEmpty()) {
//	        String uploadPath = getServletContext().getRealPath("") + "cwdfunding/images";
//	        File uploadDir = new File(uploadPath);
//	        if (!uploadDir.exists()) {
//	            uploadDir.mkdirs(); // 如果目錄不存在，則創建目錄
//	        }
//	        filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
//	        String filePath = uploadPath + File.separator + filename;
//	        filePart.write(filePath);;
//	        System.out.println(filePath);
//		}else {
//			System.out.println("hereee@!!");
//			System.out.println(oldImage);
//			filename = oldImage;
//		}
//		System.out.println("filename:"+filename);
//		fundProjService.updateFundProj(projectID, title, description, filename, startDate, endDate, targetAmount, currentAmount, threshold, postponeDate, category);
//		
//		response.sendRedirect(request.getContextPath() + "/FundProjs");	
//	}
//	
//	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		FundProjService fundProjService = new FundProjService();		
//		int projectID = Integer.valueOf(request.getParameter("del-projectID"));
//
//		fundProjService.deleteFundProj(projectID);
//		
//		response.sendRedirect(request.getContextPath() + "/FundProjs");	
//	}

}