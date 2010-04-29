package palabraprensa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import palabraprensa.factory.BlogFactory;
import palabraprensa.model.Blog;
import palabraprensa.model.User;
import palabraprensa.model.constants.Wordpress;
import palabraprensa.xmlrpc.Request;

public class BlogDao {

	@SuppressWarnings("unchecked")
	public static List<Blog> getBlogsOwned(User user) throws Exception {		
		String method = Wordpress.GET_USERS_BLOGS;
		Object[] params = new Object[]{new String(user.getName()), new String(user.getPass())};
		Object result = Request.make(user, method, params);
		if (!(result instanceof Object[])) {
			// TODO: Log!!!
			return null;
		}
		Object[] blogs = (Object[]) result;
		List<Blog> ans = new ArrayList<Blog>();	    
		for (int index = 0; index < blogs.length; index++) {
			if (blogs[index] instanceof HashMap<?,?>) {
				HashMap<String, Object> map = (HashMap<String, Object>) blogs[index];
				Blog blog = BlogFactory.createIfAdmin(map);
				if (blog != null) {
					ans.add(blog);
				}
			} else {
				// TODO: Log!
			}
		}
		return ans;
	}
	
}
