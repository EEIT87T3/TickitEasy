package post.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberBean;
import post.bean.CommentBean;
//import post.dao.PostDao ;
import post.dao.CommentDao ;
import post.dao.impl.CommentDaoImpl ;
import util.PasswordUtil;
@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	//impl->dao才能抓到autowired
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
