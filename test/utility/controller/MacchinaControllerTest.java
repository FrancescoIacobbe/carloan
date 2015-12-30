package test.utility.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.controller.MacchinaController;

public class MacchinaControllerTest {

	MacchinaController test;
	@Before
	public void setUp() throws Exception {
		test = new MacchinaController();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testCheck() {
		boolean[] expected = {true, false, false, false};
		String[] string = {"DK802FN", "00000000000", "ABABA3", "JKGH45349"};
				
		for (int i = 0; i < expected.length; i++) {
			boolean result = test.check(string[i]);
			assertTrue("Caso di testCheck: " + i, result == expected[i]);
		}
	}

}
