package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.GestioneSessione;
import business.GestioneDipendente;


public class GestioneDipendenteTest {
	
	GestioneDipendente test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneDipendente();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}
	
	@Test
	public void testSetDipendente() {
		GestioneSessione.setIdDipendenteCorrente("2");
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("MariaGiordano");
		p.add("Giordano12");
		p.add("mariaGiordano@gmail.com");
		p.add("Maria");
		p.add("Giordano");
		p.add("12-05-1989");
		p.add("MRAGRD5A69HFV045J");
		p.add("Andria");
		p.add("Via Stazio, 2");
		p.add("76123");
		p.add("0883252166");
		boolean result = test.setDipendente(p);
		assertTrue("Caso di testSetdipendente 0", result == expected);
	}

	@Test
	public void testSetAccount() {
		GestioneSessione.setUsernameUtenteCorrente("MariaGiordano");
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("MariaGiorndano");
		p.add("mariaGiorndano@gmail.com");
		p.add("Giordano12");
		boolean result = test.setAccount(p);
		assertTrue("Caso di testSetAccount 0", result == expected);
	}
	
	@Test //controllo
	public void testRicercaContratto() {
		GestioneSessione.setIdDipendenteCorrente("2");
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("id", "2");
		expected1.put("data", "31/08/2014");
		expected.add(expected1);
		ArrayList<String> p = new ArrayList<>();
		p.add("ContrattoTest");
		ArrayList<HashMap<String, String>> result = test.ricercaContratto(p);
		assertTrue("Caso testRicercaContratto", result.equals(expected));
	}
	
	@Test
	public void testRegistraDipendente() {
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("michelazira1");
		p.add("michelazira");
		p.add("michelazira@alice.it");
		p.add("Michela");
		p.add("Zira");
		p.add("1998-10-03");
		p.add("ZRAGTN93S18A669I");
		p.add("Barletta");
		p.add("Via G. Falcone 121");
		p.add("76123");
		p.add("0883568912");
		boolean result = test.registraDipendente(p);
		assertTrue("Caso di testRegistraDipendente", result == expected);
	}

	@Test
	public void testRicerca(){
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("id", "1");
		expected1.put("nome", "Francesco");
		expected1.put("cognome", "Iacobbe");
		expected1.put("CodiceFiscale", "CBBFNC92A29H096I");
		expected.add(expected1);
		ArrayList<String> p = new ArrayList<>();
		p.add("Francesco");
		ArrayList<HashMap<String, String>> result = test.ricerca(p);
		assertTrue("Caso testRicercaDipendente", result.equals(expected));
	}
}
