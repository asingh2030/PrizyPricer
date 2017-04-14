package com.test.prizy.store.service;

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
	@Autowired
	StoreService storeService;
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
			StoreResponseDTO responseDTO = storeService.create(dto);
			Assert.assertNotNull(responseDTO);
			StoreResponseDTO responseDTO1 = storeService.create(dto1);
			Assert.assertNotNull(responseDTO1);
		} catch (ObjectAlreadyExistException e) {
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
			StoreResponseDTO responseDTO = storeService.create(dto);
			Assert.assertNotNull(responseDTO);
			Assert.assertEquals(dto.getUuid(), responseDTO.getUuid());
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
}
