package post.bean;



public class ThemeBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer themeID;
	private String themeName;
	
	
	public ThemeBean() {
		super();

	}
	public Integer getThemeID() {return themeID;}
	public String getThemeName() {return themeName;}
	
	public void setThemeID(Integer themeID) {this.themeID = themeID;}
	public void setThemeName(String themeName) {this.themeName = themeName;}


}
