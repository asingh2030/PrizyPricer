package com.prizy.store.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class StoreInputDTO implements Serializable{
	private static final long serialVersionUID = 6331004913213153714L;
	private String name;
	private Address address;
	private Set<UUID> products;
	private UUID uuid;
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StoreInputDTO [name=" + name + ", address=" + address + ", products=" + products + ", uuid=" + uuid
				+ "]";
	}
	public Set<UUID> getProducts() {
		return products;
	}
	public void setProducts(Set<UUID> products) {
		this.products = products;
	}
}
