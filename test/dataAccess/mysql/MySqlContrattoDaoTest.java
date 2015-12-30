package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlContrattoDao;

public class MySqlContrattoDaoTest {
	
	MySqlContrattoDao test;
	int limiteTemporale;

	@Before
	public void setUp() throws Exception {
		test = new MySqlContrattoDao();
		limiteTemporale = 48;
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetDati() {
		String[] idContratto = {"1", "99", null};
		HashMap<String, String>[] results = new HashMap[3];
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("dataInizio", "27/07/2014"); 
		result.put("dataLimite", "02/08/2014");
		result.put("dataRestituzione", "01/08/2014"); 
		result.put("luogoRestiruzione", "Bari");
		result.put("tipoNoleggio", "settimanale"); 
		result.put("tipoKm", "illimitato"); 
		result.put("tipoFascia", "A"); 
		result.put("costoKm", "30"); 
		result.put("KmEffettivi", "350"); 
		result.put("acconto", "50"); 
		results[0] = result;
		
		HashMap<String, String> resultNull = null;
		results[1] = resultNull;
		results[2] = resultNull;
		
		for (int i = 0; i < idContratto.length; i++) {
			result = test.getDatiContratto(idContratto[i]);
			if(result == null) {
				assertNull("Caso di testGetDati: " + i, results[i]);
			} else {
				assertTrue("Caso di testGetDati: " + i, result.equals(results[i]));
			}
		}
	}


	@Test
	public void testCompilaContratto() {
		boolean[] expected = {true, false, false, false, false, false, false, false, false, false, false, false,false};
		String[] CFDipendente = {"FCBLOV125DFR215H", null, "FCBLOV125DFR215H", "FCBLOV125DFR215H", "FCBLOV125DFR215H", "FCBLOV125DFR215H", "FCBLOV125DFR215H","FCBLOV125DFR215H","FCBLOV125DFR215H","FCBLOV125DFR215H","FCBLOV125DFR215H","FCBLOV125DFR215H","FCBLOV125DFR215H"};
		String[] id = {"46", "46", null, "46", "46", "46", "46","46","46","46","46","46","46"};
		String[] dataInizio = {"2015/01/25","2015/01/25","2015/01/25",null,"2015/01/25","2015/01/25","2015/01/25","2015/01/25","2015/01/25","2015/01/25","2015/01/25","2015/01/25","2015/01/25"};
		String[] dataLimite = {"2015/01/31","2015/01/31","2015/01/31","2015/01/31",null,"2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31"};
		String[] dataRestituzione = {"2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31",null,"2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31","2015/01/31"};
		String[] luogoRestituzione = {"Bari", "Bari","Bari","Bari","Bari","Bari",null,"Bari","Bari","Bari","Bari","Bari","Bari"};
		String[] tipoNoleggio = {"settimanale","settimanale","settimanale","settimanale","settimanale","settimanale","settimanale",null,"settimanale","settimanale","settimanale","settimanale","settimanale"};
		String[] tipoKm = {"illimitato","illimitato","illimitato","illimitato","illimitato","illimitato","illimitato","illimitato",null,"illimitato","illimitato","illimitato","illimitato"};
		String[] tipoFascia = {"A","A","A","A","A","A","A","A","A",null,"A","A","A"};
		double[] costoKm = {5,5,5,5,5,5,5,5,5,5,0,5,5};
		int[] kmEffetivi = {50,50,50,50,50,50,50,50,50,50,50,0,50};
		double[] acconto = {75,75,75,75,75,75,75,75,75,75,75,75,0};

		for (int i = 0; i < CFDipendente.length; i++) {
			boolean result = test.compilaContratto(CFDipendente[i], id[i], dataInizio[i], dataLimite[i], dataRestituzione[i], luogoRestituzione[i], 
					tipoNoleggio[i],tipoKm[i], tipoFascia[i], costoKm[i], kmEffetivi[i], acconto[i]);
			assertEquals("Caso di testCompilContratto: " + 1, expected[i], result);
		}
	}
	

	@Test
	public void testSetTariffaBaseAllocata() {
		boolean[] expected = {true, false, false};
		String[] idContratto = {"3", null, "3"};
		double[] tariffaBase = {25,25,0};
		

		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setTariffaBaseAllocato(idContratto[i], tariffaBase[i]);
			assertTrue("Caso di testSetOptionalAllocati: " + i, result == expected[i]);
		}
	}

