package dataAccess.factory;


import dataAccess.entity.ContrattoDao;
import dataAccess.entity.MacchinaDao;
import dataAccess.entity.RequisitoDao;
import dataAccess.entity.DipendenteDao;
import dataAccess.entity.ManagerSistemaDao;
import dataAccess.entity.ClienteDao;
import dataAccess.entity.UtenteDao;
import dataAccess.utility.AutoTextDao;
import dataAccess.utility.EmailDao;

/**
 * Classe astratta per l'istanziazione di specifici Data Access Object.
 * 
 * @author Gianluca
 * 
 */
public abstract class DaoFactory {

	/**
	 * Data Access Object per DBMS MySql.
	 */
	public static final int MYSQL = 1;

	/**
	 * Fornisce un'istanza di {@link ClienteDao}.
	 * 
	 * @return un istanza di {@link ClienteDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract ClienteDao getClienteDao()
			throws InstantiationException, IllegalAccessException;
	
	/**
	 * Fornisce una istanza di {@link DipendenteDao}.
	 * @return un'istanza di {@link DipendenteDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract DipendenteDao getDipendenteDao() throws InstantiationException, IllegalAccessException;
	
	/**
	 * Fornisce un'istanza di {@link ManagerSistemaDao}.
	 * @return un'istaza di {@link ManagerSistemaDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract ManagerSistemaDao getManagerSistemaDao() throws InstantiationException, IllegalAccessException;

	/**
	 * Fornisce un'istanza di {@link AutoTextDao}.
	 * 
	 * @return un'istanza di {@link AutoTextDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract AutoTextDao getAutoTextDao() throws InstantiationException,
			IllegalAccessException;

	/**
	 * Fornisce un'istanza di {@link UtenteDao}.
	 * 
	 * @return un'istanza di {@link UtenteDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract UtenteDao getUtenteDao() throws InstantiationException,
			IllegalAccessException;

	

	/**
	 * Fornisce un'istanza di {@link ContrattoDao}.
	 * 
	 * @return un'istanza di {@link ContrattoDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract ContrattoDao getContrattoDao()
			throws InstantiationException, IllegalAccessException;

	/**
	 * Fornisce un'istanza di {@link MacchinaDao}.
	 * 
	 * @return un'istanza di {@link MacchinaDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract MacchinaDao getMacchinaDao()
			throws InstantiationException, IllegalAccessException;
	
	/**
	 * Fornisce un'istanza di {@link RequisitoDao}.
	 * 
	 * @return un'istanza di {@link RequisitoDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract RequisitoDao getRequisitoDao()
			throws InstantiationException, IllegalAccessException;
	
	
	
	/**
	 * Fornisce un'istanza di {@link EmailDao}.
	 * 
	 * @return un'istanza di {@link EmailDao}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract EmailDao getEmailDao()
			throws InstantiationException, IllegalAccessException;

	/**
	 * Fornisce l'istanza di una particolare implementazione di un DAO Factory.
	 * 
	 * @param i
	 *            il tipo di Data Access Object desiderato.
	 * @return uno specifico DAO Factory se i è valido, altrimenti null.
	 */
	public static DaoFactory getDaoFactory(int i) {
		DaoFactory dao = null;
		switch (i) {
		case MYSQL:
			dao = MySqlDaoFactory.getIstance();
			break;
		default:
			dao = null;
			break;
		}
		return dao;
	}

	/**
	 * Crea istanze di specifici DAO.
	 * 
	 * @param c
	 *            la classe dell'oggetto DAO desiderata.
	 * @return uno specifico DAO.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	protected static Object createDao(Class c) throws InstantiationException,
			IllegalAccessException {
		return c.newInstance();
	}
}
