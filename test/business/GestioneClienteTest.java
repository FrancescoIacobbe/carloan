package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.GestioneSessione;
import business.GestioneCliente;


public class GestioneClienteTest {
	
	GestioneCliente test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneCliente();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}
	
	@Test
	public void testSetAnagrafica() {
		GestioneSessione.setIdClienteCorrente("3");
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("Maria");
		p.add("Giordano");
		p.add("1954-08-25");
		p.add("ZRIGTN93S18A669I");
		p.add("Napoli");
		p.add("Via Piemonte 5");
		p.add("083597158");
		p.add("76123");
		boolean result = test.setAnagrafica(p);
		assertTrue("Caso di testSetAnagrafica 0", result == expected);
	}

	@Test
	public void testSetAccount() {
		GestioneSessione.setUsernameUtenteCorrente("cliente1");
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("cliente1@gmail.it");
		p.add("cliente1");
		boolean result = test.setAccount(p);
		assertTrue("Caso di testSetAccount 0", result == expected);
	}
	
	@Test //controllo
	public void testRicercaContratto() {
		GestioneSessione.setIdClienteCorrente("4");
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("id", "4");
		expected1.put("data", "31/08/2014");
		expected.add(expected1);
		ArrayList<String> p = new ArrayList<>();
		p.add("ContrattoTest");
		ArrayList<HashMap<String, String>> result = test.ricercaContratto(p);
		assertTrue("Caso testRicercaContratto", result.equals(expected));
	}
	
	@Test
	public void testRegistraCliente() {
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
		boolean result = test.registraCliente(p);
		assertTrue("Caso di testRegistraCliente", result == expected);
	}

	@Test
	public void testRicercaCliente(){
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> expected1 = new HashMap<>();
		expected1.put("id", "1");
		expected1.put("nome", "Francesco");
		expected1.put("cognome", "Iacobbe");
		expected1.put("CodiceFiscale", "CBBFNC92A29H096I");
		expected.add(expected1);
		ArrayList<String> p = new ArrayList<>();
		p.add("Francesco");
		ArrayList<HashMap<String, String>> result = test.ricercaCliente(p);
		assertTrue("Caso testRicercaCliente", result.equals(expected));
	}
}
