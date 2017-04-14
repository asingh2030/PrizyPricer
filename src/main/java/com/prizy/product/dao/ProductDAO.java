package com.prizy.product.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.prizy.dao.AbstractUUIDIdentityDAO;
import com.prizy.dto.BARCode;

@Entity
@Table(name="PRODUCT")
public class ProductDAO extends AbstractUUIDIdentityDAO implements Serializable, BARCode{

	private static final long serialVersionUID = -2762145322987858311L;
	@Column
	private String name;
	@Column
	private String model;
	@Column
	private String manufacturer;
	@ElementCollection
	@CollectionTable(name="price", joinColumns=@JoinColumn(name="PRODUCT_ID"))
	@Column(name="PRICE")
	private Set<Double> priceSet = new HashSet<>();
	@Column
	private String description;
	@Column
	private String  notes;
	@Column
	private String barCode;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Double> getPriceSet() {
		return priceSet;
	}
	public void setPrice(Double price) {
		this.priceSet.add(price);
	}
	
	public String getBarcode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Override
	public String toString() {
		return "ProductDAO [name=" + name + ", model=" + model + ", manufacturer=" + manufacturer + ", priceSet="
				+ priceSet + ", description=" + description + ", notes=" + notes + ", barCode=" + barCode + "]";
	}
}
