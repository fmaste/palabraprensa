package wordpress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import wordpress.dao.BlogDao;
import wordpress.dao.CommentDao;
import wordpress.dao.RequestDao;
import wordpress.factory.RequestFactory;
import wordpress.model.Blog;
import wordpress.model.Comment;
import wordpress.model.Request;
import wordpress.model.constants.Wordpress;

public class Main {
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		while(true) {
			
		try {
			
			// First, get User blogs				
			List<Blog> blogs = BlogDao.getBlogsOwned(Wordpress.USER_NAME, Wordpress.USER_PASS);
			
			// SUPPOSE ALL COMMENTS ARE REJECTED (TESTING)
			boolean rejected = true;
			
			// Get comments for each blog in which the user is admin
			for(Blog blog : blogs) {
				if (blog != null) {
					//Comment[] comments = CommentDao.getComments(blog);
					//Comment[] comments = CommentDao.getCommentsSince(blog, Wordpress.getDateFromXDaysAgo(1));
					Comment[] comments = CommentDao.getCommentsApproved(blog);
					for(int i = 0; i < comments.length; i++) {
						if (comments[i] != null) {
							System.out.println("THIS MESSAGES SHOULD BE SENT TO KEEPCON PLATFORM:");
							System.out.println(comments[i]);
							System.out.println("SUPPOSE THEY ARE ALL REJECTED");
							if (rejected) {
								if (CommentDao.setCommentSpam(blog, comments[i])) {
									System.out.println("Comment: " + comments[i].getContent() + " succesfully deleted");
								} else {
									System.out.println("There was an error deleting the comment: " + comments[i].getContent());
								}
							} else {
								if (CommentDao.setCommentApproved(blog, comments[i])) {
									System.out.println("Comment: " + comments[i].getContent() + " succesfully approved");
								} else {
									System.out.println("There was an error approving the comment: " + comments[i].getContent());
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(4000);
		}
	}
}
