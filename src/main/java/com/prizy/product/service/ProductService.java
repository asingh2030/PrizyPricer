package com.prizy.product.service;

import java.util.List;

import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.product.dto.ProductInputDTO;
import com.prizy.product.dto.ProductResponseDTO;

public interface ProductService {
	ProductResponseDTO findByBarCode(String barcode) throws ObjectNotFoundException;
	ProductResponseDTO create(ProductInputDTO dto) throws ObjectAlreadyExistException, ObjectNotFoundException;
	boolean delete(String barcode) throws ObjectNotFoundException;
	ProductResponseDTO update(String barcode, ProductInputDTO dto) throws ObjectNotFoundException;
	ProductResponseDTO findByModel(String model) throws ObjectNotFoundException;
	List<ProductResponseDTO> findAll(int limit);
	Boolean addPrice(String barcode, Double price) throws ObjectNotFoundException;
}
