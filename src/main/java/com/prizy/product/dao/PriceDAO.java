package com.prizy.product.dao;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.prizy.dao.AbstractUUIDIdentityDAO;

@Entity
@Table(name="PRODUCT_PRICE")
public class PriceDAO extends AbstractUUIDIdentityDAO implements Serializable{

	private static final long serialVersionUID = -2762145322987858311L;
	@Column(name="PRODUCT_UUID")
	private UUID productUUID;
	@Column(name="PRICE")
	private float price;
	public UUID getProductUUID() {
		return productUUID;
	}
	public void setProductUUID(UUID productUUID) {
		this.productUUID = productUUID;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PriceDAO [productUUID=" + productUUID + ", price=" + price + "]";
	}
}
