package dataAccess.mysql;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import dataAccess.entity.UtenteDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link UtenteDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlUtenteDao implements UtenteDao {

	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		String result = null;
		String query = "SELECT tipo FROM DatiAccesso "
				+ "WHERE BINARY username = ? AND password = ?";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();

			if (results.next()) {
				result = results.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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

	@Override
	public boolean isAvailableUsername(String username) {
		boolean result = true;
		String query = "SELECT username FROM DatiAccesso "
				+ "WHERE BINARY username = ?";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, username);

			ResultSet results = statement.executeQuery();

			if (results.next()) {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e, GestioneErroriInt.WARNING);
		} finally {
			try {
				if(statement != null) {
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

	@Override
	public boolean isAvailableEmail(String email) {
		boolean result = true;
		String query = "SELECT email FROM ((SELECT email FROM Cliente) "
				+ "UNION "
				+ "(SELECT email FROM Dipendente) "
				+ "UNION "
				+ "(SELECT email FROM ManagerSistema)) AS email "
				+ "WHERE email = ?;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, email);

			ResultSet results = statement.executeQuery();

			if (results.next()) {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e, GestioneErroriInt.WARNING);
		} finally {
			try {
				if(statement != null) {
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
