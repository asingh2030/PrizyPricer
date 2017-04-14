package com.prizy.product.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.prizy.product.dao.ProductDAO;
public interface ProductRepository extends CrudRepository<ProductDAO, Integer> {
	ProductDAO findByBarCode(String barcode);
	ProductDAO findByUuid(UUID uuid);
	ProductDAO findByModel(String model);
}
