package test.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.GestioneAccesso;

public class GestioneAccessoTest {
	
	GestioneAccesso test;

	@Before
	public void setUp() throws Exception {
		test = new GestioneAccesso();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testLogin() {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> expected = new ArrayList<>();
		ArrayList<String> loginCorretto = new ArrayList<>();
		loginCorretto.add("manager");
		loginCorretto.add("12345678");
		expected.add("ManagerSistema");
		expected.add("manager");
		result = test.login(loginCorretto);
		assertTrue("Caso di testLogin 1", result.equals(expected));
		
		ArrayList<String> loginErrato = new ArrayList<>();
		loginErrato.add("cliente");
		loginErrato.add("password");
		expected.clear();
		expected.add(null);
		expected.add("cliente");
		result = test.login(loginErrato);
		assertTrue("Caso di testLogin 2", result.equals(expected));		
	}

	@Test
	public void testIsAvailableUsername() {
		boolean result;
		boolean expected;
		
		ArrayList<String> usernameValido = new ArrayList<>();
		usernameValido.add("usernameNonPresente");
		expected = true;
		result = test.isAvailableUsername(usernameValido);
		assertTrue("Caso di testIsAvailableUsername 1", result == expected);	

		ArrayList<String> usernameNonValido = new ArrayList<>();
		usernameNonValido.add("cliente1");
		expected = false;
		result = test.isAvailableUsername(usernameNonValido);
		assertTrue("Caso di testIsAvailableUsername 2", result == expected);	
	}

	@Test
	public void testIsAvailableEmail() {
		boolean result;
		boolean expected;
		
		ArrayList<String> emailValida = new ArrayList<>();
		emailValida.add("prova@email.it");
		expected = true;
		result = test.isAvailableEmail(emailValida);
		assertTrue("Caso di testIsAvailableEmail 1", result == expected);	

		ArrayList<String> emailNonValida = new ArrayList<>();
		emailNonValida.add("cliente1@gmail.it");
		expected = false;
		result = test.isAvailableEmail(emailNonValida);
		assertTrue("Caso di testIsAvailableEmail 2", result == expected);
	}

}
