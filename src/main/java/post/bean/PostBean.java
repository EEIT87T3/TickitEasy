package post.bean;


import java.sql.Timestamp;

public class PostBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer postID;
	private Integer themeID;
	private Integer memberID;
	private String postTitle;
	private String postContent;
	private String postImgUrl;
	private Timestamp postTime;
	private Integer likesCount;
	private Integer viewCount;
	private Integer status;
	private String themeName; // theme
	private String name; // member
	private String profilePic; // member
	
	public PostBean() {
		super();
		
	}
	public Integer getPostID() {return postID;}
	public Integer getThemeID() {return themeID;}
	public Integer getMemberID() {return memberID;}
	public String getPostTitle() {return postTitle;}
	public String getPostContent() {return postContent;}
	public String getPostImgUrl() {return postImgUrl;}
	public Timestamp getPostTime() {return postTime;}
	public Integer getLikesCount() {return likesCount;}
	public Integer getViewCount() {return viewCount;}
	public Integer getStatus() {return status;}
	public String getThemeName() {
	        return themeName;
	    }
	public String getName() {
		return name;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setPostID(Integer postID) {this.postID = postID;}
	public void setThemeID(Integer themeID) {this.themeID = themeID;}
	public void setMemberID(Integer memberID) {this.memberID = memberID;}
	public void setPostTitle(String postTitle) {this.postTitle = postTitle;}
	public void setPostContent(String postContent) {this.postContent = postContent;}
	public void setPostImgUrl(String postImgUrl) {this.postImgUrl = postImgUrl;}
	public void setPostTime(Timestamp postTime) {this.postTime = postTime;}
	public void setLikesCount(Integer likesCount) {this.likesCount = likesCount;}
	public void setViewCount(Integer viewCount) {this.viewCount = viewCount;}
	public void setStatus(Integer status) {this.status = status;}

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}


    
    
}


