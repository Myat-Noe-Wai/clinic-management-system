package com.clinic.models;

public class Prescription {
	private int id;
	private int patient_details_id;
	private String medicine;
	private int quantity;
	private String instruction;
	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prescription(int patient_details_id, String medicine, int quantity, String instruction) {
		super();
		this.patient_details_id = patient_details_id;
		this.medicine = medicine;
		this.quantity = quantity;
		this.instruction = instruction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_details_id() {
		return patient_details_id;
	}
	public void setPatient_details_id(int patient_details_id) {
		this.patient_details_id = patient_details_id;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}
