package wordpress.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import wordpress.factory.BlogFactory;
import wordpress.factory.RequestFactory;
import wordpress.model.Blog;
import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class BlogDao {

	@SuppressWarnings("unchecked")
	public static List<Blog> getBlogs(String userName, String userPass) throws Exception {		
		Object[] params = new Object[]{new String(userName), new String(userPass)};
		Request request = RequestFactory.create(Wordpress.GET_USERS_BLOGS, params);
		Object[] results = RequestDao.makeRequest(request);		
		List<Blog> blogs = new ArrayList<Blog>();	    
		for (int index=0; index < results.length ; index ++) {
			if (results[index] instanceof HashMap<?,?>) {
				HashMap<String, Object> map = (HashMap<String, Object>) results[index];
				// Create a blog and add it to answer
				blogs.add( BlogFactory.create(userName, userPass, map) );
			}
		}
		return blogs;
	}
	
	public static List<Blog> getBlogsOwned(String userName, String userPass) throws Exception {				
		List<Blog> ans = new ArrayList<Blog>();
		List<Blog> blogs = getBlogs(userName, userPass);
		for (Blog blog : blogs) {
			if (blog.getIsAdmin()) {
				ans.add(blog);
			}
		}
		return ans;
	}
	
}
