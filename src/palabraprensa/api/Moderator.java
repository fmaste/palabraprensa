package palabraprensa.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import palabraprensa.dao.BlogDao;
import palabraprensa.dao.CommentDao;
import palabraprensa.model.Blog;
import palabraprensa.model.Comment;
import palabraprensa.model.User;

public class Moderator {
	private User user;
	private Blog blog;
	
	/**
	 * 
	 * @param userName		The Wordpress username that has moderator privileges (admin or editor profile) of the desired blog
	 * @param pass			The Wordpress username password
	 * @param rootUrl		The server root url. Example, http://wordpress.com (always include the http://)
	 * @param blogUrl		The url of the desired blog. Example, http://keepcontest.wordpress.com (always include the http://)
	 * @throws Exception	If theres no blog on the provided urls.
	 */
	public Moderator(String userName, String pass, String rootUrl, String blogUrl) throws Exception {
		this.user = new User();
		this.user.setName(userName);
		this.user.setPass(pass);
		Map<String,Blog> blogs = BlogDao.getBlogsByUrl(rootUrl, user);
		if (!blogUrl.endsWith("/")) {
			blogUrl += "/";
		}
		Blog blog = blogs.get(blogUrl);
		if (blog == null) {
			throw new Exception("There is no blog with an url equal to " + blogUrl);
		}
		this.blog = blog;
	}
	
	private List<Comment> getComments(Date startDate, boolean premoderation) throws Exception {
		int offset = 0;
		int step = 20;
		boolean stop = false;
		List<Comment> comments;
		if (premoderation) {
			comments = CommentDao.getCommentsOnHold(user, blog, offset, step);
		} else {
			comments = CommentDao.getCommentsApproved(user, blog, offset, step);
		}
		if (comments == null || comments.isEmpty()) {
			return new ArrayList<Comment>();
		}
		Comment newest = comments.get(0);
		if (newest.getDateCreated().before(startDate)) {
			return new ArrayList<Comment>();
		}
		while (!stop) {
			Comment oldest = comments.get(comments.size() - 1);
			if (oldest.getDateCreated().after(startDate)) { // Ask for more ??
				offset += step;
				List<Comment> tmpComments;
				if (premoderation) {
					tmpComments = CommentDao.getCommentsOnHold(user, blog, offset, step);
				} else {
					tmpComments = CommentDao.getCommentsApproved(user, blog, offset, step);
				}
				if (tmpComments == null || tmpComments.isEmpty()) {
					stop = true;
				} else {
					comments.addAll(tmpComments);
				}
			} else {
				stop = true;
			}
		}
		List<Comment> ans = new ArrayList<Comment>();
		for (Comment comment : comments) {
			if (comment.getDateCreated().after(startDate)) {
				ans.add(comment);
			} else {
				break;
			}
		}
		return ans;		
	}
	
	/**
	 * Gets all the comments that are waiting on the premoderation queue.
	 * @param startDate		Since this date (always in GMT/UTC)
	 * @return				Ordered from newest to oldest.
	 * @throws Exception	On fatal errors.
	 */
	public List<Comment> getCommentsForPreModeration(Date startDate) throws Exception {
		return getComments(startDate, true);
	}

	/**
	 * Gets all the comments that are published.
	 * @param startDate		Since this date (always in GMT/UTC)
	 * @return 				Ordered from newest to oldest.
	 * @throws Exception	On fatal errors.
	 */
	public List<Comment> getCommentsForPostModeration(Date startDate) throws Exception {
		return getComments(startDate, false);
	}
	
	/**
	 * Gets pre and post moderations comments.
	 * @param startDate		Since this date (always in GMT/UTC)
	 * @return				All pre moderation from newest to oldest first and them post moderation from newest to oldest.
	 * @throws Exception	On fatal errors.
	 */
	public List<Comment> getAllComments(Date startDate) throws Exception {
		List<Comment> ans = getCommentsForPreModeration(startDate);
		ans.addAll(getCommentsForPostModeration(startDate));
		return ans;
	}
	
	/**
	 * Moves the comment to the trash.
	 * @param comment		The comment to delete.
	 * @return				False on failure.
	 * @throws Exception	On fatal errors.
	 */
	public boolean deleteComment(Comment comment) throws Exception {
		return CommentDao.deleteComment(user, blog, comment); 
	}
	
	/**
	 * Publishes the comments (if it is already published nothing happens).
	 * @param comment		The comment to approve.
	 * @return				False on failure.
	 * @throws Exception	On fatal errors.
	 */
	public boolean approveComment(Comment comment) throws Exception {
		return CommentDao.setCommentApproved(user, blog, comment);
	}
	
	/**
	 * Moves the comment to the spam folder.
	 * @param comment		The comment to set as spam.
	 * @return				False on failure.
	 * @throws Exception	On fatal errors.
	 */
	public boolean spamComment(Comment comment) throws Exception {
		return CommentDao.setCommentSpam(user, blog, comment);
	}
	
}
