package model;

import java.sql.Timestamp;

public class User {
	private int userId;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private String phone;
	private String address;
	private int role;
	private Timestamp created_at;
	private Timestamp modified_at;
	
	public User() {
		super();
	}
	
	
	public User(int userId, String email, String password, String fullname, String avatar, String phone, String address,
			int role) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}



	public User(String email, String password, String fullname, String avatar, String phone, String address, int role) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	public User(int userId, String email, String password, String fullname, String avatar, String phone,
			String address, int role, Timestamp created_at, Timestamp modified_at) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getModified_at() {
		return modified_at;
	}

	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", fullname=" + fullname
				+ ", avatar=" + avatar + ", phone=" + phone + ", address=" + address + ", role=" + role
				+ ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}
	
	
	

	
	
}
