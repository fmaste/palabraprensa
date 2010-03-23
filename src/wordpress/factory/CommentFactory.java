package wordpress.factory;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import wordpress.model.Comment;

public class CommentFactory {
	
	private static final String COMMENT_ID = "comment_id";
	private static final String STATUS = "status";
	private static final String LINK = "link";
	private static final String AUTHOR_URL = "author_url";
	private static final String PARENT = "parent";
	private static final String TYPE = "type";
	private static final String POST_ID = "post_id";
	private static final String AUTHOR_IP = "author_ip";
	private static final String CONTENT = "content";
	private static final String AUTHOR = "author";
	private static final String AUTHOR_EMAIL = "author_email";
	private static final String DATE_CREATED = "date_created_gmt";
	private static final String USER_ID = "user_id";
	private static final String POST_TITLE = "post_title";

	public static Comment create (Date dateCreated, String userId, String commentId, String parent,
			String status, String content, String link, String postId, String postTitle, String author,
			String authorUrl, String authorEmail, String authorIp) {
		
		if (dateCreated == null || userId == null || commentId == null || parent == null ||
				status == null || content == null || link == null || postId == null || postTitle == null ||
				author == null || authorUrl == null || authorEmail == null || authorIp == null) {
			return null;
		}
		
		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setDateCreated(dateCreated);
		comment.setCommentId(commentId);
		comment.setParent(parent);
		comment.setStatus(status);
		comment.setContent(content);
		comment.setLink(link);
		comment.setPostId(postId);
		comment.setPostTitle(postTitle);
		comment.setAuthor(author);
		comment.setAuthorUrl(authorUrl);
		comment.setAuthorEmail(authorEmail);
		comment.setAuthorIp(authorIp);
		return comment;
	}

	public static Comment create(HashMap<String, Object> map) {
		
		Date dateCreated = null;	// (ISO.8601, always GMT)
		String userId = null;
		String commentId = null;
		String parent = null;
		String status = null;
		String content = null;
		String link = null;
		String postId = null;
		String postTitle = null;
		String author = null;
		String authorUrl = null;
		String authorEmail = null;
		String authorIp = null;
			
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();

			if (key.equals(COMMENT_ID)) {
				commentId = map.get(key).toString();
			} else if(key.equals(STATUS)) {
				status = map.get(key).toString();
			} else if(key.equals(LINK)) {
				link = map.get(key).toString();
			} else if(key.equals(AUTHOR_URL)) {
				authorUrl = map.get(key).toString();
			} else if(key.equals(PARENT)) {
				parent = map.get(key).toString();
			} else if(key.equals(POST_ID)) {
				postId = map.get(key).toString();
			} else if(key.equals(AUTHOR_IP)) {
				authorIp = map.get(key).toString();
			} else if(key.equals(CONTENT)) {
				content = map.get(key).toString();
			} else if(key.equals(AUTHOR)) {
				author = map.get(key).toString();
			} else 	if (key.equals(AUTHOR_EMAIL)) {
				authorEmail = map.get(key).toString();
			} else if(key.equals(DATE_CREATED)) {
				dateCreated = (Date) map.get(key);
			} else if(key.equals(USER_ID)) {
				userId = map.get(key).toString();
			}  else if(key.equals(POST_TITLE)) {
				postTitle = map.get(key).toString();
			} else if (key.equals(TYPE)) {
				// do nothing
			}else {
				return null;
			}
		}
		return CommentFactory.create(dateCreated, userId, commentId, parent,
				status, content, link, postId, postTitle, author,
				authorUrl, authorEmail, authorIp);
	}
}
