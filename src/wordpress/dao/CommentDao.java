package wordpress.dao;

import java.util.HashMap;
import java.util.List;

import wordpress.factory.BlogFactory;
import wordpress.factory.CommentFactory;
import wordpress.factory.Requestfactory;
import wordpress.model.Blog;
import wordpress.model.Comment;
import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class CommentDao {

	public static Comment[] getComments(Blog blog) throws Exception {
		
		Object[] params = new Object[]{new Integer(blog.getBlogId()), 
				new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass())};
		Request request = Requestfactory.create(Wordpress.GET_COMMENTS, params);
		Object[] results = RequestDao.makeRequest(request);
		
		Comment[] comments = new Comment[results.length];
		
		for (int index=0; index < results.length ; index ++) {
			if (results[index] instanceof HashMap) {
				HashMap<String, Object> map = (HashMap<String, Object>) results[index];
				// Create a blog and add it to answer
				comments[index] = CommentFactory.create(map);
			}
		}
		return comments;
	}

}
