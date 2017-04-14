package com.prizy.store.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.store.dao.AddressDAO;
import com.prizy.store.dao.StoreDAO;
import com.prizy.store.dao.StoreProductDAO;
import com.prizy.store.dto.Address;
import com.prizy.store.dto.StoreInputDTO;
import com.prizy.store.dto.StoreResponseDTO;
import com.prizy.store.repo.StoreProductRepository;
import com.prizy.store.repo.StoreRepository;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private StoreProductRepository storeProductRepository;
	
	@Override
	public StoreResponseDTO create(StoreInputDTO dto) throws ObjectAlreadyExistException {
		StoreDAO existDAO = storeRepository.findByUuid(dto.getUuid());
		if(existDAO != null){
			throw new ObjectAlreadyExistException("Store already exist. store uuid = "+dto.getUuid());
		}
		StoreDAO dao = convertDTOToDAO(dto);
		final StoreDAO savedDAO= storeRepository.save(dao);
		Set<UUID> products = dto.getProducts();
		if(products != null && !products.isEmpty()){
			products.forEach(productUUID -> saveStoreProduct(savedDAO.getUuid(), productUUID));
			
		}
		return convertDAOToDTO(dao);
	}

	private StoreProductDAO saveStoreProduct(UUID storeUUID, UUID productUUID) {
		StoreProductDAO dao = storeProductRepository.findByStoreUuidAndProductUuid(storeUUID, productUUID);
		if(dao == null){
			StoreProductDAO spDAO = new StoreProductDAO();
			spDAO.setProductUUID(productUUID);
			spDAO.setStoreUUID(storeUUID);
			dao = storeProductRepository.save(spDAO);
		}
		return dao;
	}

	private StoreResponseDTO convertDAOToDTO(StoreDAO dao) {
		StoreResponseDTO dto = new StoreResponseDTO();
		dto.setUuid(dao.getUuid());
		dto.setName(dao.getName());
		AddressDAO address = dao.getAddress();
		if(address != null){
			dto.setAddress(convertAddressDAOToDTO(address));
		}
		List<StoreProductDAO> spDAOList = storeProductRepository.findByStoreUuid(dao.getUuid());
		if(spDAOList != null && !spDAOList.isEmpty()){
			Set<UUID> products = new HashSet<>();
			spDAOList.forEach(spDAO -> products.add(spDAO.getProductUUID()));
			dto.setProducts(products);
		}
		return dto;
	}

	private Address convertAddressDAOToDTO(AddressDAO address) {
		Address add = new Address();
		add.setLine1(address.getLine1());
		add.setLine2(address.getLine2());
		add.setLine3(address.getLine3());
		add.setLatitude(address.getLatitude());
		add.setLongitude(address.getLongitude());
		add.setPincode(address.getPincode());
		return add;
	}

	private StoreDAO convertDTOToDAO(StoreInputDTO dto) {
		StoreDAO dao = new StoreDAO();
		dao.setName(dto.getName());
		dao.setUuid(dto.getUuid());
		if(dto.getAddress() != null){
			dao.setAddress(convertAddressDTOToDAO(dto.getAddress()));
		}
		return dao;
	}

	private AddressDAO convertAddressDTOToDAO(Address address) {
		AddressDAO dao = new AddressDAO();
		dao.setUuid(address.getUuid());
		dao.setLatitude(address.getLatitude());
		dao.setLine1(address.getLine1());
		dao.setLine2(address.getLine2());
		dao.setLine3(address.getLine3());
		dao.setLongitude(address.getLongitude());
		dao.setPincode(address.getPincode());
		return dao;
	}

	@Override
	public boolean delete(UUID uuid) throws ObjectNotFoundException {
		List<StoreProductDAO> spDAOList = storeProductRepository.findByStoreUuid(uuid);
		if(spDAOList != null && !spDAOList.isEmpty()){
			storeProductRepository.delete(spDAOList);
		}
		StoreDAO storeDAO = storeRepository.findByUuid(uuid);
		if(storeDAO == null){
			throw new ObjectNotFoundException("Given store not found uuid "+uuid);
		}
		storeRepository.delete(storeDAO);
		return true;
	}

	@Override
	public StoreResponseDTO update(UUID uuid, StoreInputDTO dto) throws ObjectNotFoundException {
		StoreDAO dao = storeRepository.findByUuid(uuid);
		if(dao == null){
			throw new ObjectNotFoundException("Store not exist with given UUID = "+uuid);
		}
		StoreDAO storeDAO = convertDTOToDAO(dto);
		storeDAO.setId(dao.getId());
		storeDAO.setUuid(dao.getUuid());
		StoreDAO savedDAO = storeRepository.save(storeDAO);
		Set<UUID> products = dto.getProducts();
		if(products!= null && !products.isEmpty()){
			products.forEach(productUUID -> saveStoreProduct(savedDAO.getUuid(), productUUID));
		}
		return convertDAOToDTO(savedDAO);
	}

	@Override
	public boolean addProducts(UUID storeUUID, Set<UUID> productUUIDList) {
		productUUIDList.forEach(productUUID -> {
				saveStoreProduct(storeUUID, productUUID);
		});
		return true;
	}

	@Override
	public StoreResponseDTO findByName(String storeName) throws ObjectNotFoundException {
		StoreDAO dao = storeRepository.findByName(storeName);
		if(dao == null){
			throw new ObjectNotFoundException("Given store not found by name = "+storeName);
		}
		return convertDAOToDTO(dao);
	}

	@Override
	public StoreResponseDTO findByUUID(UUID uuid) throws ObjectNotFoundException {
		StoreDAO dao = storeRepository.findByUuid(uuid);
		if(dao == null){
			throw new ObjectNotFoundException("Given store not found by uuid = "+uuid);
		}
		return convertDAOToDTO(dao);
	}

	@Override
	public List<StoreResponseDTO> findAllStores(int limit){
		Iterable<StoreDAO> daoList = storeRepository.findAll();
		if(daoList == null){
			Collections.emptyList();
		}
		List<StoreResponseDTO> responseList = new ArrayList<>();
		daoList.forEach(dao -> responseList.add(convertDAOToDTO(dao)));
		return responseList;
	}

	@Override
	public List<StoreResponseDTO> findByLocation(String latitude, String longitude) {
		// TODO Auto-generated method stub
		return null;
	}

}