	@Test
	public void testSetMacchinaAllocata() {
		boolean[] expected = {true, false, false};
		String[] idContratto = {"3", null, "3"};
		String[] macchina = {"BT213GH","BT213GH", null};
		

		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setMacchinaAllocata(idContratto[i], macchina[i]);
			assertTrue("Caso di testSetOptionalAllocati: " + i, result == expected[i]);
		}
	}


	@Test
	public void testSetPrezzoExtraAllocato() {
		boolean[] expected = {true, false, false};
		String[] idContratto = {"3", null, "3"};
		double[] prezzoExtra = {50,50, 0};
		

		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setPrezzoExtraAllocato(idContratto[i], prezzoExtra[i]);
			assertTrue("Caso di testSetOptionalAllocati: " + i, result == expected[i]);
		}
	}


	@Test
	public void testSetCostoTotAllocata() {
		boolean[] expected = {true, false, false};
		String[] idContratto = {"3", null, "3"};
		double[] costoTot = {150.50,150.50, 0.00};
		

		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setCostoTotAllocato(idContratto[i], costoTot[i]);
			assertTrue("Caso di testSetOptionalAllocati: " + i, result == expected[i]);
		}
	}
	
	@Test
	public void testSetDatiContratto() {

		boolean expected[] = {true, false, false, false, false, false, false, false,false, false,false,false};
		String[] idContratto = {"5", null, "5", "5", "5", "5", "5", "5","5","5","5","5"};
		String[] dataInizio = {"2014/09/23", "2014/09/23",null,"2014/09/23","2014/09/23","2014/09/23","2014/09/23","2014/09/23","2014/09/23","2014/09/23","2014/09/23","2014/09/23"};
		String[] dataLimite= {"2014/09/30","2014/09/30","2014/09/30", null, "2014/09/30","2014/09/30","2014/09/30","2014/09/30","2014/09/30","2014/09/30","2014/09/30","2014/09/30"};
		String[] dataRestituzione = {"2014/09/30", "2014/09/30", "2014/09/30", "2014/09/30", null,"2014/09/30", "2014/09/30", "2014/09/31","2014/09/30","2014/09/30","2014/09/30","2014/09/30"};
		String [] tipoNoleggio = {"settimanale","settimanale","settimanale","settimanale","settimanale",null,"settimanale","settimanale","settimanale","settimanale","settimanale","settimanale"};
		String [] luogoRestituzione = {"Bari","Bari","Bari","Bari","Bari","Bari",null,"Bari","Bari","Bari","Bari","Bari"};
		String[] tipoKm = {"limitato","limitato","limitato","limitato","limitato","limitato","limitato",null,"limitato","limitato","limitato","limitato"};
		String[] tipoFascia = {"A","A","A","A","A","A","A","A",null,"A","A","A"};
		double[] costoKm = {30,30,3,30,30,30,30,30,30,0,30,30};
		int [] kmEffettivi={120,120,120,120,120,120,120,120,120,120,0,120};
		double[] acconto={50,50,50,50,50,50,50,50,50,50,50,0};
	
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setDatiContratto(idContratto[i], dataInizio[i], dataLimite[i], dataRestituzione[i], luogoRestituzione[i], tipoNoleggio[i], tipoKm[i], tipoFascia[i], 
					costoKm[i], kmEffettivi[i], acconto[i]);
			assertTrue("Caso di testSetDati: " + i, result == expected[i]);
		}
	}

	@Test
	public void testGetMacchinaAllocata() {
		String[] idContratto = {"1", "99", null};
		ArrayList<HashMap<String,String>>[] expected = new ArrayList[3];
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> o1 = new HashMap<String,String>();
		o1.put("targa", "BT123GH");
		o1.put("modello", null);
		
		result.add(o1);
		expected[0] = result;
		expected[1]= expected[2] = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < idContratto.length; i++) {
			result = test.getMacchinaAllocata(idContratto[i]);
			assertTrue("Caso di testMacchinaAllocati: " + i, result.equals(expected[i]));
			}
	}
	
	@Test
	public void testGetTariffaBaseAllocata() {
		String[] idContratto = {"1", "99", null};
		double[] expected={25, 25, 0};
		double result ;
		
		for (int i = 0; i < idContratto.length; i++) {
			result = test.getTariffaBaseAllocata(idContratto[i]);
			assertTrue("Caso di testGetTariffaBaseAllocata: " + i, (result==expected[i]));
			}
	}

	@Test
	public void testGetPrezzoExtraAllocata() {
		String[] idContratto = {"1", "99", null};
		double[] expected={25, 25, 0};
		double result ;
		
		for (int i = 0; i < idContratto.length; i++) {
			result = test.getPrezzoExtraAllocata(idContratto[i]);
			assertTrue("Caso di testGetPrezzoExtraAllocata: " + i, (result==expected[i]));
			}
	}

	@Test
	public void testGetCostoTotAllocato() {
		String[] idContratto = {"1", "99", null};
		double[] expected={150.00, 150.00, 0};
		double result ;
		
		for (int i = 0; i < idContratto.length; i++) {
			result = test.getCostoTotAllocato(idContratto[i]);
			assertTrue("Caso di testGetPrezzoExtraAllocata: " + i, (result==expected[i]));
			}
	}

}

