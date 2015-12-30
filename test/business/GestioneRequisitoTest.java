package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.GestioneSessione;
import business.GestioneRequisito;

public class GestioneRequisitoTest {
	
	GestioneRequisito test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneRequisito();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testRegistraRequisito() {
		boolean result;
		boolean expected;
		
		ArrayList<String> p = new ArrayList<>();
		p.add("2");
		p.add("Alfa Romeo");
		p.add("descrizione requisito test");
		expected = true;
		result = test.registraRequisito(p);
		assertTrue("Caso testRegistraRequisito", result == expected);
	}

	@Test
	public void testSetDatiRequisito() {
		GestioneSessione.setIdRequisitoCorrente("4");
		boolean result;
		boolean expected;
		
		ArrayList<String> p = new ArrayList<>();
		p.add("3");
		p.add("opel");
		p.add("descrizione requisiti test");
		p.add("false");
		expected = true;
		result = test.setDatiRequisito(p);
		assertTrue("Caso testSetDatiRequisito 0", result == expected);
	}

	@Test
	public void testRicercaRequisito() {
		ArrayList<HashMap<String, String>> expected = new ArrayList<>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("id", "4");
		expected1.put("descrizione", "descrizione requisito test");
		expected.add(expected1);
		
		ArrayList<String> p = new ArrayList<>();
		p.add("RequsitoTest");

		ArrayList<HashMap<String, String>> result = test.ricercaRequisito(p);
		assertTrue("Caso testRicercaRequisito 0", result.equals(expected));
		System.out.println(result);
	}

}
