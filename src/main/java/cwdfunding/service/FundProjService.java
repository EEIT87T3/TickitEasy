package cwdfunding.service;
import java.io.IOException;
import java.util.List;

import cwdfunding.bean.FundProjBean;
import cwdfunding.dao.FundProjDao;
import jakarta.servlet.ServletException;

public class FundProjService {


	private FundProjDao fundProjDao;

	public FundProjService() {
		this.fundProjDao = new FundProjDao();
	}

	public List<FundProjBean> getAllFundProjs(){
		List<FundProjBean> allProjs = fundProjDao.getAllFundProjs();
		return allProjs;
	}

	public void insertFundProj(String title, String description, String image, String startDate, String endDate
			, String targetAmount, String currentAmount, String threshold, String postponeDate, String category) throws IOException, ServletException {
		FundProjBean proj = new FundProjBean();
		proj.setTitle(title);
		proj.setDescription(description);
		proj.setImage(image);
		proj.setStartDate(startDate);
		proj.setEndDate(endDate);
		proj.setTargetAmount(targetAmount);
		proj.setCurrentAmount(currentAmount);
		proj.setThreshold(threshold);
		proj.setPostponeDate(postponeDate);
		proj.setCategory(category);

		fundProjDao.insertFundProj(proj);
	}

	public void updateFundProj(int projectID, String title, String description, String image, String startDate, String endDate
			, String targetAmount, String currentAmount, String threshold, String postponeDate, String category) throws IOException, ServletException {
		FundProjBean proj = new FundProjBean();
		proj.setProjectID(projectID);
		proj.setTitle(title);
		proj.setDescription(description);
		proj.setImage(image);
		proj.setStartDate(startDate);
		proj.setEndDate(endDate);
		proj.setTargetAmount(targetAmount);
		proj.setCurrentAmount(currentAmount);
		proj.setThreshold(threshold);
		proj.setPostponeDate(postponeDate);
		proj.setCategory(category);

		fundProjDao.updateFundProj(proj);
	}

	public void deleteFundProj(int projectID) throws IOException, ServletException {

		fundProjDao.deleteFundProj(projectID);
	}
}
