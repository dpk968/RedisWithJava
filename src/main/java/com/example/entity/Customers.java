package com.example.entity;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customers implements Serializable{
	
	@Id
	private int customerId;
	
	private String name;
	private String email;
	private String password;
	private String shippingAddress;
	private String purchaseHistory;

	public Customers() {
	}

	public Customers(int customerId, String name, String email, String password, String shippingAddress,
			String purchaseHistory) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.purchaseHistory = purchaseHistory;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(String purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", shippingAddress=" + shippingAddress + ", purchaseHistory=" + purchaseHistory + "]";
	}

}
