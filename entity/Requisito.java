package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.entity.RequisitoDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi relativi gli Requisito.
 * @author Gaetano
 *
 */
public class Requisito implements LRequisitoInt, SRequisitoInt {
	
	private RequisitoDao dao;
	
	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Requisito() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getRequisitoDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}


	@Override
	public boolean registraRequisito(String id, String modelli, String descrizione) {
		// TODO Auto-generated method stub
		return dao.registraRequisito(id , modelli, descrizione);
	}

	@Override
	public ArrayList<HashMap<String, String>> getRequisitoSistema() {
		// TODO Auto-generated method stub
		return dao.getRequisitoSistema();
	}

	@Override
	public HashMap<String, String> getDatiRequisito(String idRequisito) {
		// TODO Auto-generated method stub
		return dao.getDatiRequisito(idRequisito);
	}

	@Override
	public boolean setDatiRequisito(String id, String modelli, String descrizione) {
		// TODO Auto-generated method stub
		return dao.setDatiRequisito(id , modelli, descrizione);
	}

	@Override
	public ArrayList<HashMap<String, String>> ricercaRequisito(String key) {
		// TODO Auto-generated method stub
		return dao.ricercaRequisito(key);
	}

}
