package com.clinic.models;

public class Pharmaceutical {
	private int id;
	private String name;
	private String vendor;
	private String category;
	private int qty;
	
	public Pharmaceutical() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Pharmaceutical(int id, int qty) {
		super();
		this.id = id;
		this.qty = qty;
	}

	public Pharmaceutical(int id, String name, String vendor, String category, int qty) {
		super();
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.category = category;
		this.qty = qty;
	}

	public Pharmaceutical(String name, String vendor, String category, int qty) {
		super();
		this.name = name;
		this.vendor = vendor;
		this.category = category;
		this.qty = qty;
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

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}		
}
