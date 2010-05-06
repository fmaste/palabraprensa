package palabraprensa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import palabraprensa.factory.BlogFactory;
import palabraprensa.model.Blog;
import palabraprensa.model.User;
import palabraprensa.model.constants.Wordpress;
import palabraprensa.xmlrpc.Request;

public class BlogDao {
	private static final Logger logger = LoggerFactory.getLogger(BlogDao.class);
	
	@SuppressWarnings("unchecked")
	public static List<Blog> getBlogs(String wordpressRoot, User user) throws Exception {		
		if (wordpressRoot == null || wordpressRoot.isEmpty()) {
			logger.error("Invalid base url. A Wordpress root url is required.");
			return null;
		}
		if (user == null || user.getName() == null || user.getName().isEmpty() || user.getPass() == null || user.getPass().isEmpty()) {
			logger.error("Invalid user. A name and a password is required.");
			return null;
		}
		logger.debug("Get blogs for user: \"" + user.getName() + "\".");
		String method = Wordpress.GET_USERS_BLOGS;
		Object[] params = new Object[]{new String(user.getName()), new String(user.getPass())};
		Object result = Request.make(wordpressRoot, user, method, params);
		if (result == null) {
			return null;
		}
		if (!(result instanceof Object[])) {
			logger.error("Invalid server response.");
			return null;
		}
		Object[] blogs = (Object[]) result;
		List<Blog> ans = new ArrayList<Blog>();	    
		for (Object blog : blogs) {
			if (blog instanceof Map<?,?>) {
				Map<String, Object> map = (Map<String, Object>) blog;
				Blog tmpBlog = BlogFactory.create(map);
				if (tmpBlog != null) {
					ans.add(tmpBlog);
				}
			} else {
				logger.error("An invalid blog was fetched and will be ignored.");
			}
		}
		return ans;
	}
	
	public static Map<String,Blog> getBlogsByName(String wordpressRoot, User user) throws Exception {
		List<Blog> blogs = getBlogs(wordpressRoot, user);
		Map<String, Blog> ans = new HashMap<String, Blog>();
		for (Blog blog : blogs) {
			ans.put(blog.getTitle(), blog);
		}
		return ans;
	}

	public static Map<String,Blog> getBlogsByUrl(String wordpressRoot, User user) throws Exception {
		List<Blog> blogs = getBlogs(wordpressRoot, user);
		Map<String, Blog> ans = new HashMap<String, Blog>();
		for (Blog blog : blogs) {
			ans.put(blog.getUrl(), blog);
		}
		return ans;
	}
	
}
