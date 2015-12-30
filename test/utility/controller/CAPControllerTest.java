package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.CAPController;
import utility.controller.Controller;

public class CAPControllerTest {

	CAPControllerTest test;
	@Before
	public void setUp() throws Exception {
		test = new CAPControllerTest();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, false};
		String[] string = {"76123", "C"};
		
		for (int i = 0; i < expected.length; i++) {
			boolean result = ((Controller) test).check(string[i]);
			assertTrue("Caso di testCheck: " + i, result == expected[i]);
		}
	}

}
