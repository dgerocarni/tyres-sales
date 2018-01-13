package com.tyres.sales.model;

public class SalesByKey {
	
	private String key;
	private int quantity;
	
	
	public SalesByKey() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SalesByKey(String key, int quantity) {
		super();
		this.key = key;
		this.quantity = quantity;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	

}
