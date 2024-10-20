package com.clinic.models;

import java.util.Date;
import java.util.List;

public class PatientDetails {
	private int id;
	private int patient_id;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String phoneNo;
	private String disease;
	private String appointment_date;
	private String status;
	private List<Prescription> prescription;
	public PatientDetails() {
		super();
		// TODO Auto-generated constructor stub
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

	public PatientDetails(int patient_id, String name, String gender, int age, String address, String phoneNo,
			String disease, String appointment_date, String status, List<Prescription> prescription) {
		super();
		this.patient_id = patient_id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phoneNo = phoneNo;
		this.disease = disease;
		this.appointment_date = appointment_date;
		this.status = status;
		this.prescription = prescription;
	}

	public PatientDetails(int id, int patient_id, String disease, String appointment_date, String status,
			List<Prescription> prescription) {
		super();
		this.id = id;
		this.patient_id = patient_id;
		this.disease = disease;
		this.appointment_date = appointment_date;
		this.status = status;
		this.prescription = prescription;
	}
	
	public PatientDetails(int patient_id, String disease, String appointment_date, String status,
			List<Prescription> prescription) {
		super();
		this.patient_id = patient_id;
		this.disease = disease;
		this.appointment_date = appointment_date;
		this.status = status;
		this.prescription = prescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Prescription> getPrescription() {
		return prescription;
	}
	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}
	
}
