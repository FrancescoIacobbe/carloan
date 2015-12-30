package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.entity.MacchinaDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi relativi gli Macchina.
 * @author Gaetano
 *
 */
public class Macchina implements LMacchinaInt, SMacchinaInt {
	
	private MacchinaDao dao;
	
	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Macchina() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getMacchinaDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}


	@Override
	public boolean registraMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia) {
		// TODO Auto-generated method stub
		return dao.registraMacchina(targa, modello, disponibilita, manuntenzione, ultimoKm, fascia);
	}

	@Override
	public ArrayList<HashMap<String, String>> getMacchinaSistema() {
		// TODO Auto-generated method stub
		return dao.getMacchinaSistema();
	}

	@Override
	public HashMap<String, String> getDatiMacchina(String targa) {
		// TODO Auto-generated method stub
		return dao.getDatiMacchina(targa);
	}

	@Override
	public boolean setDatiMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia) {
		// TODO Auto-generated method stub
		return dao.setDatiMacchina(targa, modello, disponibilita, manuntenzione, ultimoKm, fascia);
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaMacchine(String key) {
		// TODO Auto-generated method stub
		return dao.ricercaMacchine(key);
	}


	@Override
	public boolean eliminaMacchina(String targa) {
		// TODO Auto-generated method stub
		return dao.eliminaMacchina(targa);
	}

}
