package org.mdissjava.mdisscore.model.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String street;
	private String city;	
	private String zip;	
	private String country;
	private String state;
	
	public Address(){}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	

}
