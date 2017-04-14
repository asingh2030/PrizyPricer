package com.test.prizy.product.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prizy.PrizyPricerApplication;
import com.prizy.exception.ObjectAlreadyExistException;
import com.prizy.exception.ObjectNotFoundException;
import com.prizy.product.dto.ProductInputDTO;
import com.prizy.product.dto.ProductResponseDTO;
import com.prizy.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PrizyPricerApplication.class)
@EnableAutoConfiguration
@Transactional
public class ProductServiceTest {
	private final String product1Barcode = "1";
	private final String product2Barcode = "2";
	private final String product3Barcode = "3";
	private final String product4Barcode = "4";
	private final UUID product1UUID = UUID.randomUUID();
	private final UUID product2UUID = UUID.randomUUID();
	private final UUID product3UUID = UUID.randomUUID();
	private final UUID product4UUID = UUID.randomUUID();
	@Autowired
	ProductService productService;
	@Before
	public void setup(){
		ProductInputDTO dto = new ProductInputDTO();
		dto.setBarcode(product1Barcode);
		dto.setDescription("MotoX good phone.");
		dto.setManufacturer("MOTO");
		dto.setModel("MOTO-X");
		dto.setName("MOTO-X");
		dto.setNotes("5 inch screen.");
		dto.setPrice(10.00D);
		dto.setUuid(product1UUID);
		ProductInputDTO dto1 = new ProductInputDTO();
		dto1.setBarcode(product2Barcode);
		dto1.setDescription("MotoG good phone.");
		dto1.setManufacturer("MOTO");
		dto1.setModel("MOTO-G");
		dto1.setName("MOTO-G");
		dto1.setNotes("5 inch screen.");
		dto1.setPrice(20.00D);
		dto1.setUuid(product2UUID);
		ProductInputDTO dto2 = new ProductInputDTO();
		dto2.setBarcode(product3Barcode);
		dto2.setDescription("MotoE good phone.");
		dto2.setManufacturer("MOTO");
		dto2.setModel("MOTO-E");
		dto2.setName("MOTO-E");
		dto2.setNotes("5 inch screen.");
		dto2.setPrice(30.00D);
		dto2.setUuid(product3UUID);
		try {
			ProductResponseDTO responseDTO = productService.create(dto);
			Assert.assertNotNull(responseDTO);
			ProductResponseDTO responseDTO1 = productService.create(dto1);
			Assert.assertNotNull(responseDTO1);
			ProductResponseDTO responseDTO2 = productService.create(dto2);
			Assert.assertNotNull(responseDTO2);
		} catch (ObjectAlreadyExistException | ObjectNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSuccessCreateProduct(){
		ProductInputDTO dto = new ProductInputDTO();
		dto.setBarcode(product4Barcode);
		dto.setDescription("MotoX-Play good phone.");
		dto.setManufacturer("MOTO");
		dto.setModel("MOTO-X-Play");
		dto.setName("MOTO-X-Play");
		dto.setNotes("5 inch screen.");
		dto.setPrice(40.00D);
		dto.setUuid(product4UUID);
		try {
			ProductResponseDTO responseDTO = productService.create(dto);
			Assert.assertNotNull(responseDTO);
			Assert.assertEquals(dto.getUuid(), responseDTO.getUuid());
			Assert.assertEquals(dto.getBarcode(), responseDTO.getBarCode());
		} catch (ObjectAlreadyExistException | ObjectNotFoundException e) {
			Assert.fail(e.getMessage());
		}
		
	}
	@Test
	public void testAddPrice(){
		try {
			productService.addPrice(product1Barcode, 20.00D);
			productService.addPrice(product1Barcode, 30.00D);
			productService.addPrice(product1Barcode, 40.00D);
			productService.addPrice(product1Barcode, 50.00D);
			productService.addPrice(product1Barcode, 60.00D);
			productService.addPrice(product1Barcode, 70.00D);
			productService.addPrice(product1Barcode, 80.00D);
			productService.addPrice(product1Barcode, 90.00D);
			ProductResponseDTO res = productService.findByBarCode(product1Barcode);
			Assert.assertEquals(new Double(50.00), res.getAveragePrice());
			Assert.assertEquals(new Double(90.00), res.getHighestPrice());
			Assert.assertEquals(new Double(10.00), res.getLowestPrice());
			Assert.assertEquals(new Double(60.00), res.getIdealPrice());
		} catch (ObjectNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(expected=ObjectAlreadyExistException.class)
	public void testCreateFailure() throws Exception{
		ProductInputDTO dto = new ProductInputDTO();
		dto.setBarcode(product1Barcode);
		dto.setDescription("MotoX-Play good phone.");
		dto.setManufacturer("MOTO");
		dto.setModel("MOTO-X-Play");
		dto.setName("MOTO-X-Play");
		dto.setNotes("5 inch screen.");
		dto.setPrice(40.00D);
		dto.setUuid(product1UUID);
		productService.create(dto);
	}
}
