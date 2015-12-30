package test.dataAccess.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.mysql.MySqlClienteDao;

public class MySqlClienteDaoTest {

	MySqlClienteDao test;
	
	@Before
	public void setUp() throws Exception {
		test = new MySqlClienteDao();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetDatiCliente() {
		HashMap<String, String>[] expected = new HashMap[3];
		String[] username = {"cliente1", null, "prova"};
		
		HashMap<String, String> p1 = new HashMap<String,String>();
		p1.put("id", "1");
		p1.put("username", "cliente1");
		
		expected[0] = p1;
		expected[1] = expected[2] = null;
		
		for (int i = 0; i < expected.length; i++) {
			HashMap<String, String> result = test.getDatiCliente(username[i]);
			if (result == null) {
				assertNull("Caso di testGetDati: " + i, expected[i]);
			} else {
				assertTrue("Caso di testGetDati: " + i, result.equals(expected[i]));
			}
		}
	}

	@Test
	public void testGetContrattoCliente() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[3];
		String[] id = {"4", null, "prova"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> p1 = new HashMap<String,String>();
		p1.put("id", "4");
		p1.put("data", "07/08/2014");
		a1.add(p1);
		
		expected[0] = a1;
		expected[1] = expected[2] = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String, String>> result = test.getContrattoCliente(id[i]);
			assertTrue("Caso di test getContrattoCliente: " + i, result.equals(expected[i]));
		}
	}

	@Test
	public void testGetAnagraficaCliente() {
		HashMap<String, String>[] expected = new HashMap[3];
		String[] id = {"antonella4", null, "prova"};
		
		HashMap<String, String> p1 = new HashMap<String,String>();
		
		p1.put("username", "cliente1");
		p1.put("nome", "Claudio");
		p1.put("cognome", "Neri");
		p1.put("CF", "0123456789012345");
		p1.put("dataNascita", "25/10/1993");
		p1.put("indirizzo", "Via Olbia, 27");
		p1.put("CAP", "76125");		
		p1.put("citta", "Bari");		
		p1.put("numTelefono", "0804512487");
		
		expected[0] = p1;
		expected[1] = expected[2] = null;
		
		for (int i = 0; i < expected.length; i++) {
			HashMap<String, String> result = test.getAnagraficaCliente(id[i]);
			if (result == null) {
				assertNull("Caso di testGetAnagrafica: " + i, expected[i]);
			} else {
				assertTrue("Caso di testGetAnagrafica: " + i, result.equals(expected[i]));
			}
		}
	}

	@Test
	public void testGetAccount() {
		HashMap<String, String>[] expected = new HashMap[3];
		String[] username = {"cliente1", null, "prova"};
		
		HashMap<String, String> p1 = new HashMap<String,String>();
		p1.put("username", "cliente1");
		p1.put("email", "cliente1@gmail.it");
		p1.put("password", "Cliente1");
		
		expected[0] = p1;
		expected[1] = expected[2] = null;
		
		for (int i = 0; i < expected.length; i++) {
			HashMap<String, String> result = test.getAccountCliente(username[i]);
			if (result == null) {
				assertNull("Caso di testGetAccount: " + i, expected[i]);
			} else {
				assertTrue("Caso di testGetAccount: " + i, result.equals(expected[i]));
			}
		}
	}

