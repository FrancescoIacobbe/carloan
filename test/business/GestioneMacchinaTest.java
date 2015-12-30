package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.GestioneSessione;
import business.GestioneMacchina;

public class GestioneMacchinaTest {
	
	GestioneMacchina test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneMacchina();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testNuovoMacchina() {
		boolean result;
		boolean expected;
		
		ArrayList<String> p = new ArrayList<>();
		p.add("DK802FM");
		p.add("Alfa Romeo");
		p.add("Si");
		p.add("No");
		p.add("70");
		p.add("B");
		expected = true;
		result = test.nuovoMacchina(p);
		assertTrue("Caso testNuovoMacchina", result == expected);
	}

	@Test
	public void testSetDati() {
		GestioneSessione.setIdMacchinaCorrente("3");
		boolean result;
		boolean expected;
		
		ArrayList<String> p = new ArrayList<>();
		p.add("DK802FM");
		p.add("Alfa Romeo");
		p.add("Si");
		p.add("No");
		p.add("70");
		p.add("B");
		expected = true;
		result = test.setDati(p);
		assertTrue("Caso testSetDati 0", result == expected);
	}

	@Test
	public void testRicercaRequisito() {
		ArrayList<HashMap<String, String>> expected = new ArrayList<>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("targa", "DK802FN");
		expected1.put("modello", "Alfa Romeo");
		expected1.put("disponibilità", "Si");
		expected.add(expected1);
		
		ArrayList<String> p = new ArrayList<>();
		p.add("MacchinaTest");

		ArrayList<HashMap<String, String>> result = test.ricerca(p);
		assertTrue("Caso testRicercaMacchina 0", result.equals(expected));
		System.out.println(result);
	}

	public void TestElimina() {
		boolean expected = true;
		ArrayList<String> p  = new ArrayList<String>();
		p.add("targa");
		GestioneSessione.setIdMacchinaCorrente("4");
		boolean result = test.elimina(p);
		assertTrue("Caso di eliminaMacchina", result == expected);
	}
	
}
