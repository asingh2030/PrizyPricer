/**
 * 
 */
package com.prizy.product.web;

import java.util.List;
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
import com.prizy.product.dto.ProductInputDTO;
import com.prizy.product.dto.ProductResponseDTO;
import com.prizy.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ashutosh
 *
 */
@RestController
@RequestMapping(path="api/v1/product", name="ProductController")
@Api(value = "api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create product.")
	public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductInputDTO dto) throws ObjectAlreadyExistException, ObjectNotFoundException{
		dto.setUuid(UUID.randomUUID());
		ProductResponseDTO response = productService.create(dto);
		return Responses.ok(response);
	}
	
	@RequestMapping(path="/{barcode}", method=RequestMethod.PUT)
	@ApiOperation(value = "Update product.")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable("barcode") final String barcode, 
			@RequestBody ProductInputDTO dto) throws ObjectNotFoundException{
		ProductResponseDTO response = productService.update(barcode, dto);
		return Responses.ok(response);
	}
	
	@RequestMapping(path="/{barcode}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete product by barcode.")
	public ResponseEntity<Boolean> delete(@PathVariable("barcode") final String barcode) throws ObjectNotFoundException{
		return Responses.ok(productService.delete(barcode));
	}
	
	@RequestMapping(path="/{barcode}", method=RequestMethod.GET)
	@ApiOperation(value = "Find product by barcode.")
	public ResponseEntity<ProductResponseDTO> findOne(@PathVariable("barcode") final String barcode) throws ObjectNotFoundException{
		ProductResponseDTO response = productService.findByBarCode(barcode);
			
		return Responses.ok(response);
	}

	@RequestMapping(path="/model/{model}", method=RequestMethod.GET)
	@ApiOperation(value = "Find product by model.")
	public ResponseEntity<ProductResponseDTO> findByModel(@PathVariable("model") final String model) throws ObjectNotFoundException{
		ProductResponseDTO response = productService.findByBarCode(model);
			
		return Responses.ok(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Find all products.")
	public ResponseEntity<List<ProductResponseDTO>> findAll(){
		List<ProductResponseDTO> response = null;
		response = productService.findAll(10);
		return Responses.ok(response);
	}
	
	

}
