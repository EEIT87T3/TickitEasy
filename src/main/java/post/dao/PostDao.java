package post.dao;
import java.util.List;

import post.bean.PostBean;

public interface PostDao {

//	 增加貼文
//	 註釋
//	 int addPost(PostBean post) ;
	   	PostBean findById(int id);
	   	List<PostBean> findAll();
	    List<PostBean> findByTheme(int themeID);
	    List<PostBean> findByEnter(String text);
	    void insert(PostBean post);
	    void update(PostBean post);
	    void delete(int id);
}
