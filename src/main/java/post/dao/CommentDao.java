package post.dao;
import java.util.List;

import post.bean.CommentBean;


public interface CommentDao {

//	 增加貼文
//	 註釋

		List<CommentBean> findById(int id);
//	   	List<CommentBean> findAll();
	    void insert(CommentBean post);
	    void update(CommentBean post);
	    void delete(int id);
}
