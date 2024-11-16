package com.cmu;

public class Address {

	private String line1;
	private String line2;
	private String city;
	private String state;
	private String ZIPCode;
	
	public Address(String line1, String line2, String city, String state, String ZIPCode) {
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.ZIPCode = ZIPCode;
	}
	
	public Address(String line1, String city, String state, String ZIPCode) {
		this(line1, null, city, state, ZIPCode);
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZIPCode() {
		return ZIPCode;
	}

	public void setZIPCode(String ZIPCode) {
		this.ZIPCode = ZIPCode;
	}	
}
