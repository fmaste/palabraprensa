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

	public static Blog create(String userName, String userPass, Boolean isAdmin, String url, Integer blogId, String blogName, String xmlrpcUrl) {		
		if (userName == null || userPass == null ||
				isAdmin == null || url == null || blogId == null || 
				blogName == null || xmlrpcUrl == null) {
			return null;
		}
		Blog blog = new Blog();
		blog.setAdmin(AdminFactory.create(userName, userPass));
		blog.setIsAdmin(isAdmin);
		blog.setUrl(url);
		blog.setId(blogId);
		blog.setName(blogName);
		blog.setXmlrpcUrl(xmlrpcUrl);
		return blog;
	}

	public static Blog create(String userName, String userPass, HashMap<String, Object> map) {
		Boolean isAdmin = null;
		String url = null; 
		Integer blogId = null;
		String blogName = null;
		String xmlrpcUrl = null;			
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key.equals(BLOG_NAME)) {
				blogName = map.get(key).toString();
			} else if(key.equals(XML_RPC)) {
				xmlrpcUrl = map.get(key).toString();
			} else if(key.equals(BLOG_ID)) {
				blogId = Integer.parseInt(map.get(key).toString());
			} else if(key.equals(IS_ADMIN)) {
				isAdmin = Boolean.parseBoolean(map.get(key).toString());
			} else if(key.equals(URL)) {
				url = map.get(key).toString();
			} else {
				return null;
			}
		}
		return BlogFactory.create(userName, userPass, isAdmin, url, blogId, blogName, xmlrpcUrl);
	}
}
