package com.datasure.sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datasure.shop.Product;

public class StoreDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRemainPrdts() {
	}

	@Test
	public void testAddPrdtToStoreListOfProduct() {
//		StoreDAO.addPrdtToStore(new Product("iPhone7",8));
//		System.out.println(StoreDAO.getRemainPrdts());
	}

	@Test
	public void testAddPrdtToStoreProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelPrdtFromStoreProduct() {
		StoreDAO.delPrdtFromStore(new Product("iPhone7"));
	}
	

	@Test
	public void testDelPrdtFromStoreListOfProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPrdtExist() {
		
		assertEquals(true, StoreDAO.isPrdtExist(new Product("iPhone6")));
		assertEquals(false, StoreDAO.isPrdtExist(new Product("iPhone7")));
	}

	@Test
	public void testUpdatePrdtFromStoreProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePrdtFromStoreListOfProduct() {
//		StoreDAO.updatePrdtFromStore(new Product("iPhone6sp",100));
	}

}
