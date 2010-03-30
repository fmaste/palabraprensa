package wordpress.dao;

import java.util.Date;
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
		
		return getComments(request);
	}

	public static Comment[] getCommentsOnHold(Blog blog) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "hold");
		map.put("number", new Integer(100));
		Object[] params = new Object[]{new Integer(blog.getBlogId()), 
				new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass()),
				map};
		Request request = Requestfactory.create(Wordpress.GET_COMMENTS, params);
		
		return getComments(request);
	}
	
	private static Comment[] getComments(Request request) throws Exception {
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

	public static boolean deleteComment(Blog blog, Comment comment) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "spam");
		map.put("date_created_gmt", comment.getDateCreated());
		map.put("content", comment.getContent());
		map.put("author", comment.getAuthor());
		map.put("author_url", comment.getAuthorUrl());
		map.put("author_email", comment.getAuthorEmail());
		Object[] params = new Object[]{new Integer(blog.getBlogId()), 
				new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass()),
				new Integer(comment.getCommentId()), map};
		Request request = Requestfactory.create(Wordpress.EDIT_COMMENT, params);
		
		return RequestDao.makeRequest(request, true);
	}
}
