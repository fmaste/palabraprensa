package wordpress.model;

public class Blog {
	
	private Admin admin;
	private Boolean isAdmin;
	private String url;
	private Integer blogId;
	private String blogName;
	private String xmlrpcUrl;
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getXmlrpcUrl() {
		return xmlrpcUrl;
	}
	public void setXmlrpcUrl(String xmlrpcUrl) {
		this.xmlrpcUrl = xmlrpcUrl;
	}
	public String toString(){
		return "Blog ID: " + blogId + "\nBlog name: " + 
			blogName + "\nURL: " + url + "\nXML-RPC URL: " + xmlrpcUrl + "\nIs Admin: " + isAdmin;
	}
}
