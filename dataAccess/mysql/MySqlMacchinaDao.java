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

import dataAccess.entity.MacchinaDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link PartecipanteDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlMacchinaDao implements MacchinaDao {

	@Override
	public HashMap<String, String> getDatiMacchina(String targa) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		String query = "SELECT modello, disponibilità, manuntenzione, ultimoKm, fascia"
				+ " FROM Macchina WHERE targa = ?";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, targa);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String modello = results.getString("modello");
				result.put("modello", modello);
				String disponibilità = results.getString("disponibilità");
				result.put("disponibilità", disponibilità);
				String manuntenzione = results.getString("manuntenzione");
				result.put("manuntenzione", manuntenzione);
				String ultimoKm = results.getString("ultimoKm");
				result.put("ultimoKm", ultimoKm);
				String fascia = results.getString("fascia");
				result.put("fascia", fascia);
				result.put("targa", targa);
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
	public boolean setDatiMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query = "UPDATE Macchina "
					+ "SET modello = ?, disponibilità = ?, manuntenzione = ?, "
					+ "ultimoKm = ?, fascia = ?, "
					+ "WHERE targa = ?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setString(1, modello);
			statement.setString(2, disponibilita);
			statement.setString(3, manuntenzione);
			statement.setLong(4, ultimoKm);
			statement.setString(5, fascia);
			

			

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
	public boolean registraMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia)  {
		// TODO Auto-generated method stub
		boolean inserito = false;
		String inserimentoMacchina = ""
				+ "INSERT INTO Macchina "
				+ "(targa, modello, disponibilità, manuntenzione, ultimoKm, fascia) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement sMacchina = null;
		Connection con = null;
		try {
			con = MySqlDaoFactory.connetti();
			con.setAutoCommit(false);
			sMacchina = con.prepareStatement(inserimentoMacchina);
			int i = 1;
			sMacchina.setString(i, targa);
			i++;
			sMacchina.setString(i, modello);
			i = 1;
			sMacchina.setString(i, disponibilita);
			i++;
			sMacchina.setString(i, manuntenzione);
			i++;
			sMacchina.setLong(i, ultimoKm);
			i++;
			sMacchina.setString(i, fascia);
			inserito = sMacchina.executeUpdate() == 1;
			if (inserito) {
				// inserimento account eseguito correttamente
				inserito = sMacchina.executeUpdate() == 1;
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
				if (sMacchina != null) {
					sMacchina.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return inserito;
	}

	@Override
	public ArrayList<HashMap<String, String>> getMacchinaSistema() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT targa,  modello, disponibilità "
				+ "FROM Macchina;";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String,String>>();
			while (results.next()) {
				HashMap<String, String> macchina = new HashMap<String, String>();
				String targa = results.getString("targa");
				macchina.put("targa", targa);
				String modello = results.getString("modello");
				macchina.put("modello", modello);
				String disponibilità = results.getString("disponibilità");
				macchina.put("disponibilità", disponibilità);
				result.add(macchina);
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
	public ArrayList<HashMap<String, String>> ricercaMacchine(String key) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT targa,  modello, disponibilità"
				+ "FROM Macchina "
				+ "WHERE targa = ? OR modello LIKE ? OR fascia = ?;";
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
				HashMap<String, String> macchina = new HashMap<String, String>();
				String targa = results.getString("targa");
				macchina.put("targa", targa);
				String modello = results.getString("modello");
				macchina.put("modello", modello);
				String disponibilità = results.getString("disponibilità");
				macchina.put("disponibilità", disponibilità);
				result.add(macchina);
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




	@Override
	public boolean eliminaMacchina(String targa) {
		// TODO Auto-generated method stub
				boolean controll = false;
				String query = ""
						+ "DELETE FROM Macchina "
						+ "WHERE targa = ?;";
				PreparedStatement statement = null;
				try {
					Connection con = MySqlDaoFactory.connetti();
					statement = con.prepareStatement(query);
					statement.setString(1, targa);
					controll = statement.executeUpdate() == 1;
				} catch (SQLException e) {
					gestisciEccezione(e, GestioneErroriInt.WARNING);
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
				return controll;
	}
}
