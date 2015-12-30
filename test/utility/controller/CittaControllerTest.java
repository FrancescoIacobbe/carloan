package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.CittaController;

public class CittaControllerTest {

	CittaController test;
	@Before
	public void setUp() throws Exception {
		test = new CittaController();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, true, false, false, false};
		String[] string = {"BA", null, "B", null, "Bari1"};
		
		char c = 'A';
		String s1 = "";
		for (int i = 0; i < 40; i++) {
			s1 += c;
		}
		string[1] = s1;
		string[3] = s1 + c;
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.check(string[i]);
			assertTrue("Caso di testCheck: " + i, result == expected[i]);
		}
	}

}
