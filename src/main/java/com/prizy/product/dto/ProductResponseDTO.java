package com.prizy.product.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProductResponseDTO implements Serializable{
	private static final long serialVersionUID = -8597054812589375557L;
	private UUID uuid;
	private String barCode;
	private String name;
	private List<Serializable> storeList;
	private String model;
	private String description;
	private Double averagePrice;
	private Double lowestPrice;
	private Double highestPrice;
	private Double idealPrice;	
	
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
	public List<Serializable> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<Serializable> storeList) {
		this.storeList = storeList;
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
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Double getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public Double getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(Double highestPrice) {
		this.highestPrice = highestPrice;
	}
	public Double getIdealPrice() {
		return idealPrice;
	}
	public void setIdealPrice(Double idealPrice) {
		this.idealPrice = idealPrice;
	}
	@Override
	public String toString() {
		return "ProductResponseDTO [uuid=" + uuid + ", barCode=" + barCode + ", name=" + name + ", storeList="
				+ storeList + ", model=" + model + ", description=" + description + ", averagePrice=" + averagePrice
				+ ", lowestPrice=" + lowestPrice + ", highestPrice=" + highestPrice + ", idealPrice=" + idealPrice
				+ "]";
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}
