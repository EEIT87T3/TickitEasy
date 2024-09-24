package post.dao;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import post.bean.PostBean;
//import post.model.Post;

public interface PostDao {

//	 增加貼文
//	 註釋
//	 int addPost(PostBean post) ;
	   	PostBean findById(Integer id);
	   	List<PostBean> findAll();
	    List<PostBean> findByTheme(Integer themeID);
	    List<PostBean> findByEnter(String enter);
	    PostBean insert(PostBean post);
	    PostBean update(Integer postID,PostBean post);
	    void delete(Integer id);
}
