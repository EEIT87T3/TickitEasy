package cwdfunding.service;

import cwdfunding.dao.FundProjDao;
import cwdfunding.bean.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FundProjService {
	
	@Autowired
	private FundProjDao fundProjDao;
	
	/* Hibernate Service: 查詢全部*/
	public List<FundProjBean> selectAll() {
		return fundProjDao.selectAll();
	}

	/* Hibernate Service: 插入資料*/
	public FundProjBean insertFundProj(FundProjBean proj) {
		return fundProjDao.insertFundProj(proj);
	}

	/* Hibernate Service: 更新資料*/
	public FundProjBean updateFundProj(FundProjBean proj) {
		return fundProjDao.updateFundProj(proj);
	}
	
	/* Hibernate Service: 刪除資料*/
	public boolean deleteFundProj(int projectID) {
		return fundProjDao.deleteFundProj(projectID);
	}	
	

}
