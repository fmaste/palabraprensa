package wordpress.model;

public class Admin implements Credentials {
	
	private String userName;
	private String userPass;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getUserPass() {
		return userPass;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}
