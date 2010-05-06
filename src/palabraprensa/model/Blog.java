package palabraprensa.model;

public class Blog {	
	private Long id;
	private String title;
	private String url;
	private String xmlrpcUrl;	
			
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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
	
	public String toString(){
		return "Blog[\n" +
			"\t" + "Id:" 		+ " \"" + id		+ "\",\n" + 
			"\t" + "Title:"		+ " \"" + title 		+ "\",\n" +
			"\t" + "URL:" 		+ " \"" + url 		+ "\",\n" + 
			"\t" + "XML-RPC:" 	+ " \"" + xmlrpcUrl + "\",\n" +
			"]";
	}

}
