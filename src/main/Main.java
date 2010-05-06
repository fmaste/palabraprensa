package main;

import java.util.Date;
import java.util.List;
import palabraprensa.api.Moderator;
import palabraprensa.model.Comment;

public class Main {
	public static final String WP_USER_NAME = "yourusername";
	public static final String WP_PASS_PASS = "yourpassword";

	public static void main(String[] args) throws Exception {
		Moderator moderator = new Moderator(WP_USER_NAME, WP_PASS_PASS, "http://wordpress.com", "http://keepcontest.wordpress.com");
		Date startDate = new Date();
		while (true) {
			System.out.println("Using: " + startDate);
			Date tmpDate = new Date();
			List<Comment> comments = moderator.getCommentsForPostModeration(startDate); // This can also be pre or all!
			startDate = tmpDate;
			for(Comment comment : comments) {
				System.out.println(comment);
				if (moderator.deleteComment(comment)) { // This can also be spam or approve!
					System.out.println("Comment " + comment.getId() + " was succesfully changed.");
				} else {
					System.out.println("There was an error changing comment " + comment.getId() + ".");
				}
			}
			Thread.sleep(60000);
		}
	}

}
