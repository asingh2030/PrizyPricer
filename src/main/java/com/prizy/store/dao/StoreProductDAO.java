package com.prizy.store.dao;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.prizy.dao.AbstractIdentityDAO;
@Entity
@Table(name="STORE_PRODUCT")
public class StoreProductDAO extends AbstractIdentityDAO implements Serializable{

	private static final long serialVersionUID = -8387035693753283770L;
	@Type(type = "uuid-char")
    @Column(name = "STORE_UUID", columnDefinition = "CHAR(36)", length = 36)
	@NotNull
    private UUID storeUUID;

	@Type(type = "uuid-char")
    @Column(name = "PRODUCT_UUID", columnDefinition = "CHAR(36)", length = 36)
    @NotNull
    private UUID productUUID;
    
	public UUID getProductUUID() {
		return productUUID;
	}

	public void setProductUUID(UUID productUUID) {
		this.productUUID = productUUID;
	}

	public UUID getStoreUUID() {
		return storeUUID;
	}

	public void setStoreUUID(UUID storeUUID) {
		this.storeUUID = storeUUID;
	}
	
}
