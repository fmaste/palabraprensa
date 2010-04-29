package palabraprensa.factory;

import palabraprensa.model.Admin;

public class AdminFactory {

	public static Admin create(String userName, String userPass) {
		
		if (userName == null || userPass == null) {
			return null;
		}
		Admin admin = new Admin();
		admin.setName(userName);
		admin.setPass(userPass);
		return admin;
	}

}
