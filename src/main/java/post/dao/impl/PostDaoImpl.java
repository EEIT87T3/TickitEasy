package post.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import member.bean.MemberBean;
import post.dao.PostDao;
import post.bean.PostBean;
import util.HibernateUtil;

@Repository
public class PostDaoImpl implements PostDao {
	

	@Autowired
	private SessionFactory sessionFactory;
	
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public PostBean findById(Integer id) {
		return getCurrentSession().get(PostBean.class, id);
	}

    
    @Override
	public List<PostBean> findAll() {
    	Session session = sessionFactory.getCurrentSession();
    	Query<PostBean> query = session.createQuery("from PostBean", PostBean.class);
		return query.list();
    }
    
    
    @Override
	public List<PostBean> findByTheme(Integer themeID) {
    	String hql="FROM PostBean WHERE themeID=:themeID";//跟javabean一樣
		return getCurrentSession().createQuery(hql, PostBean.class)
				.setParameter("themeID", themeID)
				.list();
    }

    @Override
	public List<PostBean> findByEnter(String enter) {
     
     	String hql = "FROM PostBean p WHERE p.postTitle LIKE :enter";
     	//只抓部分欄位 回傳值不是bean 會壞掉
     	return getCurrentSession().createQuery(hql, PostBean.class)
				.setParameter("enter", "%"+enter+"%")
				.list();
    }
    
    @Transactional
    @Override
    public PostBean insert(PostBean post) {
    	  getCurrentSession().save(post);
          System.out.println("impl");
          return post ;
    }
    
    @Transactional
    @Override
    public PostBean update(Integer postID,PostBean post) {
           
           PostBean updatepost = getCurrentSession().get(PostBean.class, postID);
           
           updatepost.setPostTitle(post.getPostTitle());
           updatepost.setPostContent(post.getPostContent());
           updatepost.setPostImgUrl(post.getPostImgUrl());
//           updatepost.setPostTime(post.getPostTime());//會動到發表時間
           updatepost.setLikesCount(post.getLikesCount());
           updatepost.setViewCount(post.getViewCount());
           updatepost.setStatus(post.getStatus()); 
           updatepost.setThemeID(post.getThemeID()); 

           getCurrentSession().merge(updatepost);
           //update?
           return updatepost ;
    }

    @Transactional
    @Override
    public void delete(Integer id) {

         PostBean post = getCurrentSession().get(PostBean.class, id);
         if (post != null) {
        	 getCurrentSession().remove(post);
         }
    }


}




