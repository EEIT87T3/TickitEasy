package post.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	
	@Column(name = "themeID")
	private Integer themeID;
	
	@Column(name = "memberID")
	private Integer memberID;
	
	@Column(name = "postTitle")
	private String postTitle;
	
	@Column(name = "postContent")
	private String postContent;
	
	@Column(name = "postImgUrl")
	private String postImgUrl;
	
	@Column(name = "postTime")
	private Timestamp postTime;
	
	@Column(name = "likesCount")
	private Integer likesCount;
	
	@Column(name = "viewCount")
	private Integer viewCount;
	
	@Column(name = "status")
	private Integer status;
	
	// 正確使用 @ManyToOne 關聯到 Theme 實體
	@ManyToOne
	@JoinColumn(name = "themeID", referencedColumnName = "themeID", insertable = false, updatable = false)
    private Theme theme;  // 這裡關聯到 Theme 實體

    // 正確使用 @ManyToOne 關聯到 Member 實體
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID", insertable = false, updatable = false)
    private Member member;  // 這裡關聯到 Member 實體
	
	
	public Post() {
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
	
	public Theme getThemeName() {
		return theme;
	}
	public void setThemeName(Theme theme) {
		this.theme = theme;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
    

	
	
}
