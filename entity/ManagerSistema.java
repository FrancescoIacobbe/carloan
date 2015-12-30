package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.HashMap;

import dataAccess.entity.ManagerSistemaDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi inerenti il manager di sistema.
 * @author Gianluca
 *
 */
public class ManagerSistema implements LManagerSistemaInt, SManagerSistemaInt{

	private ManagerSistemaDao dao;
	
	/**
	 * Costruttore Principale.
	 * Istanzia il dao di riferimento.
	 */
	public ManagerSistema() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getManagerSistemaDao();
		} catch (Exception e) {
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}
	@Override
	public boolean setAccountManager(String password, String email) {
		// TODO Auto-generated method stub
		return dao.setAccountManager(email, password);
	}

	@Override
	public HashMap<String, String> getAccountManager() {
		// TODO Auto-generated method stub
		return dao.getAccountManager();
	}
}
