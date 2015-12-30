package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.UsernameController;

public class UsernameControllerTest {

	UsernameController test;
	@Before
	public void setUp() throws Exception {
		test = new UsernameController();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, true, false, false, false};
		String[] string = {"user", "username.-_0123", "A", null, "!username"};
		
		char c = 'A';
		String s1 = "";
		for (int i = 0; i < 16; i++) {
			s1 += c;
		}
		string[3] = s1;
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.check(string[i]);
			assertTrue("Caso di testCheck: " + i, result == expected[i]);
		}
	}

}
