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

import dataAccess.entity.DipendenteDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link PartecipanteDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlDipendenteDao implements DipendenteDao {

	@Override
	public HashMap<String, String> getDatiDipendente(String username) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		String query = "SELECT CF FROM Dipendente WHERE username = ?";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String CF = results.getString("CF");
				result.put("CF", CF);
				result.put("username", username);
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
	public ArrayList<HashMap<String, String>> getContrattiDipendente(String id) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		String query = "SELECT id, DATE_FORMAT( "
				+ "dataInizio, \"%d/%m/%Y %H:%i\") AS data "
				+ "FROM Contratto "
				+ "WHERE CFDipendente = ?));";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String, String>>();
			while (results.next()) {
				HashMap<String, String> contratto = new HashMap<String, String>();
				String idContratto = results.getString(1);
				contratto.put("id", idContratto);
				String data = results.getString(2);
				contratto.put("data", data);
				

				result.add(contratto);
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
	public HashMap<String, String> getDipendente(String username) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		Connection con = null;
		String query = "SELECT nome, cognome, CF, DATE_FORMAT(dataNascita, '%d/%m/%Y') AS dataNascita, "
				+ "indirizzo, citta, CAP, numTelefono "
				+ "FROM Dipendente WHERE CF = ?";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				result.put("username", username);
				String nome = results.getString("nome");
				result.put("nome", nome);
				String cognome = results.getString("cognome");
				result.put("cognome", cognome);
				String CF = results.getString("CF");
				result.put("CF", CF);
				String dataNascita = results.getString("dataNascita");
				result.put("dataNascita", dataNascita);
				String indirizzo = results.getString("indirizzo");
				result.put("indirizzo", indirizzo);
				String CAP = results.getString("CAP");
				result.put("CAP", CAP);
				String citta = results.getString("citta");
				result.put("citta", citta);
				String numTelefono = results.getString("numTelefono");
				result.put("numTelefono", numTelefono);
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
	public HashMap<String, String> getAccountDipendente(String username) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		Connection con = null;
		String query = "SELECT password, email, username "
				+ "FROM DatiAccesso NATURAL JOIN Dipendente "
				+ "WHERE username = ?";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String password = results.getString("password");
				result.put("password", password);
				String email = results.getString("email");
				result.put("email", email);
				result.put("username", username);
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
	public boolean setDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono){
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query = "UPDATE Dipendente "
					+ "SET nome = ?, cognome = ?, dataNascita = ?, "
					+ "CF = ?, citta = ?, "
					+ "indirizzo = ?, CAP = ?, NumTelefono = ?" + "WHERE CF = ?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, dataNascita);
			statement.setString(4, cf);
			statement.setString(5, città);
			statement.setString(6, indirizzo);
			statement.setString(7, cap);
			statement.setString(8, numTelefono);

			

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
	public boolean setAccountDipendente(String username, String email, String password) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query = "UPDATE DatiAccesso " + "NATURAL JOIN Dipendente "
					+ "SET email = ?, password = ? "
					+ "WHERE DatiAccesso.username = ?;";
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, username);
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

	@Override
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String CFDipendente, String idContratto) {
		ArrayList<HashMap<String, String>> result = null;
		String query = "SELECT id, DATE_FORMAT( "
				+ "dataInizio, \"%d/%m/%Y %H:%i:%s\") AS data "
				+ "FROM Contratto "
				+ "WHERE CFDipendente = ?;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, CFDipendente);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String, String>>();
			while (results.next()) {
				HashMap<String, String> contratto = new HashMap<String, String>();
				String idCont = results.getString(1);
				contratto.put("id", idCont);
				String data = results.getString(2);
				contratto.put("data", data);
				
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

	

	@Override
	public boolean registraDipendente(String username, String password,
			String email, String nome, String cognome, String dataNascita,
			String CF, String città, String indirizzo, String cap,
			String numTelefono)  {
		// TODO Auto-generated method stub
		boolean inserito = false;
		String queryAccount = "" + "INSERT INTO DatiAccesso "
				+ "VALUES (?, ?, 'Dipendente')";
		String queryDipendente = "INSERT INTO Dipendente "
				+ "(nome, cognome, CF, dataNascita, indirizzo, "
				+ " citta, CAP, numTelefono, username, email, password) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement sAccount = null;
		PreparedStatement sDipendente = null;
		Connection con = null;
		try {
			con = MySqlDaoFactory.connetti();
			con.setAutoCommit(false);
			sAccount = con.prepareStatement(queryAccount);
			sDipendente = con.prepareStatement(queryDipendente);
			int i = 1;
			sAccount.setString(i, username);
			i++;
			sAccount.setString(i, password);
			i = 1;
			sDipendente.setString(i, nome);
			i++;
			sDipendente.setString(i, cognome);
			i++;
			sDipendente.setString(i, CF);
			i++;
			sDipendente.setString(i, dataNascita);
			i++;
			sDipendente.setString(i, indirizzo);
			i++;
			sDipendente.setString(i, città);
			i++;
			sDipendente.setString(i, cap);
			i++;
			sDipendente.setString(i, username);
			i++;
			sDipendente.setString(i, email);
			i++;
			sDipendente.setString(i, password);
			inserito = sAccount.executeUpdate() == 1;
			if (inserito) {
				// inserimento account eseguito correttamente
				inserito = sDipendente.executeUpdate() == 1;
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
				if (sAccount != null) {
					sAccount.close();
				}
				if (sDipendente != null) {
					sDipendente.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return inserito;
	}

	@Override
	public ArrayList<HashMap<String, String>> getDipendenteSistema() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT CF,  nome, cognome "
				+ "FROM Dipendente;";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String,String>>();
			while (results.next()) {
				HashMap<String, String> dipendente = new HashMap<String, String>();
				String CF = results.getString("CF");
				dipendente.put("CF", CF);
				String nome = results.getString("nome");
				dipendente.put("nome", nome);
				String cognome = results.getString("cognome");
				dipendente.put("cognome", cognome);
				result.add(dipendente);
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
	public ArrayList<HashMap<String, String>> ricercaDipendente(String key) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT CF,  nome, cognome"
				+ "FROM Dipendente "
				+ "WHERE nome LIKE ? OR cognome LIKE ? OR CF = ?;";
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
				HashMap<String, String> dipendente = new HashMap<String, String>();
				String CF = results.getString("CF");
				dipendente.put("CF", CF);
				String nome = results.getString("nome");
				dipendente.put("nome", nome);
				String cognome = results.getString("cognome");
				dipendente.put("cognome", cognome);
				result.add(dipendente);
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
