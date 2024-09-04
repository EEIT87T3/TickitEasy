package admin.bean;

public class AdminBean {
	private int adminID;
	private String email;
	private String password;
	private String name;

	public AdminBean(int adminID, String email, String password, String name) {
		super();
		this.adminID = adminID;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public AdminBean(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
