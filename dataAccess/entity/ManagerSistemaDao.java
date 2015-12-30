package dataAccess.entity;

import java.util.HashMap;

/**
 * Interfaccia per la memorizzazione e il recupero dei dati inerenti il manager
 * di sistema.
 * 
 * @author Gianluca
 * 
 */
public interface ManagerSistemaDao {

	/**
	 * Fornisce username, password e email del manager di sistema.
	 * @return un {@link HashMap} contenente i dati account.
	 */
	public HashMap<String,String> getAccountManager();
	
	/**
	 * Imposta email e password del manager di sistema.
	 * @param email l'email.
	 * @param password la password.
	 * @return true sse l'aggiornamento va a buon fine, altrimenti false.
	 */
	public boolean setAccountManager(String email, String password);
}
