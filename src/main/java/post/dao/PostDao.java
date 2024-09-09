package post.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import post.model.Post;

public interface PostDao {

//	 增加貼文
//	 註釋
//	 int addPost(PostBean post) ;
//	   	Post findById(int id);
	   	List<Post> findAll();
//	    List<Post> findByTheme(int themeID);
//	    List<Post> findByEnter(String text);
	    void insert(Post post);
	    void update(Post post);
	    void delete(int id);
}
