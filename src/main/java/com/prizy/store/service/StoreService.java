package com.prizy.store.service;

import java.util.List;
import java.util.UUID;

import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.store.dto.StoreInputDTO;
import com.prizy.store.dto.StoreResponseDTO;

public interface StoreService {
	StoreResponseDTO create(StoreInputDTO dto) throws ObjectAlreadyExistException;
	boolean delete(UUID uuid) throws ObjectNotFoundException;
	StoreResponseDTO update(UUID uuid, StoreInputDTO dto) throws ObjectNotFoundException;
	boolean addProducts(UUID storeUUID, List<UUID> productUUID);
	StoreResponseDTO findByName(String storeName) throws ObjectNotFoundException;
	StoreResponseDTO findByUUID(UUID uuid) throws ObjectNotFoundException;
	List<StoreResponseDTO> findAllStores(int limit) throws ObjectNotFoundException;
	List<StoreResponseDTO> findByLocation(String latitude, String longitude);
}
