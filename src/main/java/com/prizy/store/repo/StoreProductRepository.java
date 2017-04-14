package com.prizy.store.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prizy.store.dao.StoreProductDAO;
public interface StoreProductRepository extends CrudRepository<StoreProductDAO, Integer> {
	@Query(value="SELECT * from store_product where store_uuid=:#{#storeUuid.toString()}", nativeQuery=true)
	List<StoreProductDAO> findByStoreUuid(@Param("storeUuid") UUID storeUuid);
	@Query(value="SELECT * from store_product where product_uuid=:#{#productUuid.toString()}", nativeQuery=true)
	List<StoreProductDAO> findByProductUuid(@Param("productUuid") UUID productUuid);
	@Query(value="SELECT * from store_product where store_uuid=:#{#storeUuid.toString()} and product_uuid=:#{#productUuid.toString()}", nativeQuery=true)
	StoreProductDAO findByStoreUuidAndProductUuid(UUID storeUuid, UUID productUuid);
}
