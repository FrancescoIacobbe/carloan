package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.CFController;

public class CFControllerTest {

	CFController test;
	@Before
	public void setUp() throws Exception {
		test = new CFController();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, false, false, false};
		String[] string = {"CLNGLC93M16A662C", "CLNGLC93M16A662", "CLNGLC93M16A662CA", "CLNGLC93M16A66UC"};
				
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.check(string[i]);
			assertTrue("Caso di testCheck: " + i, result == expected[i]);
		}
	}

}
