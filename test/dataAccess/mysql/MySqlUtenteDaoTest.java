package test.dataAccess.mysql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlUtenteDao;

public class MySqlUtenteDaoTest {

	MySqlUtenteDao test;
	
	@Before
	public void setUp() throws Exception {
		test = new MySqlUtenteDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testLogin() {
		String[] expected = {"ManagerSistema", null, null, null, null};
		String[] username = {"mSistema", null, "mSistema1", "mSistema", "mSistema"};
		String[] password = {"mSistema4865", "hdiwuiehhka5", "sakudhauw78", null, "dwqhdkahdka"};
		
		for (int i = 0; i < expected.length; i++) {
			String result = test.login(username[i], password[i]);
			if (result == null) {
				assertNull("Caso di testLogin: " + i, expected[i]);
			} else {
				assertTrue("Caso di testLogin: " + i, result.equals(expected[i]));
			}
		}
	}

	@Test
	public void testIsAvailableUsername() {
		boolean[] expected = {true, false};
		String[] username = {"mSistema1", "mSistema"};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.isAvailableUsername(username[i]);
			assertTrue("Caso di testIsAvailableUsername: " + i, result == expected[i]);
		}
	}

	@Test
	public void testIsAvailableEmail() {
		boolean[] expected = {true, false};
		String[] email = {"cliente@gmail.it", "cliente6@gmail.it"};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.isAvailableEmail(email[i]);
			assertTrue("Caso di testIsAvailableEmail: " + i, result == expected[i]);
		}
	}

}
