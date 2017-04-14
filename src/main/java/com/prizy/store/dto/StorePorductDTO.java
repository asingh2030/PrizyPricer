package com.prizy.store.dto;

import java.util.Set;
import java.util.UUID;

public class StorePorductDTO {
	private Set<UUID> products;

	public Set<UUID> getProducts() {
		return products;
	}

	public void setProducts(Set<UUID> products) {
		this.products = products;
	}
	
}
