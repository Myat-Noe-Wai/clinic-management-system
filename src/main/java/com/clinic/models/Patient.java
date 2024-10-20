package com.clinic.models;

public class Patient {
	private int id;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String phoneNo;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Patient(int id, String name, String gender, int age, String address, String phoneNo) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public Patient(String name, String gender, int age, String address, String phoneNo) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phoneNo = phoneNo;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
