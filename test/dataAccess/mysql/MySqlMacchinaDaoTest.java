package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlMacchinaDao;

public class MySqlMacchinaDaoTest {

	MySqlMacchinaDao test;
	@Before
	public void setUp() throws Exception {
		test = new MySqlMacchinaDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetDatiMacchina() {
		HashMap<String, String>[] expected = new HashMap[3];
		String[] targa = {"BT456NM", null, "CV110FL"};
		
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("modello", "bmw  m2");
		o1.put("disponibilita", "SI");
		o1.put("ultimokm", "45");
		o1.put("fascia", "A");
		o1.put("manuternzione", "NO");

		HashMap<String,String> o2 = null;
		 expected[0] = o1;
		 expected[1] = expected[2] = o2;
		 
		 for (int i = 0; i < expected.length; i++) {
			 HashMap<String, String> result = test.getDatiMacchina(targa[i]);
			 if (result == null) {
				 assertNull("Caso di testGetDati: " + i, expected[i]);
			 } else {
				 assertTrue("Caso di testGetDati: " + i, result.equals(expected[i]));
			 }
		 }
	}

	@Test
	public void testRegistraMacchina() {
		boolean[] expected = {true, false};
		String[] targa = {"BF236ML", null};
		String[] modello = {"lancia delta", "opel astra"};
		String[] disponibilita = {"SI", "NO"};
		String[] manuntenzione= {"SI", "NO"};
		int[] ultimoKm= {256, 0};
		String[] fascia ={"C","C"};
		
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.registraMacchina(targa[i], modello[i], disponibilita[i], manuntenzione[i], ultimoKm[i],fascia[i]);
			assertTrue("Caso di testRegistraMacchina: " + i, result == expected[i]);
		}
	}

	@Test
	public void testGetRequisitoSistema() {
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("targa", "BL264VL");
		o1.put("modello", "citroen c3");
		o1.put("disponibilita", "SI");
		expected.add(o1);
		
		HashMap<String, String> o2 = new HashMap<String,String>();
		o1.put("targa", "DF15MO");
		o1.put("modello", "bmw m4 gts");
		o1.put("disponibilita", "NO");
		expected.add(o2);
		
		HashMap<String, String> o3 = new HashMap<String,String>();
		o1.put("targa", "GF458IL");
		o1.put("modello", "audi a4");
		o1.put("disponibilita", "SI");
		expected.add(o1);

		HashMap<String, String> o4 = new HashMap<String,String>();
		o1.put("targa", "GV158XL");
		o1.put("modello", "audi a2");
		o1.put("disponibilita", "NO");
		expected.add(o4);
		
		ArrayList<HashMap<String, String>> result = test.getMacchinaSistema();
		assertTrue("Caso di testGetMacchinaSistema", result.equals(expected));
	}

	
	@Test
	public void testSetDati() {
		boolean[] expected = {true, false, false, false};
		String[] targa = {"BB158LM", null, "KL469LO", "KM548FV"};
		String[] modello = {"alfa romeo", "lancia delta", "toyota yaris", null};
		String[] disponibilita = {"SI", null, "NO", "SI"};
		String[] manutenzione  ={"NO","SI","SI","NO"};
		int [] ultimokm ={152, 0, 816, 0};
		String[] fascia ={"A", "B", "C", null};
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setDatiMacchina(targa[i], modello[i],  disponibilita[i], manutenzione[i], ultimokm[i], fascia[i]);
			assertTrue("Caso di testSetDati: " + i, result == expected[i]);
		}
	}

	@Test
	public void testRicerca() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[2];
		String[] key = {"not", "noResult"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> o1 = new HashMap<String,String>();
		o1.put("targa", "BL156LD");
		o1.put("modello", "citroen picasso");
		o1.put("disponibilita", null);
		a1.add(o1);
		
		expected[0] = a1;
		expected[1] = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String, String>> result = test.ricercaMacchine(key[i]);
			assertTrue("Caso di testRicercaMacchine: " + i, result.equals(expected[i]));
		}
	}

}
