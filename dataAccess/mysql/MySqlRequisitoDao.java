package dataAccess.mysql;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import dataAccess.entity.RequisitoDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link PartecipanteDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlRequisitoDao implements RequisitoDao {

	@Override
	public HashMap<String, String> getDatiRequisito(String idRequisito) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		String query = "SELECT descrizione, modelli"
				+ " FROM Requisito WHERE id = ?";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idRequisito);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String descrizione = results.getString("descrizione");
				result.put("descrizione", descrizione);
				String modelli = results.getString("modelli");
				result.put("modelli", modelli);
				result.put("id", idRequisito);
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
	public boolean setDatiRequisito(String id, String modelli, String descrizione) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query = "UPDATE Requisito "
					+ "SET modelli = ?, descrizione = ?"
					+ "WHERE id = ?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, id);
			statement.setString(2, descrizione);
			statement.setString(3, modelli);
			result = statement.executeUpdate() == 1;
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

	
	
	@Override
	public boolean registraRequisito(String id, String modelli, String descrizione)  {
		// TODO Auto-generated method stub
		boolean inserito = false;
		String inserimentoRequisito = ""
				+ "INSERT INTO Requisito "
				+ "(id, descrizione, modelli) "
				+ "VALUES(?, ?, ?);";
		PreparedStatement sRequisito = null;
		Connection con = null;
		try {
			con = MySqlDaoFactory.connetti();
			con.setAutoCommit(false);
			sRequisito = con.prepareStatement(inserimentoRequisito);
			int i = 1;
			sRequisito.setString(i, id);
			i++;
			sRequisito.setString(i, descrizione);
			i = 1;
			sRequisito.setString(i, modelli);
			inserito = sRequisito.executeUpdate() == 1;
			if (inserito) {
				// inserimento account eseguito correttamente
				inserito = sRequisito.executeUpdate() == 1;
			}
			if (inserito) {
				// inserimento partecipante avvenuto correttamente
				con.commit();
			}
		} catch (SQLException e) {
			inserito = false;
			gestisciEccezione(e, GestioneErroriInt.WARNING);
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					gestisciEccezione(e1, GestioneErroriInt.WARNING);
				}
			}
		} finally {
			try {
				if (sRequisito != null) {
					sRequisito.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return inserito;
	}

	@Override
	public ArrayList<HashMap<String, String>> getRequisitoSistema() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT id, modelli, descrizione"
				+ "FROM Requisito;";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String,String>>();
			while (results.next()) {
				HashMap<String, String> requisito = new HashMap<String, String>();
				String id = results.getString("id");
				requisito.put("id", id);
				String descrizione = results.getString("descrizione");
				requisito.put("descrizione", descrizione);
				String modelli = results.getString("modelli");
				requisito.put("modelli", modelli);
			
				result.add(requisito);
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
	public ArrayList<HashMap<String, String>> ricercaRequisito(String key) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT id,  modelli, descrizione"
				+ "FROM Requisito "
				+ "WHERE id = ? OR modelli LIKE ?;";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			int i = 1;
			statement.setString(i, "%"+ key + "%");
			i++;
			statement.setString(i, "%"+ key + "%");
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String,String>>();
			while (results.next()) {
				HashMap<String, String> requisito = new HashMap<String, String>();
				String id = results.getString("id");
				requisito.put("id", id);
				String descrizione = results.getString("descrizione");
				requisito.put("descrizione", descrizione);
				String modelli = results.getString("modelli");
				requisito.put("modelli", modelli);
				
				result.add(requisito);
			}
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
	 */
	private void gestisciEccezione(Exception e, Level l) {
		DAOException ex = new DAOException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, l);
	}
}
