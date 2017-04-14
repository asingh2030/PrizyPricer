package com.prizy.store.dto;

import java.util.UUID;

public class Address {
	private UUID uuid;
	private String line1;
	private String line2;
	private String line3;
	private String pincode;
	private String latitude;
	private String longitude;
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
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Address [uuid=" + uuid + ", line1=" + line1 + ", line2=" + line2 + ", line3=" + line3 + ", pincode="
				+ pincode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
