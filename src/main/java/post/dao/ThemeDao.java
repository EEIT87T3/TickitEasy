package post.dao;
import java.util.List;

import post.bean.ThemeBean;

public interface ThemeDao {

//	 增加貼文
//	 註釋
//	 int addPost(PostBean post) ;
		ThemeBean findById(int id);
		List<ThemeBean> findAll();
	    void insert(ThemeBean theme);
//	    void update(ThemeBean theme);
	    void delete(int id);
}
