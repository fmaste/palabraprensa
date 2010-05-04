package palabraprensa.model.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Wordpress {	
	// Methods
	public static String GET_USERS_BLOGS = "wp.getUsersBlogs";
	public static String GET_COMMENTS = "wp.getComments";
	public static String EDIT_COMMENT = "wp.editComment";
	public static String DELETE_COMMENT = "wp.deleteComment";
	
	// For testing
	public static Date getDateFromXDaysAgo(int X) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -X);
		return cal.getTime();
	}

}
