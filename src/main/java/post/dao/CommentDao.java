package post.dao;
import java.util.List;

import post.bean.CommentBean;
import post.model.Comment;


public interface CommentDao {

//	 增加貼文
//	 註釋

		List<CommentBean> findById(int id);
//	   	List<Comment> findAll();
	    void insert(Comment post);
	    void update(Comment post);
	    void delete(int id);
}
