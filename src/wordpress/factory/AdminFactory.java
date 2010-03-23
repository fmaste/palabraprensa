package wordpress.factory;

import wordpress.model.Admin;

public class AdminFactory {

	public static Admin create(String userName, String userPass) {
		
		if (userName == null || userPass == null) {
			return null;
		}
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setUserPass(userPass);
		return admin;
	}

}
