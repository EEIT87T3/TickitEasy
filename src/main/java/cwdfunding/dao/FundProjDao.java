package cwdfunding.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cwdfunding.bean.FundProjBean;

@Repository

public class FundProjDao {

	@Autowired
	private SessionFactory factory;
	
	
	/* Hibernate Dao: 查詢全部*/
	@Transactional(readOnly = true)
	public List<FundProjBean> selectAll() {
		Session session = factory.openSession();
		Query<FundProjBean> query = session.createQuery("from FundProjBean", FundProjBean.class);
		return query.list();
	}
	
	/* Hibernate Dao: 插入資料*/
	@Transactional
	public FundProjBean insertFundProj(FundProjBean proj) {
		Session session = factory.openSession();
		if(proj != null) {
			session.persist(proj);
			session.flush();
			return proj;
		}
		return null;
	}

	/* Hibernate Dao: 更新資料*/
	@Transactional
	public FundProjBean updateFundProj(FundProjBean proj) {
		Session session = factory.openSession();
		// 先以id查詢資料是否已存在，查詢結果result為永續狀態的物件(persistent object)，
		FundProjBean result = session.get(FundProjBean.class, proj.getProjectID());
		
		// 自動髒減機制：如果永續物件的屬性有更動，Hibernate會偵測到並同步到資料庫
		//（真正寫進資料庫的在transaction commit時）
		if(result != null) {			
			result.setTitle(proj.getTitle());
			result.setDescription(proj.getDescription());
			result.setImage(proj.getImage());
			result.setStartDate(proj.getStartDate());
			result.setEndDate(proj.getEndDate());
			result.setTargetAmount(proj.getTargetAmount());
			result.setCurrentAmount(proj.getCurrentAmount());
			result.setThreshold(proj.getThreshold());
			result.setPostponeDate(proj.getPostponeDate());
			result.setCategory(proj.getCategory());
			session.flush();
			return result;
		}
		return null;
	}
	
	/* Hibernate Dao: 刪除資料*/
	@Transactional
	public boolean deleteFundProj(int projectID) {
		Session session = factory.openSession();

		// 先以id查詢資料是否已存在，查詢結果result為永續狀態的物件(persistent object)，
		FundProjBean result = session.get(FundProjBean.class, projectID);
		
		if(result != null) {
			session.remove(result);
			session.flush();
			return true;
		}
		return false;
	}
}
