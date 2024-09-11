package post.dao;
import java.util.List;

import org.hibernate.SessionFactory;
import post.bean.PostBean;
//import post.model.Post;

public interface PostDao {

//	 增加貼文
//	 註釋
//	 int addPost(PostBean post) ;
	   	PostBean findById(int id);
	   	List<PostBean> findAll();
	    List<PostBean> findByTheme(int themeID);
	    List<PostBean> findByEnter(String enter);
	    PostBean insert(PostBean post);
	    PostBean update(int postID,PostBean post);
	    void delete(int id);
}
