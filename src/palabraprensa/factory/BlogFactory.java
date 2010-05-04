package palabraprensa.factory;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import palabraprensa.model.Blog;

public class BlogFactory {
	private static final Logger logger = LoggerFactory.getLogger(BlogFactory.class);
	private static final String BLOG_NAME = "blogName";
	private static final String XML_RPC = "xmlrpc";
	private static final String BLOG_ID = "blogid";
	private static final String IS_ADMIN = "isAdmin";
	private static final String URL = "url";

	public static Blog create(Long id, String name, String url, String xmlrpcUrl) {		
		// TODO: Let name, url and xmlrpcUrl be empty strings ?
		if (id == null || name == null || name.isEmpty() || url == null || url.isEmpty() || xmlrpcUrl == null || xmlrpcUrl.isEmpty()) {
			logger.error("An invalid blog was fetched.");
			return null;
		}
		Blog blog = new Blog();
		blog.setId(id);
		blog.setName(name);
		blog.setUrl(url);
		blog.setXmlrpcUrl(xmlrpcUrl);
		return blog;
	}
	
	public static Blog create(Map<String, Object> map) {
		Long id = null;
		String name = null;
		String url = null; 
		String xmlrpcUrl = null;		
		for (String key : map.keySet()) {
			if (key.equals(BLOG_NAME)) {
				name = map.get(key).toString();
			} else if (key.equals(XML_RPC)) {
				xmlrpcUrl = map.get(key).toString();
			} else if (key.equals(BLOG_ID)) {
				id = Long.parseLong(map.get(key).toString());
			} else if (key.equals(IS_ADMIN)) {
				// Use it ??
			} else if (key.equals(URL)) {
				url = map.get(key).toString();
			} else {
				logger.warn("Unknown info fetched.");
			}	
		}
		return BlogFactory.create(id, name, url, xmlrpcUrl);
	}
	
}
