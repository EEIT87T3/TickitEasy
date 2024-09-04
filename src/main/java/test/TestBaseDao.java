package test;

import post.dao.BaseDao;

public class TestBaseDao {
	
	private BaseDao baseDao;

 public void testbaseQueryObject(){
	 //查詢單筆
	 String sql="select count(1) from theme;";
//	 count存的是long
	 Long count = baseDao.baseQueryObject(Long.class,sql);
	 System.out.println(count);
	 
 }
}
