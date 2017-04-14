package com.prizy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.test.prizy.product.service.ProductServiceTest;
import com.test.prizy.store.service.StoreServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ProductServiceTest.class, StoreServiceTest.class})
public class TestSuite {

}