package post.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import member.bean.MemberBean;
import post.dao.PostDao;
import post.bean.PostBean;
import util.HibernateUtil;

public class PostDaoImpl implements PostDao {
	private Session session;
	private SessionFactory sessionFactory;
	
	
	 //建構子
    public PostDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();//取得SessionFactory
    }
//	public PostDaoImpl() {
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		session = factory.getCurrentSession();
//	}
    
//	public PostDaoImpl(Session session) {
//		this.session = session;
//	}


	@Override
	public PostBean findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(PostBean.class, id);
	}

    
    @Override
	public List<PostBean> findAll() {
    	Session session = sessionFactory.getCurrentSession();
    	Query<PostBean> query = session.createQuery("from PostBean", PostBean.class);
		return query.list();
    }
    
    
    @Override
	public List<PostBean> findByTheme(int themeID) {
    	Session session = sessionFactory.getCurrentSession();
    	String hql="FROM PostBean WHERE themeID=:themeID";//跟javabean一樣
		return session.createQuery(hql, PostBean.class)
				.setParameter("themeID", themeID)
				.list();
    }

    @Override
	public List<PostBean> findByEnter(String enter) {
     	Session session = sessionFactory.getCurrentSession();
     	String hql = "FROM PostBean p WHERE p.postTitle LIKE :enter";
     	//只抓部分欄位 回傳值不是bean 會壞掉
		return session.createQuery(hql, PostBean.class)
				.setParameter("enter", "%"+enter+"%")
				.list();
    }

    @Override
    public PostBean insert(PostBean post) {
    	  Session session = sessionFactory.getCurrentSession();
          session.save(post);
          System.out.println("impl");
          return post ;
    }

    @Override
    public PostBean update(int postID,PostBean post) {
    	   Session session = sessionFactory.getCurrentSession();
           
           PostBean updatepost = session.get(PostBean.class, postID);
           
           updatepost.setPostTitle(post.getPostTitle());
           updatepost.setPostContent(post.getPostContent());
           updatepost.setPostImgUrl(post.getPostImgUrl());
//           updatepost.setPostTime(post.getPostTime());//會動到發表時間
           updatepost.setLikesCount(post.getLikesCount());
           updatepost.setViewCount(post.getViewCount());
           updatepost.setStatus(post.getStatus()); 
           updatepost.setThemeID(post.getThemeID()); 

           session.merge(updatepost);
           
           return updatepost ;
    }

    @Override
    public void delete(int id) {
    	 Session session = sessionFactory.getCurrentSession();
         PostBean post = session.get(PostBean.class, id);
         if (post != null) {
        	 session.remove(post);
         }
    }


}




