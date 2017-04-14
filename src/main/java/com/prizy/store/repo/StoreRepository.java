package com.prizy.store.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prizy.store.dao.StoreDAO;
public interface StoreRepository extends CrudRepository<StoreDAO, Integer> {
	StoreDAO findByUuid(UUID uuid);
	StoreDAO findByName(String name);
	@Query(value="SELECT s.* from Store s inner join Address a on s.id=a.store_id where latitude=:#{#latitude.toString()} "
			+ " and longitude=:#{#longitude.toString()}", nativeQuery=true)
	StoreDAO findByLatitudeAndLongitude(@Param("latitude") String latitude,@Param("longitude") String longitude);
}
