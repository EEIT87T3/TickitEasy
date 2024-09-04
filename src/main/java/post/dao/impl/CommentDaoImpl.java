package post.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import post.bean.PostBean;
import post.bean.CommentBean;
import post.bean.ThemeBean;
import post.dao.CommentDao;

public class CommentDaoImpl implements CommentDao {
    private Connection connection;
 
    public CommentDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
   
    @Override
    public List<CommentBean> findById(int id) {
    	List<CommentBean> 	comments = new ArrayList<>();
//        String query = "SELECT * FROM postComment WHERE postID = ?";
        String query = "SELECT c.postID, c.content, c.commentDate, m.name FROM postComment c JOIN members m ON c.memberID = m.memberID WHERE c.postID = ?;";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
            	while (rs.next()) {
                	CommentBean comment = new CommentBean();
                	comment= new CommentBean();
                	comment.setPostID(rs.getInt("postID"));
                	comment.setContent(rs.getString("content"));
                	comment.setCommentDate(rs.getTimestamp("commentDate"));
                	comment.setName(rs.getString("name"));
                	comments.add(comment);
                }
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        return comments;
    }
//        public List<CommentBean> findAll(){
//        	return comment;
//       	}
    public void insert(CommentBean comment) {
        // 查詢 member 表格中的 name
        String nameQuery = "SELECT name FROM member WHERE memberID = ?";
        // 插入 postComment 表格的 SQL 查詢語句
        String insertQuery = "INSERT INTO postComment (postID, memberID, content, commentDate) VALUES (?, ?, ?, ?)";
        
        String name = null;
        
        try (PreparedStatement nameStmt = connection.prepareStatement(nameQuery)) {
            nameStmt.setInt(1, comment.getMemberID());
            
            try (ResultSet rs = nameStmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
            
            if (name == null) {
                throw new SQLException("No member found with ID: " + comment.getMemberID());
            }
            
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, comment.getPostID());
                insertStmt.setInt(2, comment.getMemberID());
                insertStmt.setString(3, comment.getContent());
                insertStmt.setTimestamp(4, comment.getCommentDate());
                
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void update(CommentBean comment) {
	    	
	    }
        public void delete(int id) {

            String query = "DELETE FROM postComment WHERE postCommentID = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
    //  
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
        
        }
    }