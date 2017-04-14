package com.prizy.store.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.prizy.dao.AbstractUUIDIdentityDAO;
@Entity
@Table(name="ADDRESS")
public class AddressDAO extends AbstractUUIDIdentityDAO implements Serializable{

	private static final long serialVersionUID = 3304961203625889507L;
	@Column
	private String line1;
	@Column
	private String line2;
	@Column
	private String line3;
	@Column
	private String pincode;
	@Column
	private String latitude;
	@Column
	private String longitude;
	@Column
	private Integer storeId;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
	@Override
	public String toString() {
		return "AddressDAO [line1=" + line1 + ", line2=" + line2 + ", line3=" + line3 + ", pincode=" + pincode
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", storeId=" + storeId + "]";
	}
	
	
}
