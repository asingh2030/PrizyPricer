package com.prizy.product.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.price.service.IdealPriceService;
import com.prizy.product.dao.ProductDAO;
import com.prizy.product.dto.ProductInputDTO;
import com.prizy.product.dto.ProductResponseDTO;
import com.prizy.product.repo.ProductRepository;
import com.prizy.product.service.ProductService;
import com.prizy.store.dao.StoreProductDAO;
import com.prizy.store.dto.StoreResponseDTO;
import com.prizy.store.repo.StoreProductRepository;
import com.prizy.store.service.StoreService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreProductRepository storeProductRepository;
	@Autowired
	private StoreService storeService;
	@Autowired
	@Value("#{idealPriceService}")
	private IdealPriceService idealPriceService;
	@Transactional
	@Override
	public List<ProductResponseDTO> findAll(int limit) {
		Iterable<ProductDAO> allProducts = productRepository.findAll();
		List<ProductResponseDTO> productList = new ArrayList<>();
		if(allProducts != null ){
			allProducts.forEach(product -> productList.add(convertDAOToDTO(product)));
			return productList;
		}else{
			return Collections.emptyList();
		}
	}

	private ProductResponseDTO convertDAOToDTO(ProductDAO product) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setModel(product.getModel());
		dto.setName(product.getName());
		dto.setBarCode(product.getBarcode());
		dto.setUuid(product.getUuid());
		Set<Double> priceList = product.getPriceSet();
		if(priceList != null && !priceList.isEmpty()){
			dto.setHighestPrice(getHighestPrice(priceList));
			dto.setAveragePrice(getAveragePrice(priceList));
			dto.setLowestPrice(getLowestPrice(priceList));
			dto.setIdealPrice(getIdealPrice(priceList));
			
		}
		return dto;
	}

	private Double getIdealPrice(Set<Double> priceList) {
		return idealPriceService.calculate(priceList);
	}

	private Double getLowestPrice(Set<Double> priceList) {
		OptionalDouble min = priceList.stream().mapToDouble(Double::doubleValue).min();
		return min.isPresent() ? min.getAsDouble() : 0;
	}

	private Double getAveragePrice(Set<Double> priceList) {
		OptionalDouble average = priceList.stream().mapToDouble(Double::doubleValue).average();
		return average.isPresent() ? average.getAsDouble() : 0;
	}

	private Double getHighestPrice(Set<Double> priceList) {
		OptionalDouble max = priceList.stream().mapToDouble(Double::doubleValue).max();
		return max.isPresent() ? max.getAsDouble() : 0;
	}

	@Override
	public ProductResponseDTO findByBarCode(String barcode) throws ObjectNotFoundException {
		ProductDAO productDAO = productRepository.findByBarCode(barcode);
		if(productDAO == null){
			throw new ObjectNotFoundException("Product not found barcode = "+barcode);
		}
		return convertDAOToDTO(productDAO);
	}
	@Transactional
	@Override
	public ProductResponseDTO create(ProductInputDTO dto) throws ObjectNotFoundException, ObjectAlreadyExistException {
		ProductDAO existingDAO = productRepository.findByBarCode(dto.getBarcode());
		if(existingDAO != null){
			throw new ObjectAlreadyExistException("Given product already exist. Barcode ="+dto.getBarcode());
		}
		ProductDAO dao = convertDTOToDAO(dto);
		dao = productRepository.save(dao);
		if(dto.getStoreUUID() != null){
			saveStoreProduct(dto, dao);
		}
		return convertDAOToDTO(dao);
	}

	private void saveStoreProduct(ProductInputDTO dto, ProductDAO dao) throws ObjectNotFoundException {
		StoreResponseDTO storeDAO = storeService.findByUUID(dto.getStoreUUID());
		if(storeDAO == null){
			throw new ObjectNotFoundException("Given store not present "+dto.getStoreUUID());
		}
		StoreProductDAO spDAO = new StoreProductDAO();
//		StoreProductKey key = new StoreProductKey();
		spDAO.setProductUUID(dao.getUuid());
		spDAO.setStoreUUID(dto.getStoreUUID());
//		spDAO.setKey(key);
		storeProductRepository.save(spDAO);
	}

	private ProductDAO convertDTOToDAO(ProductInputDTO dto) {
		ProductDAO dao = new ProductDAO();
		dao.setUuid(dto.getUuid());
		dao.setName(dto.getName());
		dao.setBarCode(dto.getBarcode());
		dao.setManufacturer(dto.getManufacturer());
		dao.setModel(dto.getModel());
		dao.setUuid(dto.getUuid());
		dao.setNotes(dto.getNotes());
		dao.setDescription(dto.getDescription());
		dao.setPrice(dto.getPrice());
		return dao;
	}
	@Transactional
	@Override
	public boolean delete(String barcode) throws ObjectNotFoundException {
		ProductDAO dao = productRepository.findByBarCode(barcode);
		if(dao == null){
			throw new ObjectNotFoundException("Product not found barcode = "+barcode);
		}
		List<StoreProductDAO> spDAO = storeProductRepository.findByProductUuid(dao.getUuid());
		storeProductRepository.delete(spDAO);
		productRepository.delete(dao);
		return true;
	}
	@Transactional
	@Override
	public ProductResponseDTO update(String barcode, ProductInputDTO dto) throws ObjectNotFoundException {
		ProductDAO productDAO = productRepository.findByBarCode(barcode);
		if(productDAO == null){
			throw new ObjectNotFoundException("Product not found barcode = "+barcode);
		}
		ProductDAO dao = convertDTOToDAO(dto);
		dao.setId(productDAO.getId());
		dao.setBarCode(productDAO.getBarcode());
		dao.setUuid(productDAO.getUuid());
		dao.getPriceSet().addAll(productDAO.getPriceSet());
		ProductDAO savedDAO = productRepository.save(dao);
		if(dto.getStoreUUID() != null){
			saveStoreProduct(dto, savedDAO);
		}
		return convertDAOToDTO(savedDAO);
	}
	@Transactional
	@Override
	public ProductResponseDTO findByModel(String model) {
		ProductDAO dao = productRepository.findByModel(model);
		if(dao != null){
			return convertDAOToDTO(dao);
		}
		return null;
	}
	
	@Transactional
	@Override
	public Boolean addPrice(String barcode, Double price) throws ObjectNotFoundException{
		ProductDAO dao = productRepository.findByBarCode(barcode);
		if(dao == null){
			throw new ObjectNotFoundException("Given product not found barcode = "+barcode);
		}
		dao.getPriceSet().add(price);
		productRepository.save(dao);
		return true;
	}
}
