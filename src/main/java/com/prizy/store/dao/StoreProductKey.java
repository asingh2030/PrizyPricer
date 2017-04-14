package com.prizy.store.dao;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StoreProductKey implements Serializable{
	private static final long serialVersionUID = 6452454314446346906L;
	@Column
	private UUID storeUUID;
	@Column
	private UUID productUUID;
	public UUID getStoreUUID() {
		return storeUUID;
	}
	public void setStoreUUID(UUID storeUUID) {
		this.storeUUID = storeUUID;
	}
	public UUID getProductUUID() {
		return productUUID;
	}
	public void setProductUUID(UUID productUUID) {
		this.productUUID = productUUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productUUID == null) ? 0 : productUUID.hashCode());
		result = prime * result + ((storeUUID == null) ? 0 : storeUUID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreProductKey other = (StoreProductKey) obj;
		if (productUUID == null) {
			if (other.productUUID != null)
				return false;
		} else if (!productUUID.equals(other.productUUID))
			return false;
		if (storeUUID == null) {
			if (other.storeUUID != null)
				return false;
		} else if (!storeUUID.equals(other.storeUUID))
			return false;
		return true;
	}
	
}