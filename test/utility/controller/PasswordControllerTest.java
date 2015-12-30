package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.PasswordController;

public class PasswordControllerTest {

	PasswordController test;
	@Before
	public void setUp() throws Exception {
		test = new PasswordController();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, true, false, false, false};
		String[] string = {"pass.-_9", null, "passwor", null, "!password"};
		
		char c = 'C';
		String s1 = "";
		for (int i = 0; i < 15; i++) {
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
