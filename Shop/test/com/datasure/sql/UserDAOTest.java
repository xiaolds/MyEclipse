package com.datasure.sql;

import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datasure.shop.User;

public class UserDAOTest {

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
	public void testIsUserExist() throws SQLException {
		User user = new User("test","test");
		assertEquals(true, UserDAO.isUserExist(user));
	}

	@Test
	public void testAddUser() {
//		User user = new User("test", "test");
//		UserDAO.addUser(user);
	}

	@Test
	public void testDelUser() {
//		UserDAO.delUser(new User("test","fuck"));
	}

	@Test
	public void testUpdataPass() {
		User user = new User("test","fuck");
		UserDAO.updataPass(user);
	}

}
