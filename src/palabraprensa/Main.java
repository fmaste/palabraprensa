package palabraprensa;

import java.util.List;
import palabraprensa.dao.BlogDao;
import palabraprensa.dao.CommentDao;
import palabraprensa.model.Blog;
import palabraprensa.model.Comment;
import palabraprensa.model.User;

public class Main {
	public static final String WP_USER_NAME = "keepcontest";
	public static final String WP_PASS_PASS = "password";
	// Suppose all comments are or aren't rejected (testing)
	public static final boolean REJECT_ALL = true;
	
	public static void main(String[] args) throws Exception {
		// Create the user
		User user = new User();
		user.setName(WP_USER_NAME);
		user.setPass(WP_PASS_PASS);
		// Get the blogs where the user is admin				
		List<Blog> blogs = BlogDao.getBlogsOwned(user);
		if (blogs == null || blogs.isEmpty()) {
			System.out.println("User has no blogs!");
		} else {
			while (true) {
				// Get comments for each blog in which the user is admin
				for(Blog blog : blogs) {
					if (blog != null) {
						moderateBlog(user, blog);
					}
				}
				// Wait 4 seconds and retry!
				Thread.sleep(4000);
			}
		}
	}
	
	public static void moderateBlog(User user, Blog blog) throws Exception {
		List<Comment> comments = CommentDao.getCommentsApproved(user, blog);
		for(Comment comment : comments) {
			if (comment != null) {
				System.out.println("THIS MESSAGE SHOULD BE SENT TO KEEPCON PLATFORM:");
				System.out.println(comment);
				if (REJECT_ALL) {
					System.out.println("SUPPOSE THEY ARE ALL REJECTED");
					if (CommentDao.setCommentSpam(user, blog, comment)) {
						System.out.println("Comment " + comment.getId() + " succesfully deleted.");
					} else {
						System.out.println("There was an error deleting comment " + comment.getId() + ".");
					}
				} else {
					System.out.println("SUPPOSE THEY ARE ALL OK");
					if (CommentDao.setCommentApproved(user, blog, comment)) {
						System.out.println("Comment " + comment.getId() + " succesfully approved.");
					} else {
						System.out.println("There was an error approving comment " + comment.getId() + ".");
					}
				}
			}
		}	
	}
	
}
