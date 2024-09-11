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

import post.bean.CommentBean;
import post.bean.PostBean;
import post.model.Comment;
import util.HibernateUtil;
import post.dao.CommentDao;

public class CommentDaoImpl implements CommentDao {
	private Session session;
	private SessionFactory sessionFactory;
	
    public CommentDaoImpl() {
    	  this.sessionFactory = HibernateUtil.getSessionFactory();//取得SessionFactory
    }


    @Override
    public List<CommentBean> findById(int postID) {
    	Session session = sessionFactory.getCurrentSession();
    	String hql="FROM CommentBean WHERE postID=:postID";//跟javabean一樣
		return session.createQuery(hql, CommentBean.class)
				.setParameter("postID", postID)
				.list();
    }
//        public List<CommentBean> findAll(){
//        	return comment;
//       	}
    @Override
	public void insert(Comment comment) {
//        // 查詢 member 表格中的 name
//        String nameQuery = "SELECT name FROM member WHERE memberID = ?";
//        // 插入 postComment 表格的 SQL 查詢語句
//        String insertQuery = "INSERT INTO postComment (postID, memberID, content, commentDate) VALUES (?, ?, ?, ?)";
//
//        String name = null;
//
//        try (PreparedStatement nameStmt = connection.prepareStatement(nameQuery)) {
//            nameStmt.setInt(1, comment.getMemberID());
//
//            try (ResultSet rs = nameStmt.executeQuery()) {
//                if (rs.next()) {
//                    name = rs.getString("name");
//                }
//            }
//
//            if (name == null) {
//                throw new SQLException("No member found with ID: " + comment.getMemberID());
//            }
//
//            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
//                insertStmt.setInt(1, comment.getPostID());
//                insertStmt.setInt(2, comment.getMemberID());
//                insertStmt.setString(3, comment.getContent());
//                insertStmt.setTimestamp(4, comment.getCommentDate());
//
//                insertStmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
        @Override
		public void update(Comment comment) {

	    }
        @Override
		public void delete(int id) {
//
//            String query = "DELETE FROM postComment WHERE postCommentID = ?";
//
//            try (PreparedStatement stmt = connection.prepareStatement(query)) {
//                stmt.setInt(1, id);
//    //
//                stmt.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            }

        }
    }