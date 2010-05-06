package palabraprensa.model;

import java.util.Date;

public class Comment {		
	private Long id = null;
	private Date dateCreated = null;	// (ISO.8601, always GMT)
	private String userId = null;	
	private String parent = null;
	private String status = null;
	private String content = null;
	private String link = null;
	private String postId = null;
	private String postTitle = null;
	private String author = null;
	private String authorUrl = null;
	private String authorEmail = null;
	private String authorIp = null;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Comment() {
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorIp() {
		return authorIp;
	}

	public void setAuthorIp(String authorIp) {
		this.authorIp = authorIp;
	}

	public String toString(){
		return "Comment[\n" +
			"\t" + "Id:" 		+ " \"" + id		+ "\",\n" + 
			"\t" + "dateCreated:" 		+ " \"" + dateCreated	+ "\",\n" +
			"\t" + "UserId:" 	+ " \"" + userId	+ "\",\n" +
			"\t" + "Author:" 	+ " \"" + author	+ "\",\n" +
			"\t" + "Status:" 	+ " \"" + status	+ "\",\n" +
			"\t" + "Content:" 	+ " \"" + content 	+ "\",\n" +
			"\t" + "Url:"	 	+ " \"" + link 		+ "\",\n" +
			"]";
	}
	
}
