package palabraprensa.model;

public class Blog {	
	private Integer id;
	private String name;
	private String url;
	private String xmlrpcUrl;
	private Admin admin;
	private Boolean isAdmin;	
			
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getXmlrpcUrl() {
		return xmlrpcUrl;
	}
	
	public void setXmlrpcUrl(String xmlrpcUrl) {
		this.xmlrpcUrl = xmlrpcUrl;
	}
	
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
	
	public String toString(){
		return "Blog ID: " + id + "\nBlog name: " + 
			name + "\nURL: " + url + "\nXML-RPC URL: " + xmlrpcUrl + "\nIs Admin: " + isAdmin;
	}

}
