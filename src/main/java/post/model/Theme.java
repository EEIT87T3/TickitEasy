package post.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "theme") // 將該類映射到資料庫中的 "members" 表
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer themeID;
	
	@Column(name = "themeName")
    private String themeName;

	public Theme() {
		// TODO Auto-generated constructor stub
	}

	public Integer getThemeID() {return themeID;}
	public String getThemeName() {return themeName;}

	public void setThemeID(Integer themeID) {this.themeID = themeID;}
	public void setThemeName(String themeName) {this.themeName = themeName;}
	
	
	@Override
	public String toString() {
	        return themeName; // 返回值,避免返回哈希值
	 }
}


