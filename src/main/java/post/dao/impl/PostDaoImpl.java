package post.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import post.bean.PostBean;
import post.bean.ThemeBean;
import post.dao.PostDao;

public class PostDaoImpl implements PostDao {
    private Connection connection;
 
    public PostDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
   
    @Override
    public PostBean findById(int id) {
        PostBean post = null;
//        String query = "SELECT * FROM post WHERE postID = ?";
        String query = "SELECT p.postID, p.themeID, p.memberID, p.postTitle, p.postContent, p.postImgUrl, p.postTime, " +
                "m.name, m.profilePic, t.themeName " +
                "FROM post p " +
                "JOIN members m ON p.memberID = m.memberID " +
                "JOIN theme t ON p.themeID = t.themeID " +
                "WHERE p.postID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    post = new PostBean();
                    post.setPostID(rs.getInt("postID"));
                    post.setThemeID(rs.getInt("themeID"));
                    post.setMemberID(rs.getInt("memberID"));
                    post.setPostTitle(rs.getString("postTitle"));
                    post.setPostContent(rs.getString("postContent"));
                    post.setPostImgUrl(rs.getString("postImgUrl"));
                    post.setPostTime(rs.getTimestamp("postTime"));
//                    post.setLikesCount(rs.getInt("likesCount"));
//                    post.setViewCount(rs.getInt("viewCount"));
//                    post.setStatus(rs.getInt("status"));
                    post.setThemeName(rs.getString("themeName"));
                    post.setName(rs.getString("name"));
                    post.setProfilePic(rs.getString("profilePic"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return post;
    }
    public List<PostBean> findAll() {
    	
    	List<PostBean> posts = new ArrayList<>();
//    	String query = "SELECT * FROM post ";
    	String query = "SELECT p.postID, p.postTitle, p.postContent, p.likesCount, p.viewCount, p.postTime, t.themeName FROM post p JOIN theme t ON p.themeID = t.themeID ;";
    	
    	try (PreparedStatement stmt = connection.prepareStatement(query);
    			ResultSet rs = stmt.executeQuery()) {

    			  while (rs.next()) {
    				PostBean post = new PostBean();
    				
    				post.setPostID(rs.getInt("postID"));
//    				post.setThemeID(rs.getInt("themeID"));
//    				post.setMemberID(rs.getInt("memberID"));
    				post.setPostTitle(rs.getString("postTitle"));
    				post.setPostContent(rs.getString("postContent"));
//    				post.setPostImgUrl(rs.getString("postImgUrl"));
    				post.setLikesCount(rs.getInt("likesCount"));
    				post.setViewCount(rs.getInt("viewCount"));
    				post.setPostTime(rs.getTimestamp("postTime"));
//    				post.setStatus(rs.getInt("status"));
    				post.setThemeName(rs.getString("themeName"));
    				posts.add(post);
    			}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		
    	}
    	return posts;
    }
    public List<PostBean> findByTheme(int themeID) {
    	
    	List<PostBean> posts = new ArrayList<>();
//    	String query = "SELECT * FROM post WHERE themeID=?";
    	String query = "SELECT p.postID, p.postTitle, p.postContent, p.likesCount, p.viewCount, p.postTime, t.themeName FROM post p JOIN theme t ON p.themeID = t.themeID WHERE p.themeID=?;";
    	
    	   try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setInt(1, themeID);
               try (ResultSet rs = stmt.executeQuery()) {
    		while (rs.next()) {
    			PostBean post = new PostBean();
    			
    			post.setPostID(rs.getInt("postID"));
//    			post.setThemeID(rs.getInt("themeID"));
//    			post.setMemberID(rs.getInt("memberID"));
    			post.setPostTitle(rs.getString("postTitle"));
    			post.setPostContent(rs.getString("postContent"));
//    			post.setPostImgUrl(rs.getString("postImgUrl"));
    			post.setPostTime(rs.getTimestamp("postTime"));
    			post.setLikesCount(rs.getInt("likesCount"));
    			post.setViewCount(rs.getInt("viewCount"));
//    			post.setStatus(rs.getInt("status"));
    			post.setThemeName(rs.getString("themeName")); // 設置主題名稱
    			posts.add(post);
    		
    		}
               }
    	} catch (SQLException e) {
    		e.printStackTrace();
    		
    	}
    	return posts;
    }
    
    public List<PostBean> findByEnter(String text) {
    	
    	List<PostBean> posts = new ArrayList<>();
//    	String query = "SELECT * FROM post WHERE themeID=?";
    	String query = "SELECT p.postID, p.postTitle, p.postContent, p.likesCount, p.viewCount, p.postTime, t.themeName FROM post p JOIN theme t ON p.themeID = t.themeID WHERE p.postTitle LIKE ?;";
    	
    	try (PreparedStatement stmt = connection.prepareStatement(query)) {
    		stmt.setString(1, "%" + text + "%");
    		
    		try (ResultSet rs = stmt.executeQuery()) {
    			while (rs.next()) {
    				PostBean post = new PostBean();
    				
    				post.setPostID(rs.getInt("postID"));
//    			post.setThemeID(rs.getInt("themeID"));
//    			post.setMemberID(rs.getInt("memberID"));
    				post.setPostTitle(rs.getString("postTitle"));
    				post.setPostContent(rs.getString("postContent"));
//    			post.setPostImgUrl(rs.getString("postImgUrl"));
    				post.setPostTime(rs.getTimestamp("postTime"));
    				post.setLikesCount(rs.getInt("likesCount"));
    				post.setViewCount(rs.getInt("viewCount"));
//    			post.setStatus(rs.getInt("status"));
    				post.setThemeName(rs.getString("themeName")); // 設置主題名稱
    				posts.add(post);
    				
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		
    	}
    	return posts;
    }
    
    @Override
    public void insert(PostBean post) {
        String query = "INSERT INTO post (themeID, memberID,  postTitle, postContent, postImgUrl, postTime, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, post.getThemeID());
            stmt.setInt(2, post.getMemberID());
            stmt.setString(3, post.getPostTitle());
            stmt.setString(4, post.getPostContent());
            stmt.setString(5, post.getPostImgUrl());
            stmt.setTimestamp(6, new java.sql.Timestamp(post.getPostTime().getTime()));
            stmt.setInt(7, post.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void update(PostBean post) {
        String query = "UPDATE post SET postTitle = ?, postContent = ?,postImgUrl = ? ,themeID = ? WHERE postID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, post.getPostTitle());
            stmt.setString(2, post.getPostContent());
            stmt.setString(3, post.getPostImgUrl());
//            stmt.setTimestamp(4, post.getPostTime());//不要動到原本的發布日期
            stmt.setInt(4, post.getThemeID());
            stmt.setInt(5, post.getPostID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
    
        String query = "DELETE FROM post WHERE postID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
//  
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
}



//public class postDaoImpl implements PostDao{
//	@Override
//	public int addPost(PostBean post) {
//		return 0;
//	}
//
//	
//}
