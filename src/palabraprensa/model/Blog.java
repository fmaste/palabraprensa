package palabraprensa.model;

public class Blog {	
	private Integer id;
	private String name;
	private String url;
	private String xmlrpcUrl;	
			
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
	
	public String toString(){
		return "Blog[\n" +
			"\t" + "Id:" 		+ " \"" + id		+ "\",\n" + 
			"\t" + "Name:" 		+ " \"" + name 		+ "\",\n" +
			"\t" + "URL:" 		+ " \"" + url 		+ "\",\n" + 
			"\t" + "XML-RPC:" 	+ " \"" + xmlrpcUrl + "\",\n" +
			"]";
	}

}
