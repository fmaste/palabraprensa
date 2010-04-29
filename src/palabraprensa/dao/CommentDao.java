package palabraprensa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import palabraprensa.factory.CommentFactory;
import palabraprensa.model.Blog;
import palabraprensa.model.Comment;
import palabraprensa.model.User;
import palabraprensa.model.constants.Wordpress;
import palabraprensa.xmlrpc.Request;

public class CommentDao {
 
	public static List<Comment> getCommentsApproved(User user, Blog blog) throws Exception {		
		return getComments(user, blog, "approve");

	}

	public static List<Comment> getCommentsOnHold(User user, Blog blog) throws Exception {		
		return getComments(user, blog, "hold");
	}
	
	@SuppressWarnings("unchecked")
	private static List<Comment> getComments(User user, Blog blog, String statusFilter) throws Exception {
		HashMap<String, Object> struct = new HashMap<String, Object>();
		// For WordPress status defaults to approve
		if (statusFilter != null && !statusFilter.isEmpty()) {
			struct.put("status", statusFilter);
		} else {
			struct.put("status", ""); // FIXME: No key on the map or empty value ??
		}
		// If you don't provide a filter['number'] value then it will limit the response to 10
		struct.put("number", new Integer(200));
		String method = Wordpress.GET_COMMENTS;
		Object[] params = new Object[]{new Integer(blog.getId()), new String(user.getName()), new String(user.getPass()), struct};
		Object result = Request.make(blog, method, params);
		if (!(result instanceof Object[])) {
			// TODO: Log!!!
			return null;
		}
		Object[] comments = (Object[])result; 
		List<Comment> ans = new ArrayList<Comment>();
		for (int index = 0; index < comments.length ; index++) {
			if (comments[index] instanceof HashMap<?,?>) {
				HashMap<String, Object> map = (HashMap<String, Object>) comments[index];
				Comment comment = CommentFactory.create(map);
				if (comment != null) {
					ans.add(comment);
				}
			} else {
				// TODO: Log!!!
			}
			
		}
		return ans;
	}

	public static boolean setCommentApproved(User user, Blog blog, Comment comment) throws Exception {
		return editCommentStatus(user, blog, comment, "approve");
	}
	
	public static boolean setCommentSpam(User user, Blog blog, Comment comment) throws Exception {
		return editCommentStatus(user, blog, comment, "spam");
	}
	
	private static boolean editCommentStatus(User user, Blog blog, Comment comment, String status) throws Exception {
		HashMap<String, Object> struct = new HashMap<String, Object>();
		struct.put("status", status);
		struct.put("date_created_gmt", comment.getDateCreated());
		struct.put("content", comment.getContent());
		struct.put("author", comment.getAuthor());
		struct.put("author_url", comment.getAuthorUrl());
		struct.put("author_email", comment.getAuthorEmail());
		String method = Wordpress.EDIT_COMMENT;
		Object[] params = new Object[]{new Integer(blog.getId()), new String(user.getName()), new String(user.getPass()),new Integer(comment.getId()), struct};		
		Object ans = Request.make(blog, method, params);		
		if (ans instanceof Boolean) {
			return (Boolean)ans;
		} else {
			// TODO: LOG!
			return false;
		}
	}
	
}
