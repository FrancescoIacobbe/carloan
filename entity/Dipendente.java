package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.entity.DipendenteDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi relativi i manager delle competizioni.
 * @author Gianluca
 *
 */
public class Dipendente implements LDipendenteInt, SDipendenteInt{

	private DipendenteDao dao;
	
	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Dipendente() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getDipendenteDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}
	
	@Override
	public HashMap<String, String> getDatiDipendente(String username) {
		// TODO Auto-generated method stub
		return dao.getDatiDipendente(username);
	}

	@Override
	public ArrayList<HashMap<String, String>> getContrattiDipendente(String id) {
		// TODO Auto-generated method stub
		return dao.getContrattiDipendente(id);
	}
	
	@Override
	public HashMap<String, String> getAccountDipendente(String username) {
		// TODO Auto-generated method stub
		return dao.getAccountDipendente(username);
	}

	@Override
	public boolean setAccountDipendente(String username, String email, String password) {
		// TODO Auto-generated method stub
		return dao.setAccountDipendente(username, email, password);
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String idDipendente, String idContratto) {
		// TODO Auto-generated method stub
		return dao.ricercaContratti(idDipendente, idContratto);
	}

	@Override
	public ArrayList<HashMap<String, String>> getDipendenteSistema() {
		// TODO Auto-generated method stub
		return dao.getDipendenteSistema();
	}

	@Override
	public boolean setDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono){
		// TODO Auto-generated method stub
		return dao.setDipendente(username,password, email, nome, cognome,
				dataNascita, cf, città,indirizzo, cap, numTelefono);

	}

	@Override
	public HashMap<String, String> getDipendente(String idDipendente) {
		// TODO Auto-generated method stub
		return dao.getDipendente(idDipendente);
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaDipendente(String key) {
		// TODO Auto-generated method stub
		return dao.ricercaDipendente(key);
	}
	
	@Override
	public boolean registraDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono){
		// TODO Auto-generated method stub
		return dao.registraDipendente(username,password, email, nome, cognome,
				dataNascita, cf, città,indirizzo, cap, numTelefono);
	}
}
