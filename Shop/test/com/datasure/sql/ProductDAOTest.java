package com.datasure.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datasure.shop.Product;
import com.datasure.shop.User;

public class ProductDAOTest {
	
	User user;
	User user1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user = new User("xiaolds");
		user1 = new User("fuck");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProduction() {
//		List<Product> list = new ArrayList<>();
//		list.add(new Product("iPhone4", 2));
//		list.add(new Product("iPhone5s", 2));
//		assertEquals(list, ProductDAO.getProduction(new User("haha")));
	}

	@Test
	public void testIsOwnPrdt() {
		User user = new User("xiaolds");
		Product prdt = new Product("iPhone6sp");
		assertEquals(true, ProductDAO.isOwnPrdt(user, prdt));
		assertEquals(false,ProductDAO.isOwnPrdt(user,new Product("iPhone7")));
	}

	@Test
	public void testIsEmpty() {
		User user = new User("xiaolds");
		User user1 = new User("fuck");
		
		assertEquals(false, ProductDAO.isEmpty(user));
		assertEquals(true, ProductDAO.isEmpty(user1));
	}

	@Test
	public void testGetPrdtNumber() {
		assertEquals(12,ProductDAO.getPrdtNumber(user, new Product("iPhone4")));
	}

	@Test
	public void testAddPrdtToUserUserProduct() {
//		ProductDAO.addPrdtToUser(user, new Product("iPhone9",10));
	}

	@Test
	public void testAddPrdtToUserUserListOfProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelPrdtFromUserUserProduct() {
		ProductDAO.delPrdtFromUser(user, new Product("iPhone9"));
	}

	@Test
	public void testDelPrdtFromUserUserListOfProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePrdtFromUser() {
//		ProductDAO.updatePrdtFromUser(user, new Product("iPhone4",2));
	}

}
