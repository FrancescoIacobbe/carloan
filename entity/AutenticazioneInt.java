package entity;

/**
 * Interfaccia per i servizi di autenticazione.
 * 
 * @author Gianluca
 * 
 */
public interface AutenticazioneInt {

	/**
	 * Esegue il login.
	 * 
	 * @param username
	 *            l'username dell'utente.
	 * @param password
	 *            la password dell'utente.
	 * @return il tipo dell'utente se l'autenticazione va a buon fine,
	 *         altrimenti null.
	 */
	public String login(String username, String password);
}
