package test.dataAccess.mysql;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlAutoTextDao;

public class MySqlAutoTextDaoTest {
	
	String[] keys = {"g", null, "ga", "", "c", "c"};
	String[] tables = {"Clienti", "Clienti", "", null, "Clienti", "Clienti"};
	String[] fields = {"Nome", "Nome", "Nome", "Nome", "", null};
	String[] results = {"giacomo", null, null, null, null, null};
	MySqlAutoTextDao test;
	

	@Before
	public void setUp() throws Exception {
		test = new MySqlAutoTextDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testAutoText() {
		int size = results.length;
		for (int i = 0; i < size; i++) {
			String result = test.autoText(keys[i], tables[i], fields[i]);
			assertTrue("Caso di test: " + i, result == null ? result == results[i] : result.equals(results[i]));
		}
		
	}

}
