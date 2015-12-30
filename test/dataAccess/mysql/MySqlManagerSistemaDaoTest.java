package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlManagerSistemaDao;

public class MySqlManagerSistemaDaoTest {

	MySqlManagerSistemaDao test;
	
	@Before
	public void setUp() throws Exception {
		test = new MySqlManagerSistemaDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetAccount() {
		HashMap<String,String> expected = new HashMap<String,String>();
		expected.put("email", "mSistema@gmail.it");
		expected.put("password", "mSistema");
		HashMap<String, String> result = test.getAccountManager();
		assertTrue("Caso di testGetAccount", result.equals(expected));
	}

	@Test
	public void testSetAccount() {
		boolean[] expected = {true, false, false};
		String[] email = {"mSistema1@gmail.it", null, "mSistema1@gmail.it"};
		String[] password = {"mSistema1", "mSistema1", null};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setAccountManager(email[i], password[i]);
			assertTrue("Caso di testSetAccount: " + i, result == expected[i]);
		}
	}

}
