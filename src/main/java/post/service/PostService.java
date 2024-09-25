package post.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberBean;
import post.bean.PostBean;
import post.dao.PostDao;
//import post.dao.PostDao ;
import post.dao.impl.PostDaoImpl ;
import util.PasswordUtil;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	//注意是PostDao不是PostDaoImpl
	public PostService() {
		 this.postDao = new PostDaoImpl();
	}
	@Transactional(readOnly = true)
	//取得所有貼文
    public List<PostBean> findAll() {
        return postDao.findAll();
    }
    @Transactional(readOnly = true)
    //根據貼文ID取得單筆貼文
    public PostBean findById(Integer postId) {
    	return postDao.findById(postId);
    }
    @Transactional(readOnly = true)
    //根據貼文主題取得多筆貼文
    public  List<PostBean> findByTheme(Integer themeId) {
    	return postDao.findByTheme(themeId);
    }
    @Transactional(readOnly = true)
    //根據搜尋內容取得多筆貼文
    public List<PostBean> findByEnter(String enter){
    	return postDao.findByEnter(enter);
    }
    //新增單筆貼文
    @Transactional
    public PostBean insert(PostBean post) {
//        post.setStatus(0);
//        post.setPostTime(LocalDate.now());
    	System.out.println("service");
    	return  postDao.insert(post);
    }
    //更新單筆貼文
    @Transactional
    public void update(Integer postID,PostBean post) {
    	postDao.update(postID,post);
    }
    //刪除單筆貼文
    @Transactional
    public void delete(Integer postId) {
    	postDao.delete(postId);
    }
    
}
