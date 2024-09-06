package admin.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "administrator")
public class AdminBean {
	
	@Id @Column(name = "adminID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminID;
	
	@Column(name = "email" , unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;

	public AdminBean() {
	}

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
