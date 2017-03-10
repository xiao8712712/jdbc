package www.bean;

public class User {
	private String user_name;
	private String user_pwd;
	public String getName() {
		return user_name;
	}
	public void setName(String name) {
		this.user_name = name;
	}
	public String getPwd() {
		return user_pwd;
	}
	public void setPwd(String pwd) {
		this.user_pwd = pwd;
	}
	@Override
	public String toString() {
		return "name=" + user_name + ", pwd=" + user_pwd ;
	}
	public User(String name, String pwd) {
		
		this.user_name = name;
		this.user_pwd = pwd;
	}
	public User() {
		
	}
	

}
