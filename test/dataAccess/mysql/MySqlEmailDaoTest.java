package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlEmailDao;

public class MySqlEmailDaoTest {

	MySqlEmailDao test;
	@Before
	public void setUp() throws Exception {
		test = new MySqlEmailDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetContratto() {
		HashMap<String,String>[] expected = new HashMap[5];
		String[] idContratto = {"4", null, "3", "2", "1"};
		String[] CFCliente = {"CBBBFNC12345678", "QSD123HJUI334567", "IOKQWER3456789JA", null, "QWE12345TRYKAS23"};
		
		HashMap<String,String> e1 = new HashMap<String,String>();
		e1.put("emailCliente", "cliente15@gmail.it");
		e1.put("contratto", "5");
		e1.put("emailDipendente", "dipendente2@gmail.it");
		e1.put("nome", "dipendente2");
		e1.put("cliente", "cliente2");
		
		expected[0] = e1;
		HashMap<String, String> e2 = null;
		expected[1] = expected[2] = expected[3] = expected[4] = e2;
		
		for(int i = 0; i < expected.length; i++) {
			HashMap<String,String> result = test.getContratto(idContratto[i], CFCliente[i]);
			if (result == null) {
				assertNull("Caso di testGetContratto: " + i, expected[i]);
			} else {
				assertTrue("Caso di testGetContratto: " + i, result.equals(expected[i]));
			}
		}
		
	}

	@Test
	public void testgetIndirizzoCliente() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[3];
		String[] idContratto = {"2", null, "7"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> e1 = new HashMap<String, String>();
		e1.put("email", "cliente4@gmail.it");
		e1.put("nome", "Giuseppe");
		a1.add(e1);
		
		HashMap<String,String> e2 = new HashMap<String, String>();
		e2.put("email", "cliente5@gmail.it");
		e2.put("nome", "Fabrizio");
		a1.add(e2);
		
		expected[0] = a1;
		expected[1] = expected[2] = new ArrayList<HashMap<String,String>>();
		
		for(int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String,String>> result = test.getIndirizzoCliente(idContratto[i]);
			assertTrue("Caso di testGetIndirizzoCliente: " + i, result.equals(expected[i]));
		}
	}
}
