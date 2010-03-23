package wordpress.model.constants;

public class Wordpress {

	// Data
	public static String USER_NAME = "keepcontest";
	public static String USER_PASS = "password";
	
	public static String XML_RPC_SERVER = "http://" + USER_NAME + ".wordpress.com/xmlrpc.php";
	
	// Methods
	public static String GET_USERS_BLOGS = "wp.getUsersBlogs";
	public static String GET_COMMENTS = "wp.getComments";
}
