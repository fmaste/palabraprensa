package wordpress.dao;

import java.util.HashMap;

import wordpress.factory.BlogFactory;
import wordpress.factory.Requestfactory;
import wordpress.model.Blog;
import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class BlogDao {

	public static Blog[] getBlogs(String userName, String userPass) throws Exception {
		
		Object[] params = new Object[]{new String(userName), new String(userPass)};
		Request request = Requestfactory.create(Wordpress.GET_USERS_BLOGS, params);
		Object[] results = RequestDao.makeRequest(request);
		
		Blog[] blogs = new Blog[results.length];
	    
		for (int index=0; index < results.length ; index ++) {
			if (results[index] instanceof HashMap) {
				HashMap<String, Object> map = (HashMap<String, Object>) results[index];
				// Create a blog and add it to answer
				blogs[index] = BlogFactory.create(userName, userPass, map);
			}
		}
		return blogs;
	}

}
