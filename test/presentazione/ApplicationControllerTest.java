package test.presentazione;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentazione.ApplicationController;
import presentazione.ApplicationControllerInt;
import utility.ReaderXMLStub;

public class ApplicationControllerTest {
	
	ApplicationControllerInt test;
	String[] keys = {"P1", "P2", "P3", "P4"};
	String[] results = {"0", "0", "1", "2"};
	ArrayList<String> p;

	@Before
	public void setUp() throws Exception {
		test = new ApplicationController();
		p = new ArrayList<String>();
		p.add("test");
	} 

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public final void testHandleRequest() {
		test.setReader(new ReaderXMLStub("src/presentazione/ACTest.xml"));
		String result;
		for (int i = 0; i < keys.length; i++) {
			if (i == 3) {
				p = null;
			}
			result = (String) test.handleRequest(keys[i], p);
			assertTrue("Caso di test: " + i + "; " + results[i] + " = " +result, results[i].equals(result));
		}
	}

}
