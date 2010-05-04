package palabraprensa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import palabraprensa.factory.CommentFactory;
import palabraprensa.model.Blog;
import palabraprensa.model.Comment;
import palabraprensa.model.User;
import palabraprensa.model.constants.Wordpress;
import palabraprensa.xmlrpc.Request;

public class CommentDao {
	private static final Logger logger = LoggerFactory.getLogger(CommentDao.class);
	
	public static List<Comment> getCommentsApproved(User user, Blog blog) throws Exception {		
		return getComments(user, blog, "approve", null, null);
	}

	public static List<Comment> getCommentsApproved(User user, Blog blog, String offset, String number) throws Exception {		
		return getComments(user, blog, "approve", offset, number);
	}
	
	public static List<Comment> getCommentsOnHold(User user, Blog blog) throws Exception {		
		return getComments(user, blog, "hold", null, null);
	}
	
	public static List<Comment> getCommentsOnHold(User user, Blog blog, String offset, String number) throws Exception {		
		return getComments(user, blog, "hold", offset, number);
	}
	
	@SuppressWarnings("unchecked")
	private static List<Comment> getComments(User user, Blog blog, String statusFilter, String offset, String number) throws Exception {
		if (user == null || user.getName() == null || user.getName().isEmpty() || user.getPass() == null || user.getPass().isEmpty()) {
			logger.error("Invalid user. A name and a password is required.");
			return null;
		}
		if (blog.getId() == null || blog.getXmlrpcUrl() == null || blog.getXmlrpcUrl().isEmpty()) {
			logger.error("Invalid blog. An id and a xmlrpc url is required.");
			return null;
		}
		logger.debug("Get comments for user: \"" + user.getName() + "\", blog: \"" + blog.getName() + "\".");
		Map<String, Object> struct = new HashMap<String, Object>();
		// For WordPress status defaults to approve
		if (statusFilter != null && !statusFilter.isEmpty()) {
			struct.put("status", statusFilter);
		}
		if (offset != null && !offset.isEmpty()) {
			struct.put("offset", offset);
		}
		if (number != null && !number.isEmpty()) {
			struct.put("number", number);
		}
		// If you don't provide a filter['number'] value then it will limit the response to 10
		struct.put("number", new Integer(200));
		String method = Wordpress.GET_COMMENTS;
		Object[] params = new Object[]{new String(blog.getId().toString()), new String(user.getName()), new String(user.getPass()), struct};
		Object result = Request.make(blog, method, params);
		if (!(result instanceof Object[])) {
			logger.error("Invalid server response.");
			return null;
		}
		Object[] comments = (Object[])result; 
		List<Comment> ans = new ArrayList<Comment>();
		for (Object comment : comments) {
			if (comment instanceof Map<?,?>) {
				Map<String, Object> map = (Map<String, Object>) comment;
				Comment commentTmp = CommentFactory.create(map);
				if (commentTmp != null) {
					ans.add(commentTmp);
				}
			} else {
				logger.error("An invalid comment was fetched and will be ignored.");
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
		if (user == null || user.getName() == null || user.getName().isEmpty() || user.getPass() == null || user.getPass().isEmpty()) {
			logger.error("Invalid user. A name and a password is required.");
			return false;
		}
		if (blog.getId() == null || blog.getXmlrpcUrl() == null || blog.getXmlrpcUrl().isEmpty()) {
			logger.error("Invalid blog. An id and a xmlrpc url is required.");
			return false;
		}
		if (comment == null || comment.getId() == null) {
			logger.error("Invalid comment. An id is required.");
			return false;
		}
		logger.debug("Edit comment: " + comment.getId() + " (" + comment.getContent()+ ")");
		Map<String, Object> struct = new HashMap<String, Object>();
		struct.put("status", status);
		struct.put("date_created_gmt", comment.getDateCreated());
		struct.put("content", comment.getContent());
		struct.put("author", comment.getAuthor());
		struct.put("author_url", comment.getAuthorUrl());
		struct.put("author_email", comment.getAuthorEmail());
		String method = Wordpress.EDIT_COMMENT;
		Object[] params = new Object[]{new String(blog.getId().toString()), new String(user.getName()), new String(user.getPass()), new String(comment.getId().toString()), struct};		
		Object ans = Request.make(blog, method, params);		
		if (ans instanceof Boolean) {
			return (Boolean)ans;
		} else {
			logger.error("Invalid server response.");
			return false;
		}
	}
	
	public static boolean deleteComment(User user, Blog blog, Comment comment) throws Exception {
		if (user == null || user.getName() == null || user.getName().isEmpty() || user.getPass() == null || user.getPass().isEmpty()) {
			logger.error("Invalid user. A name and a password is required.");
			return false;
		}
		if (blog.getId() == null || blog.getXmlrpcUrl() == null || blog.getXmlrpcUrl().isEmpty()) {
			logger.error("Invalid blog. An id and a xmlrpc url is required.");
			return false;
		}
		if (comment == null || comment.getId() == null) {
			logger.error("Invalid comment. An id is required.");
			return false;
		}
		logger.debug("Delete comment: " + comment.getId() + " (" + comment.getContent()+ ")");
		String method = Wordpress.EDIT_COMMENT;
		Object[] params = new Object[]{new String(blog.getId().toString()), new String(user.getName()), new String(user.getPass()), new String(comment.getId().toString())};		
		Object ans = Request.make(blog, method, params);		
		if (ans instanceof Boolean) {
			return (Boolean)ans;
		} else {
			logger.error("Invalid server response.");
			return false;
		}	
	}
	
}
