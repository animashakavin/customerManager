package com.gogo.bean;

import org.springframework.boot.orm.jpa.EntityScan;


public class Customer {
	
	private String name;
	private String address;
	private String email;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "{ \"name\":\""+this.name+"\",\"address\":\""+this.address+"\",\"email\":\""+this.email+"\"}";
	}
	@Override
	public int hashCode(){
		int hashCode = 7 * this.name.toUpperCase().hashCode();
		return hashCode ;
	}
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Customer customer = (Customer) object;
			if (this.name.equalsIgnoreCase(customer.name)) {
				result = true;
			}
		}
		return result;

	}
	
	
	

}
