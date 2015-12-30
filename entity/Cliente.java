package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.entity.ClienteDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi inerenti i partecipanti.
 * 
 * @author Gianluca
 * 
 */
public class Cliente implements LClienteInt, SClienteInt {

	private ClienteDao dao;

	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Cliente() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getClienteDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	@Override
	public HashMap<String, String> getDatiCliente(String username) {
		return dao.getDatiCliente(username);
	}

	

	@Override
	public ArrayList<HashMap<String, String>> getContrattoCliente(String id) {
		// TODO Auto-generated method stub
		return dao.getContrattoCliente(id);
	}

	

	@Override
	public HashMap<String, String> getAnagraficaCliente(String username) {
		return dao.getAnagraficaCliente(username);
	}

	@Override
	public HashMap<String, String> getAccountCliente(String username) {
		return dao.getAccountCliente(username);
	}

	@Override
	public boolean setAnagraficaCliente( String nome, String cognome,
			String dataNascita, String CF,
			String città, String indirizzo, String cap, String numTelefono) {
		return dao.setAnagraficaCliente(   nome,  cognome,
				 dataNascita,  CF, città,  indirizzo,  cap,  numTelefono);
	}

	@Override
	public boolean setAccountCliente(String username, String email, String password) {
		return dao.setAccountCliente(username, email, password);
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String id, String idContratto) {
		// TODO Auto-generated method stub
		return dao.ricercaContratti(id, idContratto); 
	}

	@Override
	public boolean registraCliente(String username, String password, String email,
			String nome, String cognome,String dataNascita, String CF,
			String città, String indirizzo, String cap, String numTelefono) {
		// TODO Auto-generated method stub
		return dao.registraCliente(username,password, email,
					nome,  cognome,dataNascita, CF,
					città, indirizzo, cap, numTelefono);
	}

	@Override
	public ArrayList<HashMap<String, String>> getClientiSistema() {
		// TODO Auto-generated method stub
		return dao.getClientiSistema();
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaClienti(String key) {
		// TODO Auto-generated method stub
		return dao.ricercaClienti(key);
	}

}
