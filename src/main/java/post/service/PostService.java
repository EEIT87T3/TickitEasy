package post.service;

import java.time.LocalDate;
import java.util.List;

import member.bean.MemberBean;
import post.bean.PostBean;
//import post.dao.PostDao ;
import post.dao.impl.PostDaoImpl ;
import util.PasswordUtil;
public class PostService {

	private PostDaoImpl postDao;
	
	public PostService() {
		 this.postDao = new PostDaoImpl();
	}

	//取得所有貼文
    public List<PostBean> findAll() {
        return postDao.findAll();
    }
    
    //根據貼文ID取得單筆貼文
    public PostBean findById(int postId) {
    	return postDao.findById(postId);
    }
    
    //根據貼文主題取得多筆貼文
    public  List<PostBean> findByTheme(int postId) {
    	return postDao.findByTheme(postId);
    }
    //根據搜尋內容取得多筆貼文
    public List<PostBean> findByEnter(String enter){
    	return postDao.findByEnter(enter);
    }
    //新增單筆貼文
    public PostBean insert(PostBean post) {
//        post.setStatus(0);
//        post.setPostTime(LocalDate.now());
    	System.out.println("service");
    	return  postDao.insert(post);
    }
    //更新單筆貼文
    public void update(int postID,PostBean post) {
    	postDao.update(postID,post);
    }
    //刪除單筆貼文
    public void delete(int id) {
    	postDao.delete(id);
    }
    
}
