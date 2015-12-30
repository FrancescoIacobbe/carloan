package dataAccess.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.ConfiguratorDBReader;
import config.ConfiguratorReader;
import dataAccess.entity.ContrattoDao;
import dataAccess.entity.MacchinaDao;
import dataAccess.entity.RequisitoDao;
import dataAccess.entity.DipendenteDao;
import dataAccess.entity.ManagerSistemaDao;
import dataAccess.entity.ClienteDao;
import dataAccess.entity.UtenteDao;
import dataAccess.mysql.MySqlAutoTextDao;
import dataAccess.mysql.MySqlContrattoDao;
import dataAccess.mysql.MySqlEmailDao;
import dataAccess.mysql.MySqlMacchinaDao;
import dataAccess.mysql.MySqlRequisitoDao;
import dataAccess.mysql.MySqlDipendenteDao;
import dataAccess.mysql.MySqlManagerSistemaDao;
import dataAccess.mysql.MySqlClienteDao;
import dataAccess.mysql.MySqlUtenteDao;
import dataAccess.utility.AutoTextDao;
import dataAccess.utility.EmailDao;

/**
 * Fornisce un'implementazione di DaoFactory per DBMS MySql. Classe Singletone.
 * 
 * @author Gianluca
 * 
 */
public class MySqlDaoFactory extends DaoFactory {

	/**
	 * Oggetto Singletone.
	 */
	private static MySqlDaoFactory dao;
	
	private final static String PATH = "src/config/mySql.cfg";
	private static String host;
	private static String db;
	private static String username;
	private static String password;
	private static String connessione;

	private static Connection con;

	private ConfiguratorReader reader;

	/**
	 * Costruttore privato per la realizzazione del Singletone.
	 */
	private MySqlDaoFactory() {
		this.reader = new ConfiguratorDBReader(PATH);
		host = reader.getField(ConfiguratorDBReader.HOST);
		db = reader.getField(ConfiguratorDBReader.DATABASE);
		username = reader.getField(ConfiguratorDBReader.USERNAME);
		password = reader.getField(ConfiguratorDBReader.PASSWORD);
		connessione = "jdbc:mysql://" + host + "/" + db;
	}

	/**
	 * Fornisce l'unica istanza di MySqlDaoFactory.
	 * 
	 * @return l'istanza di MySqlDaoFactory.
	 */
	public static MySqlDaoFactory getIstance() {
		if (dao == null) {
			dao = new MySqlDaoFactory();
		}
		return dao;
	}

	@Override
	public ContrattoDao getContrattoDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (ContrattoDao) createDao(MySqlContrattoDao.class);
	}


	@Override
	public ClienteDao getClienteDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (ClienteDao) createDao(MySqlClienteDao.class);
	}

	@Override
	public AutoTextDao getAutoTextDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (AutoTextDao) createDao(MySqlAutoTextDao.class);
	}

	@Override
	public ManagerSistemaDao getManagerSistemaDao() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return (ManagerSistemaDao) createDao(MySqlManagerSistemaDao.class);
	}

	/**
	 * Permette di aprire una connessione al database.
	 * 
	 * @return la connessione creata.
	 * @throws SQLException
	 */
	public static Connection connetti() throws SQLException {
		getIstance();
		con = DriverManager.getConnection(connessione, username, password);
		return con;
	}

	/**
	 * Chiude la connessione al database.
	 * @throws SQLException 
	 * 
	 */
	public static void chiudiConnessione() throws SQLException {
		if (con != null) {
			if (!con.isClosed()) {
				con.close();
			}
		}
	}

	@Override
	public UtenteDao getUtenteDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (UtenteDao) createDao(MySqlUtenteDao.class);
	}

	@Override
	public RequisitoDao getRequisitoDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (RequisitoDao) createDao(MySqlRequisitoDao.class);
	}

	@Override
	public DipendenteDao getDipendenteDao() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return (DipendenteDao) createDao(MySqlDipendenteDao.class);
	}

	@Override
	public MacchinaDao getMacchinaDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (MacchinaDao) createDao(MySqlMacchinaDao.class);
	}
	

	@Override
	public EmailDao getEmailDao() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return (EmailDao) createDao(MySqlEmailDao.class);
	}
}
