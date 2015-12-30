package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlRequisitoDao;

public class MySqlRequisitoDaoTest {

	MySqlRequisitoDao test;
	@Before
	public void setUp() throws Exception {
		test = new MySqlRequisitoDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetDatiRequisito() {
		HashMap<String, String>[] expected = new HashMap[3];
		String[] idRequsito = {"1", null, "20"};
		
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("id", "1");
		o1.put("modelli", "bmw  m2");
		o1.put("descrizione", "sitema start&stop");

		
		HashMap<String,String> o2 = null;
		 expected[0] = o1;
		 expected[1] = expected[2] = o2;
		 
		 for (int i = 0; i < expected.length; i++) {
			 HashMap<String, String> result = test.getDatiRequisito(idRequsito[i]);
			 if (result == null) {
				 assertNull("Caso di testGetDati: " + i, expected[i]);
			 } else {
				 assertTrue("Caso di testGetDati: " + i, result.equals(expected[i]));
			 }
		 }
	}

	@Test
	public void testRegistraRequisito() {
		boolean[] expected = {true, false};
		String[] id = {"2", null};
		String[] modelli = {"lancia delta", "opel astra"};
		String[] descrizione = {"4 porte", "cerchi in lega"};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.registraRequisito(id[i], modelli[i], descrizione[i]);
			assertTrue("Caso di testRegistraRequisito: " + i, result == expected[i]);
		}
	}

	@Test
	public void testGetRequisitoSistema() {
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("id", "1");
		o1.put("modelli", "citroen c3");
		o1.put("descrizione", "4 ruote");
		expected.add(o1);
		
		HashMap<String, String> o2 = new HashMap<String,String>();
		o2.put("id", "2");
		o2.put("modelli", "fiat 500L");
		o2.put("descrizione", null);
		expected.add(o2);
		
		HashMap<String, String> o3 = new HashMap<String,String>();
		o3.put("id", "3");
		o3.put("modelli", "fiat punto");
		o3.put("descrizione", "cerchi in lega");
		expected.add(o3);
		
		HashMap<String, String> o4 = new HashMap<String,String>();
		o4.put("id", "4");
		o4.put("modelli", "citroen c4");
		o4.put("descrizione", "Cambio automatico");
		expected.add(o4);

		ArrayList<HashMap<String, String>> result = test.getRequisitoSistema();
		assertTrue("Caso di testGetRequsiitoSistema", result.equals(expected));
	}

	
	@Test
	public void testSetDati() {
		boolean[] expected = {true, false, false, false};
		String[] id = {"1", null, "20", "1"};
		String[] modelli = {"alfa romeo", "lancia delta", "toyota yaris", null};
		String[] descrizione = {null, null, null, null};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setDatiRequisito(id[i], modelli[i],  descrizione[i]);
			assertTrue("Caso di testSetDati: " + i, result == expected[i]);
		}
	}

	@Test
	public void testRicerca() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[2];
		String[] key = {"not", "noResult"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("id", "1");
		o1.put("modelli", "citroen picasso");
		o1.put("descrizione", null);
		a1.add(o1);
		
		expected[0] = a1;
		expected[1] = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String, String>> result = test.ricercaRequisito(key[i]);
			assertTrue("Caso di testRicercaRequisito: " + i, result.equals(expected[i]));
		}
	}

}
