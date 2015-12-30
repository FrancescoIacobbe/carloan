package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.GestioneManagerSistema;
import presentazione.GestioneSessione;

public class GestioneManagerSistemaTest {
	
	GestioneManagerSistema test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneManagerSistema();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testSetAccount() {
		boolean expected;
		boolean result;
		
		ArrayList<String> p = new ArrayList<>();
		p.add("manager@gmail.com");
		p.add("manager");
		expected = true;
		result = test.setAccount(p);
		assertTrue("Caso testSetAccount 0", result == expected);
	}

}
