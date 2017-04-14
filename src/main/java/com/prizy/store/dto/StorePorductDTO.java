package com.prizy.store.dto;

import java.util.List;
import java.util.UUID;

public class StorePorductDTO {
	private List<UUID> products;

	public List<UUID> getProducts() {
		return products;
	}

	public void setProducts(List<UUID> products) {
		this.products = products;
	}
	
}
