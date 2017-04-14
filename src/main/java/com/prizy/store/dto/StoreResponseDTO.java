package com.prizy.store.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class StoreResponseDTO implements Serializable{
	private static final long serialVersionUID = -6160630784538329901L;
	private String name;
	private UUID uuid;
	private Address address;
	private Set<Serializable> objects;
	
	public Set<Serializable> getObjects() {
		return objects;
	}
	public void setObjects(Set<Serializable> objects) {
		this.objects = objects;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StoreDTO [name=" + name + ", uuid=" + uuid + ", address=" + address + "]";
	}
}
