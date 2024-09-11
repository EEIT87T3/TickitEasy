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
import member.bean.MemberBean;

@Entity
@Table(name = "postComment")
public class Comment implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postCommentID;
	
	@Column(name = "postID")
	private Integer postID;
	
	@Column(name = "memberID")
	private Integer memberID;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "commentDate")
	private Timestamp commentDate;
	


	@ManyToOne
	@JoinColumn(name = "postID", insertable = false, updatable = false)
	private Post post;  // 使用 @ManyToOne 來表示多對一關聯
	
	@ManyToOne
	@JoinColumn(name = "memberID", insertable = false, updatable = false)
	private MemberBean member;  // 使用 @ManyToOne 來表示多對一關聯
	
	public Integer getPostCommentID() {
		return postCommentID;
	}

	public void setPostCommentID(Integer postCommentID) {
		this.postCommentID = postCommentID;
	}

	public Integer getPostID() {
		return postID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public String getContent() {
		return content;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}

	

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public String getMemberNickname() {
		if (member != null) {
			return member.getNickname();
		}
		return null; // 或者返回一個預設值
	}
	
	public void setMemberNickname(String nickname) {
		if (member != null) {
			member.setNickname(nickname);
		} else {
			// 處理 member 為 null 的情況
			// 例如可以拋出異常或者設置一個默認值
			throw new IllegalStateException("MemberBean is not initialized.");
		}
	}

	
	public Comment() {
		
		
	}
	


}
