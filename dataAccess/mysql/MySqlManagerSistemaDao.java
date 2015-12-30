package dataAccess.mysql;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;

import dataAccess.entity.ManagerSistemaDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link ManagerSistemaDao} per DBMS MySql.
 * @author Gianluca
 *
 */
public class MySqlManagerSistemaDao implements ManagerSistemaDao{

	@Override
	public HashMap<String, String> getAccountManager() {
		// TODO Auto-generated method stub
		String query = "SELECT password, email "
				+ "FROM ManagerSistema NATURAL JOIN DatiAccesso "
				+ "WHERE id = 1;";
		PreparedStatement statement = null;
		HashMap<String, String> result = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String password = results.getString("password");
				result.put("password", password);
				String email = results.getString("email");
				result.put("email", email);
			}
		} catch (SQLException e) {
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return result;
	}

	@Override
	public boolean setAccountManager(String email, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		String query = "UPDATE ManagerSistema NATURAL JOIN DatiAccesso "
				+ "SET email = ?, password = ? "
				+ "WHERE id = 1;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			//il confronto viene eseguito con il valore 2 perché 2 sono le righe
			//effettivamente coinvolte nell'aggiornamento.
			result = statement.executeUpdate() == 2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e, GestioneErroriInt.WARNING);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return result;
	}
	
	/**
	 * Invia la richiesta al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 * @param l il livello dell'eccezione.
	 */
	private void gestisciEccezione(Exception e, Level l) {
		DAOException ex = new DAOException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, l);
	}
}
