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

import dataAccess.factory.MySqlDaoFactory;
import dataAccess.utility.EmailDao;

/**
 * Implementa l'interfaccia {@link EmailDao} per DBMS MySql.
 * 
 * @author Gaetano
 * 
 */
public class MySqlEmailDao implements EmailDao {

	@Override
	public HashMap<String, String> getContratto(String idContratto,
			String CFCliente) {
		// TODO Auto-generated method stub
		HashMap<String, String> result = null;
		String query = ""
				+ "SELECT D.username AS nome, D.email AS emailDipendente, Con.id AS contratto, "
				+ "C.username AS cliente, C.email AS emailCliente "
				+ "FROM Cliente C, "
				+ "(Contratto Con JOIN Dipendente D ON Con.CFDipendente = D.CF) "
				+ "WHERE Con.id = ? AND C.CF = ?;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			statement.setString(2, CFCliente);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = new HashMap<String, String>();
				String nome = results.getString("nome");
				result.put("nome", nome);
				String email = results.getString("email");
				result.put("email", email);
				String contratto = results.getString("contratto");
				result.put("contratto", contratto);
				String cliente = results.getString("cliente");
				result.put("cliente", cliente);
				String emailCliente = results.getString("emailCliente");
				result.put("emailCliente", emailCliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				gestisciEccezione(e);
			}
		}
		return result;
	}

	@Override
	public ArrayList<HashMap<String, String>> getIndirizzoCliente(String idContratto) {
		ArrayList<HashMap<String, String>> result = null;

		String query = "SELECT C.nome, C.email " 
				+ "FROM Cliente C "
				+ "JOIN Contratto Con ON C.CF = Con.CFCliente "
				+ "WHERE Con.idContratto = ?;";
		PreparedStatement statement = null;
		try {
			Connection con = MySqlDaoFactory.connetti();
			statement = con.prepareStatement(query);
			statement.setString(1, idContratto);
			ResultSet results = statement.executeQuery();
			result = new ArrayList<HashMap<String, String>>();
			while (results.next()) {
				HashMap<String, String> cliente = new HashMap<String, String>();
				String nome = results.getString("nome");
				cliente.put("nome", nome);
				String email = results.getString("email");
				cliente.put("email", email);
				result.add(cliente);
			}
		} catch (SQLException e) {
			gestisciEccezione(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				MySqlDaoFactory.chiudiConnessione();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				gestisciEccezione(e);
			}
		}
		return result;
	}
	
	/**
	 * Invia la richiesta al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 */
	private void gestisciEccezione(Exception e) {
		DAOException ex = new DAOException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, GestioneErroriInt.NO_MESSAGE);
	}
}
