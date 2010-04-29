package palabraprensa.model.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Wordpress {

	// Data
	public static String USER_NAME = "keepcontest";
	public static String USER_PASS = "password";
	
	public static String XML_RPC_SERVER = "http://" + USER_NAME + ".wordpress.com/xmlrpc.php";
	
	// Methods
	public static String GET_USERS_BLOGS = "wp.getUsersBlogs";
	public static String GET_COMMENTS = "wp.getComments";
	public static String EDIT_COMMENT = "wp.editComment";
	
	// For testing
	public static Date getDateFromXDaysAgo(int X) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -X);
		return cal.getTime();
	}
}
