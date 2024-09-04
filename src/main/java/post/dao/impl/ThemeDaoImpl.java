package post.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import post.bean.ThemeBean;
import post.dao.ThemeDao;

public class ThemeDaoImpl implements ThemeDao {
    private Connection connection;
 
    public ThemeDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
   
    @Override
    public ThemeBean findById(int id) {
    	ThemeBean theme = null;
        String query = "SELECT * FROM theme WHERE themeID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	theme = new ThemeBean();
                
                	theme.setThemeID(rs.getInt("themeID"));
                	theme.setThemeName(rs.getString("themeName"));
                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return theme;
    }
    public List<ThemeBean> findAll() {
    	  List<ThemeBean> themes = new ArrayList<>();
    	String query = "SELECT * FROM theme ";
    	
    	try (PreparedStatement stmt = connection.prepareStatement(query);
    	         ResultSet rs = stmt.executeQuery()) {
            
            // 遍歷結果集並將每一行的數據存儲到 ThemeBean 中
            while (rs.next()) {
            		ThemeBean theme = new ThemeBean();
//    				theme = new ThemeBean();
    				
    				theme.setThemeID(rs.getInt("themeID"));
    				theme.setThemeName(rs.getString("themeName"));
    				themes.add(theme); // 將 ThemeBean 添加到列表中
    			}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return themes;
    }
    public void delete(int id) {
//    	ThemeBean theme = null;
        String query = "DELETE FROM theme WHERE themeID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                	theme = new ThemeBean();
//                
//                	theme.setThemeID(rs.getInt("themeID"));
//                	theme.setThemeName(rs.getString("themeName"));
//                   
//                }
//            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
		
	}
    public void insert(ThemeBean theme) {
    	   String query = "INSERT INTO theme (themeName) VALUES (?);";
           try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, theme.getThemeName());
          
               stmt.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
    	
    	
    }
    
}