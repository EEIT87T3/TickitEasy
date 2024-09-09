package cwdfunding.service;

import cwdfunding.dao.FundProjDao;
import cwdfunding.bean.*;
import java.util.List;
import org.hibernate.Session;

public class FundProjService {
	
	
	private FundProjDao fundProjDao;
	
	public FundProjService() {
		this.fundProjDao = new FundProjDao();
	}
	
	public FundProjService(Session session) {
		this.fundProjDao = new FundProjDao(session);
	}

	/* Hibernate Dao: 查詢全部*/
	public List<FundProjBean> selectAll() {
		return fundProjDao.selectAll();
	}

	/* Hibernate Dao: 插入資料*/
	public FundProjBean insertFundProj(FundProjBean proj) {
		return fundProjDao.insertFundProj(proj);
	}

	/* Hibernate Dao: 更新資料*/
	public FundProjBean updateFundProj(FundProjBean proj) {
		return fundProjDao.updateFundProj(proj);
	}
	
	/* Hibernate Dao: 更新資料*/
	public boolean deleteFundProj(int projectID) {
		return fundProjDao.deleteFundProj(projectID);
	}	
	

}
