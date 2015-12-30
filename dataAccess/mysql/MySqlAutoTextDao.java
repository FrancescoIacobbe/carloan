package dataAccess.mysql;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.factory.MySqlDaoFactory;
import dataAccess.utility.AutoTextDao;

/**
 * Implementazione di {@link AutoTextDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlAutoTextDao implements AutoTextDao {

	@Override
	public String autoText(String key, String table, String field) {
		String result = null;
		String query = "SELECT " + field + " FROM " + table + " WHERE "
				+ field + " LIKE ? ORDER BY " + field + " ASC LIMIT 1;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, key + "%");

			ResultSet results = statement.executeQuery();

			if (results.next()) {
				result = results.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.NO_MESSAGE);
		} finally {
			try {
				if(statement != null) {
					statement.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DAOException ex = new DAOException(e.getMessage());
				GestioneErroriInt ge = GestioneErrori.getIstance();
				ge.submitError(ex.getClass(), ex, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return result;
	}
}
