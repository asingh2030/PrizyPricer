package com.prizy.product.dto;

import java.io.Serializable;
import java.util.UUID;

import com.prizy.dto.BARCode;

public class ProductInputDTO implements Serializable, BARCode{
	private static final long serialVersionUID = -5435668378578451899L;
	private String name;
	private UUID uuid;
	private UUID storeUUID;
	private String model;
	private String description;
	private Double price;
	private String notes;
	private String barcode;
	private String manufacturer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getStoreUUID() {
		return storeUUID;
	}
	public void setStoreUUID(UUID storeUUID) {
		this.storeUUID = storeUUID;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "ProductInputDTO [name=" + name + ", storeUUID=" + storeUUID + ", model=" + model + ", description="
				+ description + ", price=" + price + ", notes=" + notes + ", barcode=" + barcode + ", monufacturer="
				+ manufacturer + "]";
	}
	@Override
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
