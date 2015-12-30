package entity;

/**
 * Fornisce i servizi di scrittura per il manager di sistema.
 * @author Gianluca
 *
 */
public interface SManagerSistemaInt {

	/**
	 * Imposta i dati account del manager di sistema.
	 * @param password la password.
	 * @param email l'email.
	 * @return true sse l'aggiornamento va a buon fine, altrimenti false.
	 */
	public boolean setAccountManager(String password, String email);
}
