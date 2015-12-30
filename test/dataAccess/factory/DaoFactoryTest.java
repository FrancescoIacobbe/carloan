package test.dataAccess.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import dataAccess.factory.DaoFactory;
import dataAccess.factory.MySqlDaoFactory;

public class DaoFactoryTest {


	@Test
	public void testGetDaoFactory() {
		DaoFactory[] expected = {MySqlDaoFactory.getIstance(), null};
		int[] value = {DaoFactory.MYSQL, 100};
		
		for (int i = 0; i < expected.length; i++) {
			DaoFactory result = DaoFactory.getDaoFactory(value[i]);
			if (result == null) {
				assertNull("Caso di testGetDaoFactory: " + i, expected[i]);
			} else {
				assertTrue("Caso di testGetDaoFactory: " + i, result.equals(expected[i]));
			}
		}
	}

}
