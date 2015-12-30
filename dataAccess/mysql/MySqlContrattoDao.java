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
import java.util.Map;
import java.util.logging.Level;

import com.mysql.jdbc.Statement;

import dataAccess.entity.ContrattoDao;
import dataAccess.factory.MySqlDaoFactory;

/**
 * Implementazione di {@link CompetizioneDao} per DBMS MySql.
 * 
 * @author Gianluca
 * 
 */
public class MySqlContrattoDao implements ContrattoDao {
	
	public ArrayList<HashMap<String, String>> getContrattoSistema(){
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = null;
		Connection con = null;
		String query = "SELECT id,  dataInizio"
				+ "FROM Contratto;";
		PreparedStatement statement = null;
		try {
			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String,String>>();
			while (results.next()) {
				HashMap<String, String> contratto = new HashMap<String, String>();
				String id = results.getString("id");
				contratto.put("id", id);
				String dataInizio = results.getString("dataInizio");
				contratto.put("dataInizio", dataInizio);

				result.add(contratto);
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
				// TODO Auto-generated catch block
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return result;
	}

	@Override
	public HashMap<String, String> getDatiContratto(String idContratto) {
		HashMap<String, String> result = null;
		String query= "SELECT id,DATE_FORMAT(dataInizio, '%d/%m/%Y') AS dataInizio,"
		+ " DATE_FORMAT(dataLimite, '%d/%m/%Y') AS dataLimite, "
		+ "DATE_FORMAT(dataRestituzione, '%d/%m/%Y') AS dataRestituzione,"
		+ " luogoRestituzione,tipoNoleggio, tipoKm, tipoFascia, costoKm,"
		+ "kmEffettivi, prezzoExtra, acconto"
		+ " FROM Contratto WHERE id = ?";
		
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String id = results.getString("id");
				result.put("id", id);
				String dataInizio = results.getString("dataInizio");
				result.put("dataInizio", dataInizio);
				String dataLimite = results.getString("dataLimite");
				result.put("dataLimite", dataLimite);
				String dataRestituzione = results.getString("dataRestituzione");
				result.put("dataRestituzione", dataRestituzione);
				String luogoRestituzione = results.getString("luogoRestituzione");
				result.put("luogoRestituzione", luogoRestituzione);
				String tipoNoleggio = results.getString("tipoNoleggio");
				result.put("tipoNoleggio", tipoNoleggio);
				String tipoKm = results.getString("tipoKm");
				result.put("tipoKm", tipoKm);
				String tipoFascia = results.getString("tipoFascia");
				result.put("tipoFascia", tipoFascia);
				String costoKm = "" + results.getDouble("costoKm");
				result.put("costoKm", costoKm);
				String kmEffettivi = "" + results.getInt("kmEffettivi");
				result.put("kmEffettivi", kmEffettivi);
				String prezzoExtra = "" + results.getDouble("prezzoExtra");
				result.put("prezzoExtra", prezzoExtra);
				String acconto = "" + results.getDouble("acconto");
				result.put("acconto", acconto);
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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
	public boolean setMacchinaAllocata(String idContratto, String macchina) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query =  "UPDATE  Contratto "
					+ " SET Macchina=?"
					+ "WHERE id=?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setString(8,macchina);
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
	public boolean compilaContratto(String CFDipendente, String id,
			String dataInizio, String dataLimite, String dataRestituzione,
			String luogoRestituzione, String tipoNoleggio, String tipoKm,
			String tipoFascia, double costoKm, int kmEffettivi, double acconto) {
		String inserimentoContratto = ""
				+ "INSERT INTO Contratto "
				+ "(CFDipendente, id, dataInizio, dataLimite, dataRestituzione, luogoRestituzione,"
				+ "tipoNoleggio,macchina, tipoKm, tipoFascia,tariffaBase, costoKm, kmEffettivi,prezzoExtra, acconto,costoTot ) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?,null, ?, ?,0, ?,?,?,0,?,0);";
		boolean inserito = false;	
		PreparedStatement insContratto = null;
		Connection con = null;		
		try {
			con = MySqlDaoFactory.connetti();
			insContratto = con.prepareStatement(inserimentoContratto, 
					Statement.RETURN_GENERATED_KEYS);
			insContratto.setString(1, CFDipendente);
			insContratto.setString(2, id);
			insContratto.setString(3,dataInizio);
			insContratto.setString(4, dataLimite);
			insContratto.setString(5, dataRestituzione);
			insContratto.setString(6, luogoRestituzione);
			insContratto.setString(7, tipoNoleggio);
			insContratto.setString(8, null);
			insContratto.setString(9, tipoKm);
			insContratto.setString(10, tipoFascia);
			insContratto.setDouble(11, 0);
			insContratto.setDouble(12, costoKm);
			insContratto.setInt(13, kmEffettivi);
			insContratto.setDouble(14,0);
			insContratto.setDouble(15, acconto);
			insContratto.setDouble(16,0);

			


			inserito = insContratto.executeUpdate() == 1;
			if (inserito) {
				// inserimento account eseguito correttamente
				inserito = insContratto.executeUpdate() == 1;
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
				if (insContratto != null) {
					insContratto.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				gestisciEccezione(e, GestioneErroriInt.NO_MESSAGE);
			}
		}
		return inserito;
	}

	
	@Override
	public boolean setDatiContratto(String id, String dataInizio,
			String dataLimite, String dataRestituzione,
			String luogoRestituzione, String tipoNoleggio, String tipoKm,
			String tipoFascia, double costoKm, int kmEffettivi, double acconto){
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query = "UPDATE Contratto "
					+ "SET dataInizio = ?, dataLimite = ? , "
					+ "dataRestituzione = ?, luogoRestituzione = ?, tipoNoleggio = ?, tipoKm = ?, "
					+ "tipoFascia = ? , costoKm =? , kmEffettivi =?, acconto =?"
					+ "WHERE id = ?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setString(1,dataInizio);
			statement.setString(2, dataLimite);
			statement.setString(3, dataRestituzione);
			statement.setString(4, luogoRestituzione);
			statement.setString(5, tipoNoleggio);
			statement.setString(6, tipoKm);
			statement.setString(7, tipoFascia);
			statement.setDouble(8, costoKm);
			statement.setInt(9, kmEffettivi);
			statement.setDouble(10, acconto);

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

	@Override
	public boolean setTariffaBaseAllocato(String idContratto, double tariffaBase) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query =  "UPDATE  Contratto "
					+ " SET tariffaBase=?"
					+ "WHERE id=?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setDouble(11,tariffaBase);
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
	public boolean setPrezzoExtraAllocato(String idContratto, double prezzoExtra) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query =  "UPDATE  Contratto "
					+ " SET prezzoExtra=?"
					+ "WHERE id=?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setDouble(14,prezzoExtra);
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
	public boolean setCostoTotAllocato(String idContratto, double costoTot) {
		boolean result = false;
		PreparedStatement statement = null;
		Connection con = null;
		try {
			String query =  "UPDATE  Contratto "
					+ " SET costoTot=?"
					+ "WHERE id=?;";

			con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);

			statement.setDouble(16,costoTot);
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
	public ArrayList<HashMap<String, String>> getMacchinaAllocata(
		String idContratto) {
		ArrayList<HashMap<String, String>> result = null;
		String query="SELECT M.targa,  M.modello"
				+ "FROM Macchina AS M JOIN Contratto AS C  ON M.targa=C.macchina"
				+ "WHERE C.id=?";
		
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String, String>>();
			while (results.next()) {
				HashMap<String, String> macchina = new HashMap<String, String>();
				String targa = results.getString("targa");
				macchina.put("targa", targa);
				String modello = results.getString("modello");
				macchina.put("modello", modello);
				
				result.add(macchina);
			}
		} catch (SQLException e) {
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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
	public double  getTariffaBaseAllocata(
		String idContratto) {
		double result=0 ;
		String query="SELECT tariffaBase"
				+ "FROM Contratto"
				+ "WHERE id=?";
		
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			result =0;
			while (results.next()) {
				double tariffaBase = results.getDouble("tariffaBase");
				
				
				result=tariffaBase;
			}
		} catch (SQLException e) {
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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
	public double  getPrezzoExtraAllocata(
		String idContratto) {
		double result=0 ;
		String query="SELECT prezzoExtra"
				+ "FROM Contratto"
				+ "WHERE id=?";
		
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			result =0;
			while (results.next()) {
				double prezzoExtra = results.getDouble("prezzoExtra");
				
				
				result=prezzoExtra;
			}
		} catch (SQLException e) {
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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
	public double  getCostoTotAllocato(
		String idContratto) {
		double result=0 ;
		String query="SELECT costoTot"
				+ "FROM Contratto"
				+ "WHERE id=?";
		
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			result =0;
			while (results.next()) {
				double costoTot = results.getDouble("costoTot");
				
				
				result=costoTot;
			}
		} catch (SQLException e) {
			gestisciEccezione(e, GestioneErroriInt.SEVERE);
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
}
