package post.bean;


import java.sql.Timestamp;

public class CommentBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer postCommentID;
	private Integer postID;
	private Integer memberID;
	private String content;
	private Timestamp commentDate;
	private String name;

	public CommentBean() {
		super();

	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





}
