package com.clinic.models;

public class Doctor {
	private int id;
	private String name;
	private String email;
	private String password;
	private String qualification;
	private String specialist;
	private String phone_no;
	private String address;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Doctor(int id, String name, String email, String password, String qualification, String specialist,
			String phone_no, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.specialist = specialist;
		this.phone_no = phone_no;
		this.address = address;
	}

	public Doctor(String name, String email, String password, String qualification, String specialist, String phone_no,
			String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.specialist = specialist;
		this.phone_no = phone_no;
		this.address = address;
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
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
