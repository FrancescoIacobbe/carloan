package test.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.GestioneProperties;

public class GestionePropertiesTest {

	GestioneProperties test;
	@Before
	public void setUp() throws Exception {
		test = new GestioneProperties("src/config/test.cfg");
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testAddValue() {
		String expected = "testValue";
		String key = "testKey";
		test.addValue(key, expected);
		String result = test.getValue(key);
		assertTrue("Caso di testAddValue", result.equals(expected));
	}

}
