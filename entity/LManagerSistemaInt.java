package entity;

import java.util.HashMap;

/**
 * Fornisce i servizi di lettura per il manager del sistema.
 * @author Gianluca
 *
 */
public interface LManagerSistemaInt {

	/**
	 * Fornisce l'username, la password e l'email del manager di sistema.
	 * @return un dizionario contenente l'email, la password e l'username del Manager di sistema.
	 */
	public HashMap<String,String> getAccountManager();
}
