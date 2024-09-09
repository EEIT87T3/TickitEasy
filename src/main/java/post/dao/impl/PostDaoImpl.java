package post.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import post.dao.PostDao;
import post.model.Post;

public class PostDaoImpl implements PostDao {
	private Session session;

	public PostDaoImpl(Session session) {
		this.session = session;
	}



//    @Override
//    public Post findById(int id) {
//
//    }
//    
    @Override
	public List<Post> findAll() {
    	Query<Post> query = session.createQuery("from Post", Post.class);
		return query.list();
    }
    
    
//    @Override
//	public List<Post> findByTheme(int themeID) {
//
//    }
//
//    @Override
//	public List<Post> findByEnter(String text) {
//
//    }

    @Override
    public void insert(Post post) {
  

    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(int id) {

    }
}