	@Test
	public void testSetAnagrafica() {
		boolean[] expected = {true, false, false, false, false, false, false, false, false, false, false, false, false};
		String[] nome = {"Francesco", "Francesco", "Francesco", null, "Francesco", "Francesco", "Francesco", "Francesco", "Francesco", "Francesco", "Francesco", "Francesco", "Francesco"};
		String[] cognome = {"Mariani", "Mariani", "Mariani", "Mariani", null, "Mariani", "Mariani", "Mariani", "Mariani", "Mariani", "Mariani", "Mariani", "Mariani"};
		String[] dataNascita = {"1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", null, "prova", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16"};
		String[] CF = {"0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", null, "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345"};
		String[] città = {"Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", null, "Bari", "Bari"};
		String[] indirizzo = {"Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", "Via Olbia,21", null, "Via Olbia,21"};
		String[] CAP = {"76156", "76156", "76156", "76156", "76156", "76156", "76156", "76156", "76156", "76156", "76156", "76156", null};
		String[] numTelefono = {"0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "0801543694", "208015436947", null};

		for (int i = 0; i < expected.length; i++) {
			boolean result = test.setAnagraficaCliente(nome[i], cognome[i],dataNascita[i], CF[i], città[i], indirizzo[i], CAP[i], numTelefono[i]);
			assertTrue("Caso di testSetAnagrafica: " + i, result == expected[i]);
		}
	}

	@Test
	public void testSetAccount() {
		boolean[] expected = {true, false, false, false, false};
		String[] username = {"cliente1", null, "prova", "cliente1", "cliente1"};
		String[] email = {"cliente1Nuova@gmail.it", "cliente1Nuova@gmail.it", "cliente1Nuova@gmail.it", null, "cliente1Nuova@gmail.it"};
		String[] password = {"password", "password", "password", "password", null};
		
		for(int i = 0; i < expected.length; i++) {
			boolean result = test.setAccountCliente(username[i], email[i], password[i]);
			assertTrue("Caso di testSetAccount: " + i, result == expected[i]);
		}
	}

	
	@Test
	public void testRicercaContratti() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[4];
		String[] id = {"1", null, "prova", "1"};
		String[] idContratto = {"21", "21", "21", "noResult"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> c1 = new HashMap<String, String>();
		c1.put("idContratto", "33");
		c1.put("data", "07/08/2014");
		a1.add(c1);
		
		expected[0] = a1;
		expected[1] = expected[2] = expected[3] = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String, String>> result = test.ricercaContratti(id[i], idContratto[i]);
			assertTrue("Caso di testRicercaCompetizioniConcluse: " + i, result.equals(expected[i]));
		}
	}

	@Test
	public void testRegistraCliente() {
		boolean[] expected = {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		String[] username = {"cliente20", null, "cliente1", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20"};
		String[] password = {"cliente20", "cliente20", "cliente20", null, "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20", "cliente20"};
		String[] email = {"cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", null, "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it", "cliente20@gmail.it"};
		String[] nome = {"Antonella", "Antonella", "Antonella", "Antonella", "Antonella", null, "Antonella", "Antonella", "Antonella", "Antonella", "Antonella", "Antonella", "Antonella", "Antonella", "Antonella"};
		String[] cognome = {"Carella", "Carella", "Carella", "Carella", "Carella", "Carella", null, "Carella", "Carella", "Carella", "Carella", "Carella", "Carella", "Carella", "Carella"};
		String[] dataNascita = {"1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", null, "prova", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16", "1993/08/16"};
		String[] cf = {"0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", null, "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345", "0123456789012345"};
		String[] città = {"Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", "Bari", null, "Bari", "Bari", "Bari"};
		String[] indirizzo = {"Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", "Via Piemonte,14", null, "Via Piemonte,14", "Via Piemonte,14"};
		String[] CAP = {"70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", "70456", null, "70456"};
		String[] numTelefono = {"0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", "0883154517", null};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.registraCliente(username[i], password[i], email[i], nome[i], cognome[i], dataNascita[i], cf[i], città[i], indirizzo[i], CAP[i], numTelefono[i]);
			assertTrue("Caso di testNuovo: " + i, result == expected[i]);
		}
	}

	@Test
	public void testGetClientiSistema() {
		ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> p1 = new HashMap<String,String>();
		p1.put("CF", "DMLGNL125SDF784V");
		p1.put("nome", "Gianluca");
		p1.put("Cognome", "Domanico");
		
		expected.add(p1);
		
		HashMap<String, String> p2 = new HashMap<String,String>();
		p2.put("CF", "DFVNMY578MNO123E");
		p2.put("nome", "Giuseppe");
		p2.put("cognome", "Neri");
		expected.add(p2);
		
		HashMap<String, String> p3 = new HashMap<String,String>();
		p3.put("CF", "FNMCFE215MNI123F");
		p3.put("nome", "Maria");
		p3.put("cognome", "Giordano");
		expected.add(p3);
		
		HashMap<String, String> p4 = new HashMap<String,String>();
		p4.put("CF", "MLOFCA154MOL123W");
		p4.put("nome", "Giuseppe");
		p4.put("cognome", "Salamino");
		expected.add(p4);
		
		HashMap<String, String> p5 = new HashMap<String,String>();
		p5.put("CF", "LPOKLO154DFV125H");		
		p5.put("nome", "Fabrizio");
		p5.put("cognome", "Moro");
		expected.add(p5);
		
		HashMap<String, String> p6 = new HashMap<String,String>();
		p6.put("CF", "PPSLRA458FGT789P");
		p6.put("nome", "Laura");
		p6.put("cognome", "Pausini");
		expected.add(p6);
		
		HashMap<String, String> p7 = new HashMap<String,String>();
		p7.put("CF", "MDFEER458VBG123B");
		p7.put("nome", "Emma");
		p7.put("cognome", "Marrone");
		expected.add(p7);
		
		HashMap<String, String> p8 = new HashMap<String,String>();
		p8.put("CF", "BGLCLD456ADE126G");

		p8.put("nome", "Claudio");
		p8.put("cognome", "Baglioni");
		expected.add(p8);
		
		ArrayList<HashMap<String, String>> result = test.getClientiSistema();
		assertTrue("Caso di test getClientiSistema", result.equals(expected));
	}

	@Test
	public void testRicercaCliente() {
		ArrayList<HashMap<String, String>>[] expected = new ArrayList[2];
		String[] key = {"aur", "noResult"};
		
		ArrayList<HashMap<String, String>> a1 = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> p6 = new HashMap<String,String>();
		p6.put("CF", "PSNLRA254VFD123G");
		p6.put("nome", "Laura");
		p6.put("cognome", "Pausini");
		a1.add(p6);
		
		expected[0] = a1;
		expected[1] = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < expected.length; i++) {
			ArrayList<HashMap<String, String>> result = test.ricercaClienti(key[i]);
			assertTrue("Caso di testRicercaClienti: " + i, result.equals(expected[i]));
		}
	}

}
