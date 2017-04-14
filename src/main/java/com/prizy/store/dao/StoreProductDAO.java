package com.prizy.store.dao;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="STORE_PRODUCT")
public class StoreProductDAO implements Serializable{

	private static final long serialVersionUID = -8387035693753283770L;
	@EmbeddedId
	private StoreProductKey key;

	public StoreProductKey getKey() {
		return key;
	}

	public void setKey(StoreProductKey key) {
		this.key = key;
	}
	
}
