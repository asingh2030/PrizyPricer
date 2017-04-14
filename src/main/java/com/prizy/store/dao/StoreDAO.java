package com.prizy.store.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.prizy.dao.AbstractUUIDIdentityDAO;
@Entity
@Table(name="STORE")
public class StoreDAO extends AbstractUUIDIdentityDAO implements Serializable{

	private static final long serialVersionUID = -8387035693753283770L;
	@Column
	private String name;
	@Column
	private AddressDAO address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AddressDAO getAddress() {
		return address;
	}
	public void setAddress(AddressDAO address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StoreDAO [name=" + name + ", address=" + address + "]";
	}
}
