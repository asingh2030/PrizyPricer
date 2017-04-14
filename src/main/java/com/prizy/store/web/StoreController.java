package com.prizy.store.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.product.web.Responses;
import com.prizy.store.dto.StoreInputDTO;
import com.prizy.store.dto.StorePorductDTO;
import com.prizy.store.dto.StoreResponseDTO;
import com.prizy.store.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="api/v1/store", name="StoreController")
@Api(value = "api/v1/store")
public class StoreController {
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create Store.")
	public ResponseEntity<StoreResponseDTO> create(@RequestBody StoreInputDTO dto) throws ObjectAlreadyExistException{
		dto.setUuid(UUID.randomUUID());
		StoreResponseDTO response = storeService.create(dto);
		return Responses.ok(response);
	}
	
	@RequestMapping(path="/{uuid}", method=RequestMethod.PUT)
	@ApiOperation(value = "Update store details.")
	public ResponseEntity<StoreResponseDTO> update(@PathVariable("uuid") final UUID uuid, 
			@RequestBody StoreInputDTO dto) throws ObjectNotFoundException{
		StoreResponseDTO response = storeService.update(uuid, dto);
		return Responses.ok(response);
	}
	
	@RequestMapping(path="/{uuid}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete store.")
	public ResponseEntity<Boolean> delete(@PathVariable("uuid") final UUID uuid) throws ObjectNotFoundException{
		return Responses.ok(storeService.delete(uuid));
	}
	
	@RequestMapping(path="/{uuid}", method=RequestMethod.GET)
	@ApiOperation(value = "find store by uuid.")
	public ResponseEntity<StoreResponseDTO> findOne(@PathVariable("uuid") final UUID uuid) throws ObjectNotFoundException{
		StoreResponseDTO response = storeService.findByUUID(uuid);
			
		return Responses.ok(response);
	}

	@RequestMapping(path="/{uuid}/product", method=RequestMethod.PUT)
	@ApiOperation(value = "Add product to store.")
	public ResponseEntity<Boolean> addProduct(@PathVariable("uuid") UUID storeUUID, @RequestBody StorePorductDTO products){
			return Responses.ok(storeService.addProducts(storeUUID, products.getProducts()));
		
	}
}
