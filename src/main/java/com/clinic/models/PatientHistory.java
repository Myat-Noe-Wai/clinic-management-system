package com.clinic.models;

import java.util.Date;

public class PatientHistory {
	private int id;
	private int patient_id;
	private String disease;
	private Date appointment_date;
	private String status;
	public PatientHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientHistory(int patient_id, String disease, Date appointment_date, String status) {
		super();
		this.patient_id = patient_id;
		this.disease = disease;
		this.appointment_date = appointment_date;
		this.status = status;
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
	public Date getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
