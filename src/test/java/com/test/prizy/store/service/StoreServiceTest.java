package com.test.prizy.store.service;

import java.util.HashSet;
import java.util.Set;
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
import com.prizy.store.dto.Address;
import com.prizy.store.dto.StoreInputDTO;
import com.prizy.store.dto.StoreResponseDTO;
import com.prizy.store.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PrizyPricerApplication.class)
@EnableAutoConfiguration
@Transactional
public class StoreServiceTest {
	private final String store1Name = "store1";
	private final String store2Name = "store2";
	private final UUID store1UUID = UUID.randomUUID();
	private final UUID store2UUID = UUID.randomUUID();
	private static Address address1 = null;
	private static Address address2 = null;
	private static final String pincode1="1";
	private static final String pincode2="2";
	private final String product1Barcode = "1";
	private final String product2Barcode = "2";
	private final String product3Barcode = "3";
	private final String product4Barcode = "4";
	private final UUID product1UUID = UUID.randomUUID();
	private final UUID product2UUID = UUID.randomUUID();
	private final UUID product3UUID = UUID.randomUUID();
	private final UUID product4UUID = UUID.randomUUID();
	
	
	@Autowired
	StoreService storeService;
	@Autowired
	private ProductService productService;
	
	@Before
	public void setup(){
		StoreInputDTO dto = new StoreInputDTO();
		dto.setUuid(store1UUID);
		dto.setName(store1Name);
		address1 = new Address();
		address1.setLine1("line11");
		address1.setLine2("line21");
		address1.setLine3("line31");
		address1.setPincode(pincode1);
		StoreInputDTO dto1 = new StoreInputDTO();
		dto1.setUuid(store2UUID);
		dto1.setName(store2Name);
		address2 = new Address();
		address2.setLine1("line12");
		address2.setLine2("line22");
		address2.setLine3("line32");
		address2.setPincode(pincode2);
		try {
			createProduct(product1Barcode, product1UUID, 1);
			createProduct(product2Barcode, product2UUID, 2);
			Set<UUID> products = new HashSet<>();
			products.add(product1UUID);
			dto.setProducts(products);
			StoreResponseDTO responseDTO = storeService.create(dto);
			Assert.assertNotNull(responseDTO);
			Assert.assertEquals(products.size(), responseDTO.getProducts().size());
			products.add(product2UUID);
			dto1.setProducts(products);
			StoreResponseDTO responseDTO1 = storeService.create(dto1);
			Assert.assertNotNull(responseDTO1);
			Assert.assertEquals(products.size(), responseDTO1.getProducts().size());
		} catch (ObjectAlreadyExistException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	private void createProduct(String barcode, UUID uuid, int number){
		ProductInputDTO dto = new ProductInputDTO();
		dto.setBarcode(barcode);
		dto.setDescription("MotoX-Play good phone."+number);
		dto.setManufacturer("MOTO"+number);
		dto.setModel("MOTO-X-Play"+number);
		dto.setName("MOTO-X-Play"+number);
		dto.setNotes("5 inch screen."+number);
		dto.setPrice(40.00D+number);
		dto.setUuid(uuid);
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
	public void testSuccessCreateStore(){
		StoreInputDTO dto = new StoreInputDTO();
		dto.setName("store3");
		UUID randomUUID = UUID.randomUUID();
		dto.setUuid(randomUUID);
		try {
			createProduct(product3Barcode, product3UUID, 3);
			createProduct(product4Barcode, product4UUID, 4);
			Set<UUID> products = new HashSet<>();
			products.add(product1UUID);
			products.add(product2UUID);
			products.add(product3UUID);
			products.add(product4UUID);
			dto.setProducts(products);
			StoreResponseDTO responseDTO = storeService.create(dto);
			Assert.assertNotNull(responseDTO);
			Assert.assertEquals(dto.getUuid(), responseDTO.getUuid());
			Assert.assertEquals(products.size(), responseDTO.getProducts().size());
		} catch (ObjectAlreadyExistException e) {
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test(expected=ObjectAlreadyExistException.class)
	public void testCreateFailure() throws Exception{
		StoreInputDTO dto = new StoreInputDTO();
		dto.setName("store1");
		dto.setUuid(store1UUID);
		storeService.create(dto);
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testUpdteFailure() throws Exception{
		StoreInputDTO dto = new StoreInputDTO();
		dto.setName("store1");
		UUID randomUUID = UUID.randomUUID();
		dto.setUuid(randomUUID);
		storeService.update(randomUUID, dto);
	}

	@Test
	public void testAddProduct() throws Exception{
		Set<UUID> products = new HashSet<>();
		products.add(product3UUID);
		products.add(product4UUID);
		boolean res = storeService.addProducts(store1UUID, products);
		Assert.assertTrue(res);
		StoreResponseDTO response = storeService.findByUUID(store1UUID);
		Assert.assertNotNull(response);
		Assert.assertEquals(3, response.getProducts().size());
	}
	
	
}
