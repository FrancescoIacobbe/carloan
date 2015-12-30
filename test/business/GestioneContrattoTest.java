package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.GestioneSessione;
import business.GestioneContratto;

public class GestioneContrattoTest {

	GestioneContratto test;
	@Before
	public void setUp() throws Exception {
		GestioneSessione.setIdContrattoCorrente("3");
		test = new GestioneContratto();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}
	
	@Test
	public void testNuovoContratto() {
		boolean result;
		boolean expected;
		
		GestioneSessione.setIdDipendenteCorrente("1");
		
		ArrayList<String> p = new ArrayList<>();
		p.add("3");
		p.add("2015-01-29");
		p.add("2015-02-04");
		p.add("2015-02-04");
		p.add("Bari");
		p.add("settimanale");
		p.add("illimitato");
		p.add("B");
		p.add("20,00");
		p.add("150");
		p.add("50");
		result = test.nuovoContratto(p);
		expected = true;
		assertTrue("Caso testNuovaContratto 0", result == expected);
	}

	@Test
	public void testSetDati() {
		boolean expected = true;
		ArrayList<String> p = new ArrayList<String>();
		p.add("2");
		p.add("2015/02/05");
		p.add("2015/02/10");
		p.add("2015/02/10");
		p.add("Milano");
		p.add("settimanale");
		p.add("limitato");
		p.add("B");
		p.add("20");
		p.add("200");
		p.add("50");
		 System.out.println(p);
		boolean result = test.setDati(p);
		assertTrue("Caso di testSetDati", result == expected);
	}
	
	@Test
	public void testSetMacchinaAllocata(){
		GestioneSessione.setIdContrattoCorrente("1");
		boolean expected = true;
		boolean result;
		ArrayList<String> p = new ArrayList<String>();
		result = test.setMacchinaAllocata(p);
		assertTrue("Caso di testSetMacchinaAllocata 0", result == expected);
		p.add("1");
		p.add("DK802FM");
		result = test.setMacchinaAllocata(p);
		assertTrue("Caso di testSetMacchinaAllocata 1", result == expected);
	}
	
	@Test
	public void testSetTariffaBaseAllocato(){
		GestioneSessione.setIdContrattoCorrente("1");
		boolean expected = true;
		boolean result;
		ArrayList<String> p = new ArrayList<String>();
		result = test.setTariffaBaseAllocato(p);
		assertTrue("Caso di testSetTariffaBaseAllocato 0", result == expected);
		p.add("1");
		p.add("75.00");
		result = test.setTariffaBaseAllocato(p);
		assertTrue("Caso di testSetTariffaBaseAllocato 1", result == expected);
	}
	
	@Test
	public void testSetPrezzoExtraAllocato(){
		GestioneSessione.setIdContrattoCorrente("1");
		boolean expected = true;
		boolean result;
		ArrayList<String> p = new ArrayList<String>();
		result = test.setPrezzoExtraAllocato(p);
		assertTrue("Caso di testSetPrezzoExtraAllocato 0", result == expected);
		p.add("1");
		p.add("25");
		result = test.setPrezzoExtraAllocato(p);
		assertTrue("Caso di testSetPrezzoExtraAllocato 1", result == expected);
	}
	
	@Test
	public void testSetCostoTotAllocato(){
		GestioneSessione.setIdContrattoCorrente("1");
		boolean expected = true;
		boolean result;
		ArrayList<String> p = new ArrayList<String>();
		result = test.setCostoTotAllocato(p);
		assertTrue("Caso di testSetCostoTotAllocato 0", result == expected);
		p.add("1");
		p.add("200.00");
		result = test.setCostoTotAllocato(p);
		assertTrue("Caso di testSetCostoTotAllocato 1", result == expected);
	}
}


