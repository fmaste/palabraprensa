package wordpress.dao;

import java.util.HashMap;
import wordpress.factory.CommentFactory;
import wordpress.factory.RequestFactory;
import wordpress.model.Blog;
import wordpress.model.Comment;
import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class CommentDao {

	// Gets a comment, given it's comment ID. Note that this isn't in 2.6.1, but is in the HEAD (so should be in anything newer than 2.6.1) 
	public static Comment[] getCommentsApproved(Blog blog) throws Exception {		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "approve");
		// Status defaults to approve
		// If you don't provide a filter['number'] value then it will limit the response to 10
		map.put("number", new Integer(200));
		Object[] params = new Object[]{new Integer(blog.getBlogId()), new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass()), map};
		Request request = RequestFactory.create(Wordpress.GET_COMMENTS, params);		
		return getComments(request);
	}

	public static Comment[] getCommentsOnHold(Blog blog) throws Exception {		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "hold");
		// If you don't provide a filter['number'] value then it will limit the response to 10
		map.put("number", new Integer(200));
		Object[] params = new Object[]{new Integer(blog.getBlogId()), new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass()), map};
		Request request = RequestFactory.create(Wordpress.GET_COMMENTS, params);		
		return getComments(request);
	}
	
	@SuppressWarnings("unchecked")
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

	public static boolean setCommentApproved(Blog blog, Comment comment) throws Exception {
		return editCommentStatus(blog, comment, "approve");
	}
	
	public static boolean setCommentSpam(Blog blog, Comment comment) throws Exception {
		return editCommentStatus(blog, comment, "spam");
	}
	
	private static boolean editCommentStatus(Blog blog, Comment comment, String status) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("date_created_gmt", comment.getDateCreated());
		map.put("content", comment.getContent());
		map.put("author", comment.getAuthor());
		map.put("author_url", comment.getAuthorUrl());
		map.put("author_email", comment.getAuthorEmail());
		Object[] params = new Object[]{new Integer(blog.getBlogId()), new String(blog.getAdmin().getUserName()), new String(blog.getAdmin().getUserPass()),new Integer(comment.getCommentId()), map};
		Request request = RequestFactory.create(Wordpress.EDIT_COMMENT, params);		
		return RequestDao.makeBooleanRequest(request);
	}
	
}
