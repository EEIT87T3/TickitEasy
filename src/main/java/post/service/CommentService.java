package post.service;

import java.time.LocalDate;
import java.util.List;

import member.bean.MemberBean;
import post.bean.CommentBean;
//import post.dao.PostDao ;
import post.dao.CommentDao ;
import post.dao.impl.CommentDaoImpl ;
import util.PasswordUtil;
public class CommentService {

	private CommentDaoImpl commentDao;
	
	public CommentService() {
		 this.commentDao = new CommentDaoImpl();
	}
	
	//根據貼文ID取得多筆留言
	public List<CommentBean> findById(int id){
		return commentDao.findById(id);
	}
//   	List<CommentBean> findAll();
	
	//根據貼文ID新增留言
    void insert(CommentBean post) {
    	
    }
    //根據貼文ID更新留言
    void update(CommentBean post) {
    	
    }
    void delete(int id) {
    	
    }

    
}
