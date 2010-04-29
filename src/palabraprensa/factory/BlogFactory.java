package palabraprensa.factory;

import java.util.HashMap;
import java.util.Iterator;

import palabraprensa.model.Blog;

public class BlogFactory {
	private static final String BLOG_NAME = "blogName";
	private static final String XML_RPC = "xmlrpc";
	private static final String BLOG_ID = "blogid";
	private static final String IS_ADMIN = "isAdmin";
	private static final String URL = "url";

	public static Blog create(Integer id, String name, String url, String xmlrpcUrl) {		
		// TODO: Let name, url and xmlrpcUrl be empty strings ?
		if (id == null || name == null || url == null || xmlrpcUrl == null) {
			return null;
		}
		Blog blog = new Blog();
		blog.setId(id);
		blog.setName(name);
		blog.setUrl(url);				
		blog.setXmlrpcUrl(xmlrpcUrl);
		return blog;
	}
	
	public static Blog createIfAdmin(HashMap<String, Object> map) {
		String url = null; 
		Integer blogId = null;
		String blogName = null;
		String xmlrpcUrl = null;			
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key.equals(BLOG_NAME)) {
				blogName = map.get(key).toString();
			} else if (key.equals(XML_RPC)) {
				xmlrpcUrl = map.get(key).toString();
			} else if (key.equals(BLOG_ID)) {
				blogId = Integer.parseInt(map.get(key).toString());
			} else if (key.equals(IS_ADMIN)) {
				if (!Boolean.parseBoolean(map.get(key).toString())) {
					return null;
				}
			} else if (key.equals(URL)) {
				url = map.get(key).toString();
			} else {
				return null;
			}
		}
		return BlogFactory.create(blogId, blogName, url, xmlrpcUrl);
	}
	
}
