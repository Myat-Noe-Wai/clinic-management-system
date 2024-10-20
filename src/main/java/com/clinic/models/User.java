package com.clinic.models;

public class User {
	private int id;
	private String name;
	private String role;
	private String email;
	private String password;
	private String address;
	private String phone_no;
	private String qualification;
	private String specialist;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public User(int id, String name, String role, String email, String password, String address, String phone_no,
			String qualification, String specialist) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone_no = phone_no;
		this.qualification = qualification;
		this.specialist = specialist;
	}


	public User(String name, String role, String email, String password, String address, String phone_no,
			String qualification, String specialist) {
		super();
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone_no = phone_no;
		this.qualification = qualification;
		this.specialist = specialist;
	}

	public User(String name, String role, String email, String password, String address, String phone_no,
			String qualification) {
		super();
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone_no = phone_no;
		this.qualification = qualification;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	
}
